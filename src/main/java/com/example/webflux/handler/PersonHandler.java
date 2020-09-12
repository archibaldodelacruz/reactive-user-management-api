package com.example.webflux.handler;

import com.example.webflux.document.Person;
import com.example.webflux.repository.PersonReactiveRepository;
import org.apache.logging.log4j.util.Strings;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Optional;

import static org.springframework.web.reactive.function.BodyInserters.fromObject;

@Component
public class PersonHandler {

    PersonReactiveRepository personReactiveRepository;

    public PersonHandler(PersonReactiveRepository personReactiveRepository) {
        this.personReactiveRepository = personReactiveRepository;
    }

    public Mono<ServerResponse> autenticate(ServerRequest serverRequest){
        Optional<String> username = serverRequest.queryParam("username");
        Optional<String> password = serverRequest.queryParam("password");

        Mono<Person> itemMono = personReactiveRepository.findByUsernameAndPassword(username.orElse(Strings.EMPTY),password.orElse(Strings.EMPTY));

        return  itemMono.flatMap(
                item ->
                        ServerResponse.ok()
                                .contentType(MediaType.APPLICATION_JSON_UTF8)
                                .body(fromObject(item))).log("Getting item:")
                .switchIfEmpty( ServerResponse.notFound().build());

    }
}
