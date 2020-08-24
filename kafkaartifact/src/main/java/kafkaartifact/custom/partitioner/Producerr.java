package kafkaartifact.custom.partitioner;

import java.util.*;

import org.apache.kafka.clients.producer.*;

public class Producerr {

    public static void main(String[] args) throws Exception {

        String topicName = "SensorTopic";

        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("partitioner.class", "kafkaartifact.custom.partitioner.CustomPartitioner");
        
        props.put("speed.sensor.name", "TSS");

        Producer<String, String> producer = new KafkaProducer<String, String>(props);

        for (int i = 0; i < 10; i++)
            producer.send(new ProducerRecord<String, String>(topicName, "SSP" + i, "500" + i));

        for (int i = 0; i < 10; i++)
            producer.send(new ProducerRecord<String, String>(topicName, "TSS", "500" + i));

        producer.close();
        System.out.println("SimpleProducer Completed.");
    }
}