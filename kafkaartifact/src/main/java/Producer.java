import java.util.Properties;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class Producer {
    public static void main(String[] args) {
    	
    	
    	// bin/kafka-console-consumer.sh --topic flatMapStream --bootstrap-server localhost:9092 --from-beginning --formatter kafka.tools.DefaultMessageFormatter --property print.key=true --property print.value=true --property key.deserializer=org.apache.kafka.common.serialization.StringDeserializer --property value.deserializer=org.apache.kafka.common.serialization.IntegerDeserializer

    	String key1 = "Key1";
    	String key2 = "Key2";
    	String key3 = "Key3";
        String value1 = "value set 6";
        String value2 = "is value set ?";
        String value3 = "set the correct value";
        
        String topicName = "duplicateCheck";
        
        String key4 = "Key4";
        String value4 = "444";
        ProducerRecord<String, String> record4 = new ProducerRecord<String, String>(topicName, key4, value4);
        
        String key5 = "Key5";
        String value5 = "555";
        ProducerRecord<String, String> record5 = new ProducerRecord<String, String>(topicName, key5, value5);
        
        String key6 = "Key6";
        String value6 = "666";
        ProducerRecord<String, String> record6 = new ProducerRecord<String, String>(topicName, key6, value6);
        
        
        
        

        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(props);
        ProducerRecord<String, String> record1 = new ProducerRecord<String, String>(topicName, key1, value1);
        ProducerRecord<String, String> record2 = new ProducerRecord<String, String>(topicName, key2, value2);
        ProducerRecord<String, String> record3 = new ProducerRecord<String, String>(topicName, key3, value3);
        
        producer.send(record1);
        producer.send(record2);
        producer.send(record3);
        producer.send(record4);
        producer.send(record5);
        producer.send(record6);
        producer.close();
        System.out.println("SimpleProducer Completed.");
        System.out.println("SimpleProducer Completed.");
    }
} 