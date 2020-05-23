package stream.creation;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import usage.of.java.utils.function.Employee;

public class CreateStream {

public static void main(String[] args) {
	
	 Employee[] arrayOfEmps = {
		    new Employee("Ajay", 1000), 
		    new Employee( "Vijay", 2000), 
		    new Employee("Vinay", 3000)
		};

		Stream.of(arrayOfEmps);
		
		
		
		List<Employee> empList = Arrays.asList(arrayOfEmps);
		empList.stream();
		
		
		Stream.of(arrayOfEmps[0], arrayOfEmps[1], arrayOfEmps[2]);
		
		
		
		Stream.Builder<Employee> empStreamBuilder = Stream.builder();

		empStreamBuilder.accept(arrayOfEmps[0]);
		empStreamBuilder.accept(arrayOfEmps[1]);
		empStreamBuilder.accept(arrayOfEmps[2]);

		Stream<Employee> empStream = empStreamBuilder.build();
	
	
}
	
}
