
import java.util.Properties;

import org.apache.kafka.clients.producer.*;
	
public class Producer {
    public static void main(String[] args) {

        String key = "Key1";
        String value = "Value-2";
        String topicName = "SimpleProducerTopic";

        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092,localhost:9093");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        KafkaProducer<String, String> producer = new KafkaProducer<>(props);
        ProducerRecord<String, String> record = new ProducerRecord<>(topicName, key, value);
        producer.send(record);
        producer.close();
        System.out.println("SimpleProducer Completed.");
    }
} 