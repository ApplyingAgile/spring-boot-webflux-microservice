package com.aal.be.examplems.repositories;

import com.aal.be.examplems.models.Person;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface PersonRepository extends ReactiveMongoRepository<Person, String> {

    Flux<Person> findByName(String name);

    Mono<Person> findById(String id);

    Flux<Person> findByAge(int age);

}