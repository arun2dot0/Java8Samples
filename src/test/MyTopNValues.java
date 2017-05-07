package test;

import java.util.Collections;

public class MyTopNValues implements TopValues {

	java.util.List<Long> mylist = new java.util.ArrayList<>();
	int sizeToTeturn;

	public MyTopNValues(int topKvalues) {
		sizeToTeturn = topKvalues;
	}

	public void consume(long value) {
		// TO DO: consume/process a new value
		synchronized (mylist) {
			mylist.add(value);
		}
	}

	public long[] top() {
		// TO DO: return the top K values consumes
		//java.util.List<Long> returnList = new java.util.ArrayList<>();
		synchronized (mylist) {
			return mylist.stream().sorted((l1,l2)-> l2.compareTo(l1)).limit(10).mapToLong(l->l).toArray();
		}
	
	}

	public static void main(String[] args) {
		MyTopNValues topNValues = new MyTopNValues(10);

		Thread t1 = new Thread(() -> java.util.stream.LongStream.iterate(0, n -> n + 1)
															    .limit(100)
															    .forEach(l ->topNValues.consume(l)));
		Thread t2 = new Thread(() -> java.util.stream.LongStream.iterate(100, n -> n + 1)
			    .limit(100)
			    .forEach(l ->topNValues.consume(l)));
		Thread t3 = new Thread(() -> java.util.stream.LongStream.iterate(1000, n -> n + 1)
			    .limit(100)
			    .forEach(l ->topNValues.consume(l)));
	
		t3.run();
		t1.run();
		t2.run();
	
		while(!t1.isAlive() &&  !t2.isAlive() && !t3.isAlive())
		{
			java.util.Arrays.stream(topNValues.top()).forEach(System.out::println);
			break;
		}
	}

}
