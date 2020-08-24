import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

public class SynchronousProducer {

    public static void main(String[] args) throws Exception {

        String topicName = "cricketscores";
        String key = "Key-1";
        String value = "Value-1";

        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092,localhost:9093");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(props);
        ProducerRecord<String, String> record = new ProducerRecord<String, String>(topicName, key, value);

        try {
            RecordMetadata metadata = producer.send(record).get();
            System.out.println("Message is sent to Partition no " + metadata.partition() + " and offset " + metadata.offset());
            System.out.println("SynchronousProducer Completed with success.");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("SynchronousProducer failed with an exception");
        } finally {
            producer.close();
        }
    }
}