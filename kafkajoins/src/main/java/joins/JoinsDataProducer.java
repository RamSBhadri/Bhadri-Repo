package joins;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

public class JoinsDataProducer {
	 
	  private static final String KEY = "FIXED-KEY";
	 
	  private static final Map<Integer, String> LEFT;
	  static {
	    LEFT = new HashMap<>();
	    LEFT.put(1, null);
	    LEFT.put(3, "A");
	    LEFT.put(5, "B");
	    LEFT.put(7, null);
	    LEFT.put(9, "C");
	    LEFT.put(12, null);
	    LEFT.put(15, "D");
	  }
	 
	  private static final Map<Integer, String> RIGHT;
	  static {
	    RIGHT = new HashMap<>();
	    RIGHT.put(2, null);
	    RIGHT.put(4, "a");
	    RIGHT.put(6, "b");
	    RIGHT.put(8, null);
	    RIGHT.put(10, "c");
	    RIGHT.put(11, null);
	    RIGHT.put(13, null);
	    RIGHT.put(14, "d");
	  }
	  
	  /*
	  private static final Map<Integer, String> LEFT;
	  static {
	    LEFT = new HashMap<>();
	    LEFT.put(1, "AA");
	    LEFT.put(3, "A");
	    LEFT.put(5, "B");
	    LEFT.put(7, null);
	    LEFT.put(9, "C");
	    LEFT.put(12, "CCC");
	    LEFT.put(15, "D");
	  }
	 
	  private static final Map<Integer, String> RIGHT;
	  static {
	    RIGHT = new HashMap<>();
	    RIGHT.put(2, "DDD");
	    RIGHT.put(4, "a");
	    RIGHT.put(6, "b");
	    RIGHT.put(8, "EE");
	    RIGHT.put(10, "c");
	    RIGHT.put(11, "FFF");
	    RIGHT.put(13, "GGG");
	    RIGHT.put(14, "d");
	  }
	  
	  */
	 
	  public static void main(String[] args) {
	 
	    Properties props = new Properties();
	    props.put("bootstrap.servers", "localhost:9092");
	    props.put("acks", "all");
	    props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
	    props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
	 
	    Producer<String, String> producer = new KafkaProducer<>(props);
	 
	    try {
	      for (int i = 0; i < 15; i++) {
	        // Every 10 seconds send a message
	    	  
	    	  
	    	  
	        try {
	          Thread.sleep(10000);
	        } catch (InterruptedException e) {}
	 
	        
	       
	        
	          if (LEFT.containsKey(i + 1)) {
	            producer.send(new ProducerRecord<String, String>("6-kafka-left-stream-topic", KEY, LEFT.get(i + 1)));
	            System.out.println("Time of " + (i+1) + "=====>" + System.currentTimeMillis());
	          }
	          if (RIGHT.containsKey(i + 1)) {
	            producer.send(new ProducerRecord<String, String>("6-kafka-right-stream-topic", KEY, RIGHT.get(i + 1)));
	            System.out.println("Time of " + (i+1) + "=====>" + System.currentTimeMillis());
	          }
	 
	          
	        
	      }
	    } finally {
	      producer.close();
	    }
	 
	  }
	 
	}

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

/*
Time of 1 [ null ]=====>1595491810858
Time of 2 [ null ]=====>1595491820862
Time of 3 [ "A" ]=====>1595491830866
Time of 4 [ "a" ]=====>1595491840871
Time of 5 [ "B" ]=====>1595491850873
Time of 6 [ "b" ]=====>1595491860873
Time of 7 [ null ]=====>1595491870875
Time of 8 [ null ]=====>1595491880879
Time of 9 [ "C" ]=====>1595491890879
Time of 10 [ "c" ]=====>1595491900882
Time of 11 [ null ]=====>1595491910883
Time of 12 [ null ]=====>1595491920884
Time of 13 [ null ]=====>1595491930884
Time of 14 [ "d" ]=====>1595491940885
Time of 15 [ "D" ]=====>1595491950887

60 Seconds windowing


CreateTime:1595491840871	FIXED-KEY	left=A, right=a -----------> "a"
CreateTime:1595491850872	FIXED-KEY	left=B, right=a -----------> "B"
CreateTime:1595491860873	FIXED-KEY	left=A, right=b -----------> "b"
CreateTime:1595491860873	FIXED-KEY	left=B, right=b -----------> "b"
CreateTime:1595491890879	FIXED-KEY	left=C, right=a -----------> "C"
CreateTime:1595491890879	FIXED-KEY	left=C, right=b -----------> "C"
CreateTime:1595491900882	FIXED-KEY	left=B, right=c -----------> "c" [ combination with "A" is missing because its not in the range of 60 seconds ]
CreateTime:1595491900882	FIXED-KEY	left=C, right=c -----------> "c" [ combination with "A" is missing because its not in the range of 60 seconds ]
CreateTime:1595491940884	FIXED-KEY	left=C, right=d -----------> "d" [ combination with "A" and "B" is missing because its not in the range of 60 seconds ]
CreateTime:1595491950887	FIXED-KEY	left=D, right=c -----------> "D" [ combination with "a" and "b" is missing because its not in the range of 60 seconds ]
CreateTime:1595491950887	FIXED-KEY	left=D, right=d -----------> "D" [ combination with "a" and "b" is missing because its not in the range of 60 seconds ]
		
		*/