package br.com.matteusmoreno.spring_mongodb.response;

import br.com.matteusmoreno.spring_mongodb.entity.Person;

import java.time.LocalDateTime;

public record PersonDetailsResponse(
        String id,
        String name,
        Integer age,
        LocalDateTime createdAt) {

    public PersonDetailsResponse(Person person) {
        this(person.getId(), person.getName(), person.getAge(), person.getCreatedAt());
    }
}
