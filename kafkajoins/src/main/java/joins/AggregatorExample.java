package joins;

import java.util.Properties;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.Aggregator;
import org.apache.kafka.streams.kstream.Initializer;
import org.apache.kafka.streams.kstream.KGroupedStream;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Materialized;

public class AggregatorExample {
	
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

	    
	    KStream<String, Integer> source = builder.stream( "input-topic23");
	    
	    source.filter((key,value) -> key.startsWith("Key1"));

		
	    // Key1 - 10
	    // Key2 - 20
	    // Key1 - 30
	    // Key2 - 40
	    // Key3 - 50
	    // Key4 - 60
	    
	    // Key1 - 1
	    
	    //Key1 - 10,20
	    //Key2 - 20,40
	    //Key3 - 50
	    //Key4 - 60
	    
	    
	    // Key1 - {epid:101, deptid:1, salaray : 1000}
	    // Key2 - {epid:102, deptid:2, salaray : 2000}
	    // Key3 - {epid:103, deptid:2, salaray : 3000}
	    // Key4 - {epid:104, deptid:2, salaray : 4000}
	    
	    //deptid:1 -->  101,1000
	    //deptid:2 -->  102,103,104, 9000
	    
	    // Key3 - {epid:103, deptid:1, salaray : 10000}
	    
	    
	    // Key1 - {epid:101, deptid:1, salaray : 1000}
	    // Key2 - {epid:102, deptid:2, salaray : 2000}
	    // Key3 - {epid:103, deptid:1, salaray : 10000}
	    // Key4 - {epid:104, deptid:2, salaray : 4000}
	    
	    //deptid:1 -->  101,1000 + 10,000
	    //deptid:2 -->  102,103,104, 9000 - 3000
	    
	    
	    

	    
	    
	    

	    
	    
	    
	    
	     
	    		
	    		KGroupedStream<String, Integer> groupedKStream = source.groupByKey();
	    		
	    		
	    		KTable<String, Integer> aggregate = groupedKStream.aggregate(new Initializer<Integer>() {
                    @Override
                    public Integer apply() {
                        return 0;
                    }
                }, new Aggregator<String, Integer, Integer>() {
                    @Override
                    public Integer apply(String k, Integer v, Integer aggKeyCount) {
                        return aggKeyCount + 1;
                    }
                });
	    
	    aggregate.toStream().foreach((k,v) -> System.out.println("Key : " + k + "Value : " + v));
	    
	    
	    aggregate.toStream().to( "aggregatedCount17");
	    
	    
		
		final Topology topology = builder.build();
		KafkaStreams streams = new KafkaStreams(topology, props);
		
		streams.start();
		
		System.out.println("Aggregation");
		  
		  Thread.sleep(1000000);
		  
		  streams.close();
		
		
		
	}
	
}
