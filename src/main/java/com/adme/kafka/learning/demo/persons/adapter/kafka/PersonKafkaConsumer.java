package com.adme.kafka.learning.demo.persons.adapter.kafka;

import com.adme.kafka.learning.demo.commons.utils.KafkaProperties;
import com.adme.kafka.learning.demo.commons.utils.LocalDateJsonSerializer;
import com.adme.kafka.learning.demo.persons.domain.Person;
import com.adme.kafka.learning.demo.persons.port.PersonPort;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.time.Duration;
import java.time.LocalDate;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicBoolean;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PersonKafkaConsumer {

  private final Logger logger = LoggerFactory.getLogger(PersonKafkaConsumer.class);
  private final AtomicBoolean running = new AtomicBoolean(true);
  private static final String topic = "persons_5";
  private final PersonPort personPort;
  private final Gson gson;
  private final KafkaProperties kafkaProperties;

  public PersonKafkaConsumer(PersonPort personPort) {
    this.personPort = personPort;
    this.gson = new GsonBuilder().registerTypeAdapter(LocalDate.class,new LocalDateJsonSerializer()).create();
    kafkaProperties = new KafkaProperties();
  }

  public void consume() {
    try (Consumer<String, String> personConsumer = new KafkaConsumer<>(kafkaProperties.consumerProperties())) {
      Runtime.getRuntime().addShutdownHook(new Thread(()->{
        logger.info("shutting down consumer...");
        running.set(false);
      }));
      personConsumer.subscribe(Collections.singletonList(topic));
      while(running.get()){
        final ConsumerRecords<String, String> personRecords=personConsumer.poll(Duration.ofSeconds(60));
        for(final ConsumerRecord<String,String> personRecord:personRecords ){
          Person person = this.gson.fromJson(personRecord.value(),Person.class);
          this.personPort.save(person);
          logger.info("Consumed person: {}", person);
        }
      }

    }
  }

}
