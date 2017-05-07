package venkat.prez.sample;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class LambdaExamplesIterations {

	public static void main(String[] args) {
		/*
		 * imperative style
		 */
		//external iterator
		List<Integer> numbers = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
		
		for( int i=0 ; i< numbers.size();i++)
		{
			System.out.println(numbers.get(i));
		}
	
		for( Integer i : numbers)
		{
			System.out.println(i);
		}
		
		// internal iterator
		numbers.forEach(new Consumer<Integer>(){
			@Override
			public void accept(Integer e) {
				System.out.println(e);
			}
			
		} );
		numbers.forEach((Integer e)-> System.out.println(e));
		numbers.forEach(e-> System.out.println(e));
		//paranthesis is optional . but only for one parameter lambdas
		
		//Java8 has type inference only for Lambda expression
		numbers.forEach(System.out::println);
		//simple pass through doesnt have to be specified
		//:: method reference systax
		
		/*
		 * invoking foreach on the collection.. call function on object .. readily benefit from polymorphism
		 * vary the implementation .. seq .. parallel .. or lazy
		 */
		
		
		//lambdas expressions should be glue code .. two lines may be too many.. read venkats blog on this
		numbers.forEach(e->{
			/*
			 * dont add too many lines 
			 * this is antipattern .. try to avoid it 
			 */
		});
		
		
		

	}

}
