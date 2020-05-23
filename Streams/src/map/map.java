package map;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import usage.of.java.utils.function.Employee;

public class map {

	public static void main(String[] args) {

		List<Employee> empList = new ArrayList<Employee>();
		empList.add(new Employee("Ajay", 1000));
		empList.add(new Employee( "Vijay", 2000));
		empList.add(new Employee("Vinay", 3000));
		
		
		
	    
		List<String> empNamesList = empList.stream().map( emp -> emp.getName()).collect(Collectors.toList());
		
		empNamesList.forEach(System.out::println);
	
	}
	
}
