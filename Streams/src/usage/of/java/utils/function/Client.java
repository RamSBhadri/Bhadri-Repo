package usage.of.java.utils.function;
import java.util.ArrayList;
import java.util.List;

public class Client {

	public static void main(String[] args) {
		Employee emp1 = new Employee("Vinay",1000 );
		Employee emp2 = new Employee("Ajay",2000);
		List<Employee> employees = new ArrayList<Employee>();
		employees.add(emp1);
		employees.add(emp2);
		
		
		System.out.println(emp1.getStocks());
		System.out.println(emp2.getStocks());
		
		//StockAllocator sa = 
		emp1.increaseStocks(stocks -> stocks + 1000  );
		
		emp2.increaseStocks(stocks -> stocks + 2000  );
		
		System.out.println(emp1.getStocks());
		System.out.println(emp2.getStocks());
		
		Runnable r = () -> {System.out.println("I am a separate thread");};

		Thread t = new Thread(r);
		t.start();
		/*
		emp1.increaseStocks(new StockAllocator() {
			public int allocateStocks(int stcoks) {
				return stcoks+2000;
			}
		});
		
		System.out.println(emp1.getStocks());
		
		*/
		
		

	}

	

	
}
