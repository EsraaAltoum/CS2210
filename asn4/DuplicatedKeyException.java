package cs2210.esraa;

public class DuplicatedKeyException extends RuntimeException {
	//Constructor
		public DuplicatedKeyException(){
			super("Illegal: tree contiains give key");	//Create an exception with the message "illegal operation"
		}
		
	}

