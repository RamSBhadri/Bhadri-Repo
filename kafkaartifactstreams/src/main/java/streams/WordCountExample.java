package streams;

import java.util.Arrays;
import java.util.Properties;
import java.util.regex.Pattern;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KStreamBuilder;
import org.apache.kafka.streams.kstream.KTable;

public class WordCountExample {

    public static void main(String[] args) throws Exception{

        Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "wordcount");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        
        props.put(
        		  StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, 
        		  Serdes.String().getClass().getName());
        props.put(
        		  StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, 
        		  Serdes.String().getClass().getName());
        
        // setting offset reset to earliest so that we can re-run the demo code with the same pre-loaded data
        // Note: To re-run the demo, you need to use the offset reset tool:
        // https://cwiki.apache.org/confluence/display/KAFKA/Kafka+Streams+Application+Reset+Tool
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        // work-around for an issue around timing of creating internal topics
        // Fixed in Kafka 0.10.2.0
        // don't use in large production apps - this increases network load
        // props.put(CommonClientConfigs.METADATA_MAX_AGE_CONFIG, 500);

        KStreamBuilder builder = new KStreamBuilder();

        KStream<String, String> source = builder.stream("duplicateCheck");


        Pattern pattern = Pattern.compile("\\W+", Pattern.UNICODE_CHARACTER_CLASS);
        
        
        
        
        KTable<String, Long> wordCounts = source
          .flatMapValues(value -> Arrays.asList(pattern.split(value.toLowerCase())))
          .groupBy((key, word) -> word)
          .count();	
        
        // key - is value set to the correct correct
        
        /*
        
        key - is
        key - value
        key - set
        key - to
        key - the
        key - correct
        key - value
        
        */
        /*
        		is [key - is] - 1
        		value [key - value,key - value]		 - 2
        		set [key - set] -1
        		to [key - to] - 1
        		the [key - the] - 1
        		correct [ key - correct] - 1
        		
        		*/
        		
        
        
        

        

        String outputTopic = "outputTopic";
        Serde<String> stringSerde = Serdes.String();
        Serde<Long> longSerde = Serdes.Long();
        wordCounts.to(stringSerde, longSerde, outputTopic);
        
        KafkaStreams streams = new KafkaStreams(builder, props);
        streams.start();
         
        Thread.sleep(3000000);
        streams.close();


    }
    
    
}