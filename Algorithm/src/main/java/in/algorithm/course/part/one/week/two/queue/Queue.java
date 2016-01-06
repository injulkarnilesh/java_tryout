package in.algorithm.course.part.one.week.two.queue;

public interface Queue<T> {
	boolean isEmpty();
	void enqueue(T item);
	T dequeue();
	int size();
	
	public class EmptyQueue extends RuntimeException {
		
	}
	
}
