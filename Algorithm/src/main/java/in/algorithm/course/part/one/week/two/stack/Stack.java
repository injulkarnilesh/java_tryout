package in.algorithm.course.part.one.week.two.stack;

public interface Stack<T> {
	
	public T pop();
	
	public void push(T item);
	
	public boolean isEmpty();
	
	public class EmptyStack extends RuntimeException {
		
	} 
	
	public class FullStack extends RuntimeException {
		
	}
	
}
