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
import org.apache.kafka.streams.kstream.KTable;

public class KTableToKTableInnerJoin {
	
	public static void main(String[] args) throws InterruptedException {
		
		/*
		 * 
		 * 
		 * 
		 * Time of 1 null =====>1597300465099
Time of 3 A =====>1597300485110
Time of 5 B =====>1597300505116
Time of 7 null =====>1597300525121
Time of 9 C =====>1597300545127
Time of 12 null =====>1597300575136
Time of 15 D =====>1597300605145


Time of 2 null =====>1597300475105
Time of 4 a =====>1597300495111
Time of 6 b =====>1597300515116
Time of 8 null =====>1597300535122
Time of 10 c =====>1597300555128
Time of 11 null =====>1597300565133
Time of 13 null =====>1597300585139
Time of 14 d =====>1597300595142
		 */
		
		
		Properties props = new Properties();
		  props.put(StreamsConfig.APPLICATION_ID_CONFIG, "stream-stream-inner-join");
		  props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
		  props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
		  props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());
		 
		  final StreamsBuilder builder = new StreamsBuilder();
		 
		  KTable<String, String> leftSource = builder.table("14-kafka-left-stream-topic");
		  KTable<String, String> rightSource = builder.table("14-kafka-right-stream-topic");
		   
		  /*
		 
		  KStream<String, String> joined = leftSource.join(rightSource,
		      (leftValue, rightValue) -> "left=" + leftValue + ", right=" + rightValue,
		      JoinWindows.of(Duration.ofMillis(60000)),
		      Joined.with(
		          Serdes.String(),
		          Serdes.String(),
		          Serdes.String())
		  );
		  
		  */
		  
		  KTable<String, String> joined = leftSource.join(rightSource,
				    (leftValue, rightValue) -> "left=" + leftValue + ", right=" + rightValue /* ValueJoiner */
				  );
		 
		  joined.toStream().to("14-kafka-table-table-inner-join");
		 
		  final Topology topology = builder.build();
		  KafkaStreams streamsInnerJoin = new KafkaStreams(topology, props);
		  streamsInnerJoin.start();
		  
		  Thread.sleep(1000000);
		  
		  streamsInnerJoin.close();
		
		
	}

}
