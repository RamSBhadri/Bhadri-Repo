package streams;

import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KStreamBuilder;

public class flatMapExample {

	public static void main(String[] args) {
		
		
		
		Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "wordcount");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
		
        KStreamBuilder builder = new KStreamBuilder();

        
        KStream<String, String> source = builder.stream(Serdes.String(), Serdes.String(), "duplicateCheck");
		
		
		
		
		KStream<String, Integer> transformed = source.flatMap(
			     // Here, we generate two output records for each input record.
			     // We also change the key and value types.
			     // Example: (345L, "Hello") -> ("HELLO", 1000), ("hello", 9000)
			    (key, value) -> {
			      List<KeyValue<String, Integer>> result = new LinkedList<>();
			      result.add(KeyValue.pair(key.toUpperCase(), 1000));
			      result.add(KeyValue.pair(key.toLowerCase(), 9000));
			      return result;
			    }
			  );
		
		transformed.to(Serdes.String(), Serdes.Integer(), "flatMapStream");
		
		KafkaStreams streams = new KafkaStreams(builder, props);
        streams.start();
	}
	
}
