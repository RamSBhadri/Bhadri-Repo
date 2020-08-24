package joins;

import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class AggregatorProducer {

	public static void main(String[] args) {
		

        String topicName = "input-topic25";
        
        String key1 = "Key1";
        Integer value1 = null;
        ProducerRecord<String, Integer> record1 = new ProducerRecord<String, Integer>(topicName, key1, value1);
        
        
        String key2 = "Key2";
        Integer value2 = 20;
        ProducerRecord<String, Integer> record2 = new ProducerRecord<String, Integer>(topicName, key2, value2);
        
        String key3 = "Key3";
        Integer value3 = 10;
        ProducerRecord<String, Integer> record3 = new ProducerRecord<String, Integer>(topicName, key3, value3);
        
        String key4 = "Key4";
        Integer value4 = 20;
        ProducerRecord<String, Integer> record4 = new ProducerRecord<String, Integer>(topicName, key4, value4);
        
        String key5 = "Key5";
        Integer value5 = 10;
        ProducerRecord<String, Integer> record5 = new ProducerRecord<String, Integer>(topicName, key5, value5);
        
        String key6 = "Key6";
        Integer value6 = 20;
        ProducerRecord<String, Integer> record6 = new ProducerRecord<String, Integer>(topicName, key6, value6);
        
        
        
        

        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.IntegerSerializer");

        KafkaProducer<String, Integer> producer = new KafkaProducer<String, Integer>(props);
        
        
        producer.send(record1);
        //producer.send(record2);
        //producer.send(record3);
        //producer.send(record4);
        //producer.send(record5);
        //producer.send(record6);
        producer.close();
        System.out.println("SimpleProducer Completed.");
        System.out.println("SimpleProducer Completed.");
		
	}
	
}
