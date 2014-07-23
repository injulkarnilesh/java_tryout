package in.nilesh.designpattern.exception;

public class WrongProductIDException extends Exception{
	
	public WrongProductIDException() {}
	
	public WrongProductIDException(String message) {
		super(message);
	}
}