package usage.of.java.utils.function;

import java.util.function.Function;

public class Employee {
	
	
	
	private String name;
	private int stocks;
	
	
	
	
	public Employee(String name, int stocks) {
		super();
		this.name = name;
		this.stocks = stocks;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public int getStocks() {
		return stocks;
	}



	private void setStocks(int stocks) {
		this.stocks = stocks;
	}

	
	public void increaseStocks( Function<Integer,Integer> allocator)
	{
		
		
		if(this.getStocks() < 5000)
		{
			this.setStocks(allocator.apply(this.getStocks()));
		}
		
	}
	

	
	
	

}
