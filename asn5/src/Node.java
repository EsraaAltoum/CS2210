
public class Node {

//declaring private variables for constructor//
	private int name;
	private boolean mark;
/*constructor for node class
 * initializes variables*/
	public Node(int name) {
		this.name = name;
		this.mark = false;
	}
/*marks the node with the specified value as T or F	*/
	public void setMark(boolean mark) {
		this.mark = mark;
	}
/*returns the value with which the node has been marked*/
	public boolean getMark() {
		return this.mark;
	}
/*returns the name of the vertex*/
	public int getName() {
		return this.name;
	}
}
 