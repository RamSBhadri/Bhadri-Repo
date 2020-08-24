import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

public class Consumer {

    public static void main(String[] args) throws Exception {

        String topicName = "test";
        String groupName = "Group1";

        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("group.id", groupName);
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        
        KafkaConsumer<String,String> consumer = new KafkaConsumer <String,String>(props);
        consumer.subscribe(Arrays.asList(topicName));

        while (true) {
            ConsumerRecords records = consumer.poll(100);
            for (Object consumerrecord: records) {
            	
            	ConsumerRecord record = (ConsumerRecord)consumerrecord;
            	
                System.out.println(record.value());
            }
        }
    }
}