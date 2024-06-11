package br.com.matteusmoreno.spring_mongodb.request;

import jakarta.validation.constraints.NotBlank;

public record UpdatePersonRequest(
        @NotBlank
        String id,
        String name,
        Integer age) {
}
