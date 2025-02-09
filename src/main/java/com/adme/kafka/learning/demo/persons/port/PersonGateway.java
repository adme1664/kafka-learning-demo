package com.adme.kafka.learning.demo.persons.port;

import com.adme.kafka.learning.demo.persons.adapter.db.PersonDb;
import com.adme.kafka.learning.demo.persons.domain.Person;
import java.util.UUID;

public class PersonGateway implements PersonPort {

  private final PersonDb personDb;

  public PersonGateway(PersonDb personDb) {
    this.personDb = personDb;
  }

  @Override
  public void save(Person person) {
    this.personDb.save(person);
  }

  @Override
  public void update(Person person) {
    this.personDb.update(person);
  }

  @Override
  public void delete(Person person) {
    this.personDb.delete(person);
  }

  @Override
  public Person findById(UUID id) {
    return personDb.findById(id);
  }
}
