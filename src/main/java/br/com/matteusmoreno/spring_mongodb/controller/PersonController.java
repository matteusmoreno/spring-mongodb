package br.com.matteusmoreno.spring_mongodb.controller;

import br.com.matteusmoreno.spring_mongodb.entity.Person;
import br.com.matteusmoreno.spring_mongodb.request.CreatePersonRequest;
import br.com.matteusmoreno.spring_mongodb.request.UpdatePersonRequest;
import br.com.matteusmoreno.spring_mongodb.response.PersonDetailsResponse;
import br.com.matteusmoreno.spring_mongodb.service.PersonService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/persons")
public class PersonController {

    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping("/create")
    public ResponseEntity<PersonDetailsResponse> create(@RequestBody @Valid CreatePersonRequest request, UriComponentsBuilder uriBuilder) {
        Person person = personService.createPerson(request);
        URI uri = uriBuilder.path("/persons/create/{id}").buildAndExpand(person.getId()).toUri();

        return ResponseEntity.created(uri).body(new PersonDetailsResponse(person));
    }

    @GetMapping("/details/{id}")
    public ResponseEntity<PersonDetailsResponse> detailsById(@PathVariable String id) {
        Person person = personService.detailsById(id);

        return ResponseEntity.ok(new PersonDetailsResponse(person));
    }

    @PutMapping("/update")
    public ResponseEntity<PersonDetailsResponse> update(@RequestBody @Valid UpdatePersonRequest request) {
        Person person = personService.updatePerson(request);

        return ResponseEntity.ok(new PersonDetailsResponse(person));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        personService.deletePerson(id);

        return ResponseEntity.noContent().build();
    }
}
