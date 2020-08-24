package streams;

import java.util.Properties;

import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.ForeachAction;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KStreamBuilder;

public class KStreamArray {
	
	
	public static void main(String[] args) throws InterruptedException {
		
		
		Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "wordcount");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        
        // setting offset reset to earliest so that we can re-run the demo code with the same pre-loaded data
        // Note: To re-run the demo, you need to use the offset reset tool:
        // https://cwiki.apache.org/confluence/display/KAFKA/Kafka+Streams+Application+Reset+Tool
        

        // work-around for an issue around timing of creating internal topics
        // Fixed in Kafka 0.10.2.0
        // don't use in large production apps - this increases network load
        // props.put(CommonClientConfigs.METADATA_MAX_AGE_CONFIG, 500);

        KStreamBuilder builder = new KStreamBuilder();

        final Serde<String> stringSerde = Serdes.String();
        
        KStream<String, String> source = builder.stream(stringSerde, stringSerde, "duplicateCheck");
        source.foreach(new ForeachAction<String, String>() {
            public void apply(String key, String value) {
                System.out.println(key + ": " + value);
            }
         });
        
        System.out.println("Started **********");
		
		KStream<String, String>[] branches = source.branch(
			    (key, value) -> { System.out.println(key + "----" + value);
			    return key.equalsIgnoreCase("Key4");}, /* first predicate  */
			    (key, value) -> { System.out.println(key + "----" + value);
			    return key.equalsIgnoreCase("Key5");}
			  );
		
		
		branches[0].to(stringSerde, stringSerde, "key4stream");
		
		
		KafkaStreams streams = new KafkaStreams(builder, props);
        streams.start();
        
        //source.foreach((key,value) -> { System.out.println(key + "---> " + value);});
        
        //System.out.println("4444444 **********");
        
        //branches[0].foreach((key,value) -> { System.out.println(key + "---> " + value);});
        
        
        //System.out.println("5555555 **********");
        
        //branches[1].foreach((key,value) -> { System.out.println(key + "---> " + value);});
         
        //Thread.sleep(20000);
        streams.close();
	}

}
