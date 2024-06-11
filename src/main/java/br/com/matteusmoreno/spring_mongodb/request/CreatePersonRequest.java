package br.com.matteusmoreno.spring_mongodb.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreatePersonRequest(
        @NotBlank
        String name,
        @NotNull
        Integer age) {
}
