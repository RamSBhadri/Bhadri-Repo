package joins;

import java.util.Properties;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.Aggregator;
import org.apache.kafka.streams.kstream.Grouped;
import org.apache.kafka.streams.kstream.Initializer;
import org.apache.kafka.streams.kstream.KGroupedTable;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Materialized;
import org.apache.kafka.streams.kstream.Produced;
import org.apache.kafka.streams.kstream.Reducer;

public class KTableReduceExample {
	
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

	    
	    KTable<String, Integer> source = builder.table( "input-topic25");
	    
	    
	    // Key1 = 10
	    // Key2 = 20
	    // Key3 = 10
	    // Key4 = 20
	    // Key5 = 10
	    // Key6 = 20
	    
	    
	    
	    KGroupedTable<Integer, String> groupBy = source.groupBy((key, value) -> KeyValue.pair(value, key),Grouped.with(
	    	      Serdes.Integer(),	
	    	      Serdes.String()));
	    
	    //10 = Key1 --  plis key1
	    //20 = Key2
	    //10 = key3 --  plis key1 plus key3
	    //20 = Key4
	    //10 = key5 --  plis key1 plus key3 plus key5
	    //20 = Key6
	    
	    // 10 = Key1, Key3, Key5
	    // 20 = Key3, Key4, Key6
	    
	    
	    
	    

	    KTable<Integer, String> aggregate = groupBy.aggregate(new Initializer<String>() {
                    @Override
                    public String apply() {
                        return "";
                    }
                }, new Aggregator<Integer, String, String>() {
                    @Override
                    public String apply(Integer k, String v, String aggKeyString) { 
                        return aggKeyString + " plus " + v;
                    }
                }, new Aggregator<Integer, String, String>() {
                    @Override
                    public String apply(Integer k, String v, String aggKeyString) {
                    	return aggKeyString + " minus " + v;
                    }
                },
	    		Materialized.with(Serdes.Integer(),	
	  	    	      Serdes.String()));
	    
	    // 10 = Key1 plus Key3 plus Key5
	    // 20 = Key2 plus Key4 plus Key6
                
                
                
	    
	    
	    aggregate.toStream().to( "KGroupedTableAggregator21");
	    //aggregate.to("KGroupedTableAggregator");
	    
	    
		
		final Topology topology = builder.build();
		KafkaStreams streams = new KafkaStreams(topology, props);
		
		
		streams.start();
		
		System.out.println("Aggregation");
		  
		  Thread.sleep(1000000);
		  
		  streams.close();
		
		
		
	}
	
}
