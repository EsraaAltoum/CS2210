package cs2210.esraa;

public class InexistentKeyException extends RuntimeException{
		//Constructor
			public InexistentKeyException(){
				super("Illegal: key not found");	//Create an exception with the message "illegal operation"
			}
		}

