package com.example.webflux.repository;

import com.example.webflux.document.Person;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface PersonReactiveRepository extends ReactiveMongoRepository<Person,String> {
    Mono<Person> findByUsernameAndPassword(String username,String password);
}