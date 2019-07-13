package com.aal.be.examplems.controllers;

import com.aal.be.examplems.repositories.PersonRepository;
import com.aal.be.examplems.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
public class PersonController {

    @Autowired
    private final PersonRepository repository;

    public PersonController(PersonRepository repository) {
        this.repository = repository;
    }


    @GetMapping("/person/{id}")
    public Mono<Person> findById(@PathVariable String id) {
        return this.repository.findById(id);
    }

    @PostMapping("/person")
    public Mono<Person> create(@RequestBody Person person) {
        return this.repository.save(person);
    }

    @DeleteMapping("/person/{id}")
    public Mono<Void> delete (@PathVariable  String id) {
        return this.repository.deleteById(id);
    }

    @PutMapping()
    public Mono<Person> update (@RequestBody Person person) {
        return this.repository.save(person);
    }

}