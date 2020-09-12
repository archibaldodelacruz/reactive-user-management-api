package com.example.webflux.router;

import com.example.webflux.constants.PersonConstants;
import com.example.webflux.handler.PersonHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;

@Configuration
public class PersonRouter {

    @Bean
    public RouterFunction<ServerResponse> personsRoute(PersonHandler personHandler){
        return RouterFunctions.route(GET(PersonConstants.PERSON_FUNCTIONAL_END_POINT_V1+"/autenticate").and(accept(MediaType.APPLICATION_JSON_UTF8)),
                personHandler::autenticate);
    }

}


