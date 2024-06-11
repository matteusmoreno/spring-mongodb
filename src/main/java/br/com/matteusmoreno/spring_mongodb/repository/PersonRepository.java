package br.com.matteusmoreno.spring_mongodb.repository;

import br.com.matteusmoreno.spring_mongodb.entity.Person;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PersonRepository extends MongoRepository<Person, String> {
}
