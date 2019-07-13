package com.aal.be.examplems.repositories;

import com.aal.be.examplems.models.Person;
import com.aal.be.examplems.repositories.PersonRepository;
import com.mongodb.reactivestreams.client.MongoCollection;
import org.bson.Document;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.CollectionOptions;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static reactor.test.StepVerifier.create;


@RunWith(SpringRunner.class)
@SpringBootTest(properties = {"server.port=0", "spring.cloud.bus.enabled=false", "spring.cloud.discovery.enabled=false", "spring.cloud.consul.enabled=false", "spring.cloud.config.discovery.enabled=false"})
public class PersonRepositoryTest {

    @Autowired
    ReactiveMongoOperations operations;

    @Autowired
    PersonRepository personRepository;

    @Before
    public void setUp() {

        Mono<MongoCollection<Document>> recreateCollection = operations.collectionExists(Person.class) //
                .flatMap(exists -> exists ? operations.dropCollection(Person.class) : Mono.just(exists)) //
                .then(operations.createCollection(Person.class, CollectionOptions.empty() //
                        .size(1024 * 1024) //
                        .maxDocuments(100) //
                        .capped()));

        create(recreateCollection).expectNextCount(1).verifyComplete();

        Flux<Person> insertAll = operations.insertAll(Flux.just(new Person("Walter", 10, "50"), //
                new Person("Skyler", 10, "45"), //
                new Person("Saul", 32, "42") //
        ).collectList());

        create(insertAll).expectNextCount(3).verifyComplete();
    }

    @Test
    public void shouldInsertAndCountData() {

        Mono<Long> saveAndCount = personRepository.count() //
                .doOnNext(System.out::println) //
                .thenMany(personRepository.saveAll(Flux.just(new Person("Hank", 67, "43"), //
                        new Person("Mike", 46, "62")))) //
                .last() //
                .flatMap(v -> personRepository.count()) //
                .doOnNext(System.out::println);

        create(saveAndCount).expectNext(5L).verifyComplete();
    }


    @Test
    public void shouldQueryDataWithQueryDerivation() {
        create(personRepository.findByName("Walter")).expectNextCount(1).verifyComplete();
    }

    @Test
    public void findById() {
        create(personRepository.findById("50")).expectNextCount(1).verifyComplete();
    }

    @Test
    public void findByAge() {
        create(personRepository.findByAge(10)).expectNextCount(2).verifyComplete();
    }
}