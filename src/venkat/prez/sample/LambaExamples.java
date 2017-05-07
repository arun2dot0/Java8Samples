package venkat.prez.sample;

public class LambaExamples {

	/*
	 * What are Lambda expressions
	 * Using Lambda expression
	 * How it fits to java philosophy
	 * Lambdas under the hood
	 * Transforming iterations
	 *  from imperative
	 *  to functional
	 *  
	 *  peek at method references
	 *  parameter as an argument
	 *  parameter as an argument to a static method
	 *  parameter as a target
	 *  
	 *  
	 */
	
	
	/* 
	 * Lambda is an anonymous function
	 */
	
	public static void main(String[] args) {
		
		Thread th = new Thread(new Runnable(){//annonymous inner class
			public void run(){
				System.out.println("In other thread");
			}
		});
		
		th.start();
		
		// function has 4  things .. 
		/*
		 *  1.name , 
		 *  2. parameters , 
		 *  3.body and 
		 *  4.return type
		 *  
		 *  lambda has  body and parameters .. no body .. no return type
		 */
		
		Thread th2 = new Thread(()->
				System.out.println("in thread 2")
			);
		
		th2.start();
		
		System.out.println("In Main");
		
		/*
		 * c -sharp doesn't have backward compatibility
		 * java is backward compatible and can be used with old code
		 * 
		 * Lambdas are backed by single abstract method interfaces-- existing libraries you can pass lambdas
		 * java7 and java8 can live together as java8 can leverage lambda and java7 can pass annonymous inner classes
		 * 
		 */
		
		
		/*
		 * Lambda under the hood
		 * 
		 * second example is just NOT change in the syntax expression
		 */
		Thread th3,th4,th5;
		th3 = new Thread(()->
		System.out.println("in thread 3"));
		th4 = new Thread(()->
		System.out.println("in thread 4"));
		th5 = new Thread(()->
		System.out.println("in thread 5"));
	    th3.start();
	    th4.start();
	    th5.start();
	    
		/*
		 * Scala before 2 .. are using anonymous inner classes for lambda
		 * 
		 * More anonymous inner classes more classes in the disk .. 
		 * loading jars will have more time .. more memory food print .. 
		 * anonymous objects.. more garbage .. run time memory is going to be increased
		 * 
		 */
	    
	    
	    /*
	     * java 7 has invokedynamic .. attach and detach to the function dynamically .. also reattach
	     * at runtime .. function pointers are available in JVM 
	     * 
	     * invokedynamic for implementing dynamic lambda expressions
	     * 
	     * invokedynamic was rewritten in java8 for lambdas
	     * 
	     * no anonymous inner classes in the example
	     * 
	     * all calls are invokedynamic looking at javap
	     * lambda might be static method ,, or instance method.. or routing of invokedynamic to 
	     * an existing method in other class 
	     * 
	     * 
	     *
	     *
	     */

		
	}
}
