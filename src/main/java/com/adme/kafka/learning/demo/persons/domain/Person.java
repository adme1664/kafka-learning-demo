package com.adme.kafka.learning.demo.persons.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

public record Person(UUID id, String lastName, String firstName, String gender, LocalDate dob) implements
    Serializable {

}
