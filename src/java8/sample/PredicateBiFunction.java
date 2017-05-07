package java8.sample;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import static java.util.stream.Collectors.*;

public class PredicateBiFunction {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
		
		Function<List<Integer>, List<Integer>> filterFunctionSimple;
		BiFunction< List<Integer>  ,
		            Predicate<Integer>, 
		            List<Integer>> filterFunction ;
		
	
		filterFunctionSimple = (inputList)->
		{
			return inputList.stream().filter(e-> e%3==0).collect(toList());
		};
		
	  filterFunction = ( inputList , predicate)->{
		return inputList.stream()
			.filter(predicate)
			.collect(toList());
	  };
		
	  System.out.println( filterFunctionSimple.apply(list));
	  
	  System.out.println(
			  	filterFunction.apply(list, e-> e%2==0));
	  
	  System.out.println(
			  	filterFunction.apply(list, new divideby3()));


	}

}

class divideby3 implements Predicate<Integer>
{

	@Override
	public boolean test(Integer t) {
		
		return t%3==0;
	}
	
	
}
