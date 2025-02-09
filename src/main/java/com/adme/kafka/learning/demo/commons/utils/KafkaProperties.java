package com.adme.kafka.learning.demo.commons.utils;

import java.util.Properties;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;

public class KafkaProperties {

  private final  Properties properties;

  public KafkaProperties(){

    properties = new Properties();
    String bootstrapServers = System.getenv("CONFLUENT_BOOTSTRAP_SERVER");
    String apiKey = System.getenv("CONFLUENT_API_KEY");
    String apiSecret = System.getenv("CONFLUENT_API_SECRET");

    properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
    properties.setProperty("client.id", "ccloud-java-client-d09ea076-9b40-47eb-b761-984655a77d16");
    properties.setProperty("security.protocol", "SASL_SSL");
    properties.setProperty("sasl.mechanism", "PLAIN");
    properties.setProperty("session.timeout.ms", "45000");
    properties.setProperty("sasl.jaas.config",
        "org.apache.kafka.common.security.plain.PlainLoginModule required username='"+apiKey+"' password='"+apiSecret+"';");
  }



  public  Properties producerProperties() {

    properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
        StringSerializer.class.getName());
    properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
        StringSerializer.class.getName());
    properties.setProperty(ProducerConfig.ACKS_CONFIG, "all");
    return properties;
  }

  public  Properties consumerProperties(){

    properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
        StringDeserializer.class.getName());
    properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
        StringDeserializer.class.getName());
    properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "demo-kafka");

    return properties;
  }

}
