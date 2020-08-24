package streams;

import java.util.Arrays;
import java.util.Properties;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KStreamBuilder;

public class flatMapValues {

	public static void main(String[] args) throws InterruptedException {
		
		
		
		Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "wordcount");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		
        KStreamBuilder builder = new KStreamBuilder();

        
        KStream<String, String> source = builder.stream(Serdes.String(), Serdes.String(), "duplicateCheck");
		

		// key1 --> I am working in SAP
        // Key1 ---> I
        // Key1 ---> am
		
		KStream<String, String> words = source.flatMapValues(value -> Arrays.asList(value.split("\\s+")));
		
		words.to(Serdes.String(), Serdes.String(), "flatMapValuesStream");
		
		KafkaStreams streams = new KafkaStreams(builder, props);
        streams.start();
        
        Thread.sleep(30000);
        
        streams.close();
	}
	
}
