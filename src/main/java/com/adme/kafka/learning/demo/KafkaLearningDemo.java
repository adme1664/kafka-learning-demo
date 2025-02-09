package com.adme.kafka.learning.demo;

import com.adme.kafka.learning.demo.commons.utils.LocalDateJsonSerializer;
import com.adme.kafka.learning.demo.persons.adapter.db.PersonDb;
import com.adme.kafka.learning.demo.persons.adapter.kafka.PersonKafkaConsumer;
import com.adme.kafka.learning.demo.persons.adapter.kafka.PersonKafkaProducer;
import com.adme.kafka.learning.demo.persons.domain.Person;
import com.adme.kafka.learning.demo.persons.port.PersonGateway;
import com.adme.kafka.learning.demo.persons.port.PersonPort;
import com.adme.kafka.learning.demo.persons.service.PersonService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class KafkaLearningDemo {

  private static final Logger log = LoggerFactory.getLogger(KafkaLearningDemo.class);
  private final PersonPort personPort;
  private final PersonKafkaProducer personKafkaProducer;
  private final PersonService personService;
  private final Gson gson;
  private final PersonKafkaConsumer personKafkaConsumer;


  public KafkaLearningDemo() {
    PersonDb personDb = new PersonDb();
    this.personPort = new PersonGateway(personDb);
    this.personKafkaProducer = new PersonKafkaProducer();
    this.personService = new PersonService();
    gson = new GsonBuilder().serializeNulls()
        .registerTypeAdapter(LocalDate.class, new LocalDateJsonSerializer())
        .create();
    personKafkaConsumer = new PersonKafkaConsumer(personPort);
  }

  public void run() {
    ScheduledExecutorService executorService = Executors.newScheduledThreadPool(2);
      executorService.scheduleAtFixedRate(() -> {
        log.info("Pushing data in Kafka");
        List<Person> list = personService.createListOfPersons(2);
        for (Person person : list) {
          String personJson = this.gson.toJson(person);
          personKafkaProducer.send(personJson);
        }
      }, 5, 10, TimeUnit.SECONDS);

  }

  public void consumeData() {
    personKafkaConsumer.consume();
  }

  public void close() {
    log.info("Closing producers ...");
    personKafkaProducer.close();
  }

  public static void main(String[] args) {

    KafkaLearningDemo kafkaLearningDemo = new KafkaLearningDemo();

    Runtime.getRuntime().addShutdownHook(new Thread(kafkaLearningDemo::close));

    kafkaLearningDemo.run();

    kafkaLearningDemo.consumeData();

  }

}
