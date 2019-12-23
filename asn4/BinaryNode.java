package cs2210.esraa;

public class BinaryNode {
	//creating variables //
	private Pixel value;
	private BinaryNode left;
	private BinaryNode right;
	private BinaryNode parent;
	
	public BinaryNode(Pixel value, BinaryNode left, BinaryNode right, BinaryNode parent) {
		this.value = value;
		this.left = left;
		this.right = right;
		this.parent = parent;
		
	}
	//Constructor initializing the binary node class//
	public BinaryNode() {
		this.value = null;
		this.right = null;
		this.left = null;
		this.parent = null;
	}
	//returns the parent of the node//
	public BinaryNode getParent() {
		return this.parent;
	}
	//sets the parent of the node//
	public void setParent(BinaryNode parent) {
		this.parent = parent;
	}
	//sets the left child of a node//
	public void setLeft(BinaryNode left) {
		this.left = left;
	}
	//sets the right child of the node//
	public void setRight(BinaryNode right) {
		this.right = right;
	}
	//stores the given pixel in the node// 
	public void setData(Pixel value) {
		this.value = value;
	}
	//returns true if this node is a leaf, false otherwise//
	public boolean isLeaf() {
		if(this.left == null && this.right == null && this.value == null) {
			return true;
		}
		return false;
	}
	//returns the pixel object in this node//
	public Pixel getData() {
		return this.value;
	}
	//returns the left child of this node//
	public BinaryNode getLeft() {
		return this.left;
	}
	//return the right child of this node//
	public BinaryNode getRight() {
		return this.right;
	}
	
}
