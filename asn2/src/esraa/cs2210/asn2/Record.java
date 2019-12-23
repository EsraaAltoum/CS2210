package esraa.cs2210.asn2;

public class Record {
	
	/* this class represents an entry in the dictionary,
	   matching a configuration with its score
	 */
	
	// declaring variables, configuration and score//
	private String config;
	private int score;
	
	// this is the constructor  which returns a specified configuration and its score//
	//INPUT: config of type string, score of type int
	public Record(String config, int score) {
		this.config = config;
		this.score = score;
	}
	
	//this method gets the configuration//
	//OUTPUT: returns configuration stored in this record//
	public String getConfig() {
		return this.config;
		
	}
	// this method gets the score//
	//OUTPUT: returns the score in this record//
	public int getScore() {
		return this.score;
		
	}
	
}

