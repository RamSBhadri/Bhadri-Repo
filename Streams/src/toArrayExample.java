import java.util.ArrayList;
import java.util.List;

import usage.of.java.utils.function.Employee;

public class toArrayExample {

public static void main(String[] args) {
	
	List<Employee> empList = new ArrayList<Employee>();
	empList.add(new Employee("Ajay", 1000));
	empList.add(new Employee( "Vijay", 2000));
	empList.add(new Employee("Vinay", 3000));
	
	
	
	Employee[] array = empList.stream().filter(emp -> emp.getName().startsWith("V")).toArray(Employee[]::new);
	
	System.out.println(array.length);
	
	
}
	
}
