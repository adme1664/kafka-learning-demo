package com.adme.kafka.learning.demo.persons.port;

import com.adme.kafka.learning.demo.persons.domain.Person;
import java.util.UUID;

public interface PersonPort {
    void save(Person person);
    void update(Person person);
    void delete(Person person);
    Person findById(UUID id);

}
