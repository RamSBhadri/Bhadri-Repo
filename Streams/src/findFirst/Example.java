package findFirst;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import usage.of.java.utils.function.Employee;

public class Example {

	public static void main(String[] args) {

		List<Employee> empList = new ArrayList<Employee>();
		//empList.add(new Employee("Ajay", 1000));
		//empList.add(new Employee("Vijay", 2000));
		//empList.add(new Employee("Vinay", 3000));
		
		
		// wd can use filter to empty the tesultant list
		Employee emp = empList.stream().findFirst().orElse(new Employee("Srini",10000 ));
		
		System.out.println(emp.getName());
		
		/*Optional<Employee> firstElement = empList.stream().findFirst();
		
		if (firstElement.isPresent())
		{
			System.out.println(firstElement.get().getName());
		}
		
		*/
	}
	
	
	

}
