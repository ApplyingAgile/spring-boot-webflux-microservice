package com.aal.be.examplems.controllers;

import com.aal.be.examplems.models.Person;
import com.aal.be.examplems.repositories.PersonRepository;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@WebFluxTest
public class PersonControllerTest {

    @Autowired
    private WebTestClient webClient;

    @MockBean
    private PersonRepository personRepository;

    private static Map<String, Person> personMap = new HashMap<>();

    @BeforeClass
    public static void setup() {
        personMap.put("john1", new Person("john1", 10, "10"));
        personMap.put("john2", new Person("john2", 20, "100"));
        personMap.put("john3", new Person("john3",21, "1000"));
    }


    @Test
    public void getPerson() {

        given(personRepository.findById(anyString())).willReturn(Mono.just(new Person("john2", 20, "100")));

        webClient.get().uri("/person/{id}", 100).accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody(Person.class)
                .isEqualTo(personMap.get("john2"));
    }


}