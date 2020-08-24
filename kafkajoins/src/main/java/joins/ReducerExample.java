package joins;

import java.util.Properties;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Reducer;

public class ReducerExample {
	
	public static void main(String[] args) throws InterruptedException {
		
		Properties props = new Properties();
	    props.put(StreamsConfig.APPLICATION_ID_CONFIG, "wordcount");
	    props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
	    
	    //props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
	    //props.put("value.deserializer", "org.apache.kafka.common.serialization.IntegerDeserializer");
	    // ,,Materialized.as("aggregateSTore").with(Serdes.String(), Serdes.Integer())
	    
	    props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
	    props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.Integer().getClass().getName());
		
	    StreamsBuilder builder = new StreamsBuilder();

	    
	    KStream<String, Integer> source = builder.stream( "input-topic18");

		

	    KTable<String, Integer> reduce = source.groupByKey()
        .reduce(new Reducer<Integer>() {
            @Override
            public Integer apply(Integer currentMax, Integer v) {
            	Integer max = (currentMax > v) ? currentMax : v;
                return max;
            }
        });
	    //reduce.toStream().foreach((k,v) -> System.out.println("Key : " + k + "Value : " + v));
	    
	    
	    reduce.toStream().to( "reduceTopic");
	    
	    
		
		final Topology topology = builder.build();
		KafkaStreams streams = new KafkaStreams(topology, props);
		
		streams.start();
		
		System.out.println("Aggregation");
		  
		  Thread.sleep(1000000);
		  
		  streams.close();
		
		
		
	}
	
}
