package joins;

import java.time.Duration;
import java.util.Properties;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.JoinWindows;
import org.apache.kafka.streams.kstream.Joined;
import org.apache.kafka.streams.kstream.KStream;

public class StreamToStreamInnerJoin {
	
	public static void main(String[] args) throws InterruptedException {
		
		 
		  Properties props = new Properties();
		  props.put(StreamsConfig.APPLICATION_ID_CONFIG, "6-stream-stream-inner-join");
		  props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		  props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
		  props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());
		 
		  final StreamsBuilder builder = new StreamsBuilder();
		 
		  KStream<String, String> leftSource = builder.stream("6-kafka-left-stream-topic");
		  KStream<String, String> rightSource = builder.stream("6-kafka-right-stream-topic");
		  
		  
		  
		  
		  
		  
		  
		  // KEY, left=1, right=
		  
		  KStream<String, String> joined = leftSource.join(rightSource,
		      (leftValue, rightValue) -> "left=" + leftValue + ", right=" + rightValue, /* ValueJoiner */
		      JoinWindows.of(Duration.ofMillis(60000)),
		      Joined.with(
		          Serdes.String(), /* key */
		          Serdes.String(),   /* left value */
		          Serdes.String())  /* right value */
		  );
		  
		  
		  
		 
		  joined.to("6-kafka-stream-stream-inner-join-out");
		  
		  final Topology topology = builder.build();
		  KafkaStreams streamsInnerJoin = new KafkaStreams(topology, props);
		  streamsInnerJoin.start();
		  
		 
		  Thread.sleep(1000000);
		  
		  streamsInnerJoin.close();
		  
		 
		
	}
	
	
		  
		

}

// https://www.confluent.io/blog/crossing-streams-joins-apache-kafka/
// https://blog.codecentric.de/en/2017/02/crossing-streams-joins-apache-kafka/