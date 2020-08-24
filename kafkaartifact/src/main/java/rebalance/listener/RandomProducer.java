package rebalance.listener;


import java.util.*;

import org.apache.kafka.clients.producer.*;

public class RandomProducer {

    public static void main(String[] args) throws InterruptedException {

        String topicName = "rebalancetopic";
        

        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer<String, String>(props);
        
        try {
            while (true) {
                for (int i = 0; i < 100; i++) {
                	
                	String msg1 = "Msg1--" + i;
                    producer.send(new ProducerRecord<String, String>(topicName, 0, "Key1", msg1)).get();
                    
                    System.out.println("Msg 1 sent");
                    
                    String msg2 = "Msg2--" + i;
                    producer.send(new ProducerRecord<String, String>(topicName, 1, "Key2", msg2)).get();
                    
                    System.out.println("Msg 2 sent");
                    
                    
                    
                    
                  
                }
                
                
            }
        } catch (Exception ex) {
            System.out.println("Intrupted");
        } finally {
            producer.close();
        }

    }
}