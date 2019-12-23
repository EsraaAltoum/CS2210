package cs2210.esraa;

public class EmptyTreeException extends RuntimeException {
	//Constructor
	public EmptyTreeException(){
		super("Illegal: Tree does not contain data");	//Create an exception with the message "illegal operation"
	}
}


