package com.adme.kafka.learning.demo.persons.adapter.kafka;

import com.adme.kafka.learning.demo.commons.utils.KafkaProperties;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PersonKafkaProducer {

  private static final Logger logger = LoggerFactory.getLogger(PersonKafkaProducer.class);
  private final static String topic = "persons_5";
  private final KafkaProducer<String, String> kafkaProducer;
  private int key = 0;


  public PersonKafkaProducer(KafkaProperties properties) {
    kafkaProducer = new KafkaProducer<>(properties.producerProperties());
  }

  public void send(String value) {

    ProducerRecord<String, String> producerRecord = new ProducerRecord<>(topic, "" + key, value);
    key++;
    kafkaProducer.send(producerRecord, (recordMetadata, e) -> {
      if (e == null) {
        logger.info("Topic {}:", recordMetadata.topic());
        logger.info("Offset {}:", recordMetadata.offset());
        logger.info("Partition {}:", recordMetadata.partition());
      } else {
        logger.error("Error while producing", e);
      }
    });
    logger.info("Message sent to topic: {}, key: {}, value: {}", topic, key, value);
  }

  public void close() {
    kafkaProducer.close();
  }


}
