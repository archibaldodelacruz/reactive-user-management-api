package com.example.webflux.document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Person {

    @Id
    private String id;
    private String username;
    private String password;
    private String nombre;
    private String apellido;
    private int edad;
    private String genero;

}
