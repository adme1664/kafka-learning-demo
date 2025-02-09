package com.adme.kafka.learning.demo.persons.adapter.db;

import com.adme.kafka.learning.demo.persons.domain.Person;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PersonDb {
  private final Logger logger = LoggerFactory.getLogger(PersonDb.class);

  private final ConcurrentMap<UUID, Person> persons;

  public PersonDb() {
    this.persons = new ConcurrentHashMap<>();
  }

  public void save(Person person) {
    persons.put(person.id(), person);
    logger.info("Saved person: {}", person);
  }

  public void update(Person person) {
    persons.put(person.id(), person);
  }

  public void delete(Person person) {
    persons.remove(person.id());
  }

  public Person findById(UUID id) {
    return persons.get(id);
  }


}
