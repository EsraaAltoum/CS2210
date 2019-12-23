package cs2210.esraa;

public class Location {
//creating variables for constructor//
	private int x;
	private int y;
	
	//Constructor: initializes the x and y for the location class//
	public Location ( int x, int y) {
		this.x = x;
		this.y = y;
	}
	//returns the x coordinate of this location//
	public int xCoord() {
		return this.x;
	}
	//returns the y coordinate of this location//
	public int yCoord() {
		return this.y;
	}
	//compares this location using column order//
	public int compareTo (Location p) {
		//if this position is less than p//
		if(this.x < p.xCoord()) {
			return -1;
		}
		if(this.x > p.xCoord()) {
			return 1;
		}
		else if(this.x == p.xCoord() && this.y < p.yCoord()) {
			return -1;
		}
		//if this position is greater than p//
		else if(this.x == p.xCoord() && this.y > p.yCoord()) {
			return 1;
		}
		//if this position is equal to p//
		else {
			return 0;
		}
		
		
	}
	

}
