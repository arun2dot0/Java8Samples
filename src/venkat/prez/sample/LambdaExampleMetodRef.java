package venkat.prez.sample;

import java.util.Arrays;
import java.util.List;

public class LambdaExampleMetodRef {

	public static void main(String[] args) {
	
		List<Integer> numbers = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
		
	/* method reference is for pass through not if value is changing or result computation is required
	 * 
	 * parameter can be an argument
	 */
		numbers.forEach(e-> System.out.println(e));
		numbers.forEach( System.out::println); //passing an arguemnt to an other instance method
		//System.out is an object 
		
		// can be passed to an static method as well 
		numbers.stream()
		      //   .map(e->String.valueOf(e)) //static method
					.map(String::valueOf)  //static method reference
				.forEach( System.out::println);
		
		numbers.stream()
				//.map(e->e.toString())
				.map(e->String.valueOf(e))
				.map(String::toString)
				.forEach(System.out::println);
		
		System.out.println(
		numbers.stream()
		       .reduce(0,(total,e) ->Integer.sum(total,e))
		       );
		
		System.out.println(
		numbers.stream()
		       .map(String::valueOf)
		      // .reduce("",(carry,str)-> carry.concat(str))
		       .reduce("", String::concat)
				 );
		
		// cannot do if there is a conflict between instance and static method
		       
	}

}
