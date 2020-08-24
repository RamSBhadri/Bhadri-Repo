package rebalance.listener;

import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

public class RandomConsumer {

    public static void main(String[] args) throws Exception {

        String topicName = "rebalancetopic";

        String groupName = "RG";
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("group.id", groupName);
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("enable.auto.commit", "false");

        KafkaConsumer<String, String> consumer = new KafkaConsumer<String,String>(props);
        RebalanceListener rebalanceListner = new RebalanceListener(consumer);

        consumer.subscribe(Arrays.asList(topicName), rebalanceListner);
        try {
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(10);
                
                int count =0;
                for (ConsumerRecord<String, String> record : records) {
                        System.out.println("Topic:"+ record.topic() + 
                            " Partition:" + record.partition() + 
                            " Offset:" + record.offset() + " Value:"+ record.value());
                    // Do some processing and save it to Database
                    rebalanceListner.addOffset(record.topic(), record.partition(), record.offset());
                    
                    
                }
                
                consumer.commitAsync();
                
            }
        } catch (Exception ex) {
            System.out.println("Exception.");
            
          consumer.commitSync(rebalanceListner.getCurrentOffsets());
            
            ex.printStackTrace();
        } finally {
            consumer.close();
        }
    }

}
