import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class FlatMapExample {

	
	public static void main(String[] args) {
		
		
		// Complex objects
		List<List<String>> namesList = Arrays.asList( 
			      Arrays.asList("Srini", "Prapurna"), 
			      Arrays.asList("Shervin", "Bhadri"), 
			      Arrays.asList("Mavra", "Shiney"));
		
		List<String> collect = namesList.stream().flatMap(list -> list.stream()).collect(Collectors.toList());
		
		collect.forEach(System.out::println);
		
		System.out.println(collect.size());
		
		
		// flatMapToInt example
		

        int[] intArray = {1, 2, 3, 4, 5, 6};

        Stream<int[]> streamArray = Stream.of(intArray);

        IntStream intStream = streamArray.flatMapToInt(x -> Arrays.stream(x));

        intStream.forEach(x -> System.out.println(x));
        
        intStream.forEach(System.out::println);
		
	}
	
}
