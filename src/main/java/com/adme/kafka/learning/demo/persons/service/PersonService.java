package com.adme.kafka.learning.demo.persons.service;

import com.adme.kafka.learning.demo.persons.domain.Person;
import java.util.List;
import java.util.UUID;
import net.datafaker.Faker;

public class PersonService {

  private final Faker faker;

  public PersonService() {
    this.faker = new Faker();
  }

  public List<Person> createListOfPersons(int size) {
    return faker.collection(
            () -> new Person(UUID.randomUUID(), faker.name().firstName(), faker.name().lastName(),
                faker.gender().binaryTypes(), faker.date().birthdayLocalDate(20,30)))
        .len(size)
        .generate();
  }


}
