package com.adme.kafka.learning.demo.commons.utils;

import java.util.Properties;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;

public class KafkaProperties {

  private final static String bootstrapServers = "pkc-619z3.us-east1.gcp.confluent.cloud:9092";

  public static Properties producerProperties() {
    Properties properties = new Properties();
    properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
    properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
        StringSerializer.class.getName());
    properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
        StringSerializer.class.getName());
    properties.setProperty(ProducerConfig.ACKS_CONFIG, "all");
    properties.setProperty("client.id", "ccloud-java-client-d09ea076-9b40-47eb-b761-984655a77d16");
    properties.setProperty("security.protocol", "SASL_SSL");
    properties.setProperty("sasl.mechanism", "PLAIN");
    properties.setProperty("session.timeout.ms", "45000");
    properties.setProperty("sasl.jaas.config",
        "org.apache.kafka.common.security.plain.PlainLoginModule required username='GUIDCEXUDRLNAA2A' password='m5i/1j0u0jSqzWCEXq/IGGEf8/d4uuo0cXZICEsXIbHni2LrMXNsPuRHHbmRCaXV';");
    return properties;
  }

  public static Properties consumerProperties(){
    Properties properties = new Properties();
    properties.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
    properties.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG,
        StringDeserializer.class.getName());
    properties.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG,
        StringDeserializer.class.getName());
    properties.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "demo-kafka");
    properties.setProperty("client.id", "ccloud-java-client-d09ea076-9b40-47eb-b761-984655a77d16");
    properties.setProperty("security.protocol", "SASL_SSL");
    properties.setProperty("sasl.mechanism", "PLAIN");
    properties.setProperty("sasl.jaas.config",
        "org.apache.kafka.common.security.plain.PlainLoginModule required username='GUIDCEXUDRLNAA2A' password='m5i/1j0u0jSqzWCEXq/IGGEf8/d4uuo0cXZICEsXIbHni2LrMXNsPuRHHbmRCaXV';");
    return properties;
  }

}
