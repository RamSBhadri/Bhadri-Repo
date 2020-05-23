package forEach;

import java.util.stream.Stream;

import usage.of.java.utils.function.Employee;

public class ForEach {
	
	
	public static void main(String[] args) {
		
		Employee[] arrayOfEmps = {
			    new Employee("Ajay", 1000), 
			    new Employee( "Vijay", 2000), 
			    new Employee("Vinay", 3000)
			};

			Stream<Employee> employeeStream = Stream.of(arrayOfEmps);
			//employeeStream.forEach(emp -> System.out.println(emp.getName()));
			
			employeeStream.forEach(System.out::println);
			
			
	}
	
	
	

}
