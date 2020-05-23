

public interface InterfaceA { 
    default void defaultMethod(){ 
        System.out.println("Interface A default method"); 
    } 
    
    default void defaultMethodd(){ 
        System.out.println("Interface A default method"); 
    } 
}

class InterfaceAImpl implements InterfaceA {
	
	public void defaultMethod(){ 
        System.out.println("Interface A default method"); 
    } 
	
}

