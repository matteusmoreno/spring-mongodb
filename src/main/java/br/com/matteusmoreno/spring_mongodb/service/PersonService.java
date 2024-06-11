package br.com.matteusmoreno.spring_mongodb.service;

import br.com.matteusmoreno.spring_mongodb.entity.Person;
import br.com.matteusmoreno.spring_mongodb.repository.PersonRepository;
import br.com.matteusmoreno.spring_mongodb.request.CreatePersonRequest;
import br.com.matteusmoreno.spring_mongodb.request.UpdatePersonRequest;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Transactional
    public Person createPerson(CreatePersonRequest request) {
        Person person = new Person();

        BeanUtils.copyProperties(request, person);
        person.setCreatedAt(LocalDateTime.now());

        personRepository.save(person);

        return person;
    }

    public Person detailsById(String id) {
        return personRepository.findById(id).orElseThrow();
    }

    @Transactional
    public Person updatePerson(UpdatePersonRequest request) {
        Person person = personRepository.findById(request.id()).orElseThrow();

        if (request.name() != null) {
            person.setName(request.name());
        }
        if (request.age() != null) {
            person.setAge(request.age());
        }

        personRepository.save(person);

        return person;
    }

    public void deletePerson(String id) {
        personRepository.deleteById(id);
    }
}
