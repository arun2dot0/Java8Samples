package test;

public interface TopValues {
	// Consume a value
	void consume(long value);
	// Return the top K values
	long[] top();
}

