package venkat.prez.sample;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;


public class LambdaExampleStreams {

	public static void main(String[] args) {
		List<Integer> numbers = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
		
		System.out.println(
				numbers
					//.stream()
					.parallelStream() // powerful .. 
					.filter(e -> e%2==0)
					.mapToInt(e-> compute(e))
					.sum()
					);
		
		System.out.println(
		numbers.stream()
		       .filter(e-> e %2==0)
		      // .map(e-> e*2)
		       .map(e-> e*2.0) // input type is int ... output is double
		       .reduce(0.0,(carry,e)-> carry+e)
		       
				);
		
		       	/*
		 * uses more threads and process to do the process faster 
		 * 
		 * spend more effort and resources for 
		 *  a. it makes sense .. problem is paralleizable
		 *  b. willing to spend resource
		 *  c. data size is big enough 
		 *  d. task is big enough that paralleize will get better performance
		 */
		
		
		/*
		 * Lambda are gateway drug and streams are the addiction
		 * 
		 * Streams are abstraction .. not physical data set
		 * non mutating the data in pipeline in the above examples.. no shared mutability
		 * 
		 * Stream operation 
		 *   filter - takes Predicate<T>
		 *   map - transforming function  number of input = number of output
		 *        no gurantees on the type of output with respect to the type of input
		 *   specialized reduce
		 *   map takes Function<T,R> to return Stream<R>
		 *   
		 *   filter and map works on the swim lanes .. works on one element at a time doesnt worry about 
		 *   the left or the right item 
		 *   
		 *   reduce cuts across the swimlanes
		 *   
		 *   reduce on Stream<T> has two parameters
		 *   first parammeter is of type T
		 *   second parameter is of type BiFunction<T>
		 */
		
		System.out.println(
				numbers.stream()
				       .filter(e-> e %2==0)
				      // .map(e-> e*2)
				       .mapToDouble(e-> e*2.0) 
				       .sum()
				       
						);
		
		List<Integer> numbers2= Arrays.asList(1,2,3,4,5,1,2,3,4,5);
		
		List<Integer> doubleOfEven = new ArrayList<>();
		
		// WRONG way to do this
		numbers2.stream()
		       .filter(e->e%2==0)
		       .map(e-> e*2)
		       .forEach(e-> doubleOfEven.add(e)); // multiple threads may change this data at same time
		// mutability is okay , shared mutability is devils work 
		
		Set<Integer> doubleOfEven2 = 
				numbers2.stream()
				.filter(e-> e%2==0)
				.map(e -> e*2)
				.collect(toSet());
		
		doubleOfEven2.forEach(System.out::println);
		
		
		List<Person> personList = Arrays.asList(
				new Person("Sara",23,"F"),
				new Person("Sara",33,"F"),
				new Person("Bob",23,"M"),
				new Person("Michael",23,"M"),
				new Person("Scott",43,"M"),
				new Person("John",43,"M"),
				new Person("Lisa",23,"F"),
				new Person("Mary",21,"F")
				);
		
		/*
		 * given list of people , create a map
		 * where name and age is the key and value is  of type persons
		 */
	
		System.out.println(
	personList.stream()
			  .collect(toMap(
					  		person-> person.getName()+"-"+ person.getAge(),
					  		person-> person))	
			  
				);
		
		/*
		 * given list of people , create a map
		 * where name is the ey and value is list all the people with that name
		 */
		
		System.out.println(
				personList.stream()
						  .collect(groupingBy(Person::getName))
							);
		/*
		 * given list of people , create a map
		 * where name is the key and value is list all ages of people
		 */
		System.out.println(
				personList.stream()
						  .collect(groupingBy(Person::getName
								  ,mapping(Person::getAge,toList())	))
						  
							);
					
		
		System.out.println(
		numbers.stream()
			.filter( e -> e >3)
			.filter( e -> e%2==0)
			.map( e-> e*2)
			.findFirst()
				); // same unit of work as imperative style .. gives optional as the value may not exist
		/* 
		 * Stream doesnt apply one operation on element . applies all the operation at one time
		 * applies entier computation on one element .. only after that it applies to the next element
		 * 
		 * Imperative and functional should have the same unit of work .. Same Cost 
		 * 
		 * because of Laxy evaluation -- efficient
		 * 
		 * 
		 */
					
		/*
		 * Stream can be sized or may be unsized.. ordered or unordered .. distinct or non-distinct
		 * or sorted or unsorted 
		 */
		
		numbers2.stream()
				.filter(e-> e%2==0)
				.distinct()
				//.sorted()
				.forEach(System.out::println);
		//sized ,ordered .. non-distinct , non-sorted-- can be easily changed
	
		/* infinite streams?
		 * 
		 */
System.out.println(    
		Stream.iterate(100, e-> e+1) // unsized stream
		);

System.out.println(
compute(18,2)	
		);
		
	}
	
	public static int compute( int number) {
		//time intensive
		try{
			Thread.sleep(1000);
		}catch(Exception e){}
		return number *2;
	}
	
	public static int compute(int k , int n)
	{
		/*
		 * iterative  for the same will be messy code
		 */
		return Stream.iterate(k, e-> e+1)// unbounded and lazy
					.filter(e->e%2 ==0)// unbounded and lazy
					.filter(e-> Math.sqrt(e)>20)// unbounded and lazy
					.mapToInt(e-> e*2)// unbounded and lazy
					.limit(n)// bounded and lazy
					.sum();
	}
}



class Person{
	String name;
	int age;
	String sex;
	
	Person(String name, int age,String sex)
	{
		this.name = name;
		this.age = age;
		this.sex = sex;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	
	
}
