import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import usage.of.java.utils.function.Employee;

public class PeekExample {

	public static void main(String[] args) {
		
		List<Employee> empList = new ArrayList<Employee>();
		empList.add(new Employee("Ajay", 1000));
		empList.add(new Employee( "Vijay", 2000));
		empList.add(new Employee("Vinay", 3000));
		
		
		// To change the inner state of an element
		List<Employee> newEmpList = empList.stream().peek(emp -> emp.setName(emp.getName().toUpperCase())).peek(System.out::println).collect(Collectors.toList());
		
		newEmpList.forEach(emp -> System.out.println(emp.getName()));
		
		
		// for debugging
		List<String> collect = Stream.of("one", "two", "three", "four")
		  .filter(e -> e.length() > 3)
		  .peek(e -> System.out.println("Filtered value: " + e))
		  .map(String::toUpperCase)
		  .peek(e -> System.out.println("Mapped value: " + e))
		  .collect(Collectors.toList());
		
		collect.forEach(System.out::println);
		
		
	}
	
}
