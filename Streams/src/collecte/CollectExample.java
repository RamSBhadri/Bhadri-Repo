package collecte;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import usage.of.java.utils.function.Employee;

public class CollectExample {
	
public static void main(String[] args) {
	
	Employee[] arrayOfEmps = {
		    new Employee("Ajay", 1000), 
		    new Employee( "Vijay", 2000), 
		    new Employee("Vinay", 3000)
		};

	List<Employee> empList = Stream.of(arrayOfEmps).collect(Collectors.toList());
		
		System.out.println(empList.size());
		
		for(Employee emp : empList)
		{
			System.out.println(emp.getName());
		}
	
}

}
