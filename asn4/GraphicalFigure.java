package cs2210.esraa;

public class GraphicalFigure implements GraphicalFigureADT {
	//declaring variables//
	private int id;
	private int width;
	private int height;
	private String type;
	private Location offset;
	private BinarySearchTree figure;
	
	//constructor initializing variables//
	public GraphicalFigure(int id, int width, int height, String type, Location pos) {
		this.id = id;
		this.width = width;
		this.height = height;
		this.type = type;
		this.offset = pos;
		this.figure= new BinarySearchTree();
	}


	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public Location getOffset() {
		return offset;
	}

	public void setOffset(Location offset) {
		this.offset = offset;
	}

	public void addPixel(Pixel pix) throws DuplicatedKeyException {
		figure.put(figure.getRoot(), pix);
		
	}
	
	public boolean intersects(GraphicalFigure fig) {
		if(this.offset.xCoord()+width/2 >= fig.getOffset().xCoord() - fig.getWidth()/2) return true;
		
		if(this.offset.yCoord()+height/2 >= fig.getOffset().yCoord() - fig.getHeight()/2) return true;
		
		return false;
		
	}
	

}
