package cs2210.esraa;

public class Pixel {
//this class represents the data items to be stored in a binary tree//
	private int color;
	private Location p;
	
	//Constructor initializes pixel//
	public Pixel(Location p, int color) {
		this.color = color;
		this.p = p;
	}
	//returns location//
	public Location getLocation() {
		return this.p;
	}
	//returns color//
	public int getColor() {
		return this.color;
	}

}
