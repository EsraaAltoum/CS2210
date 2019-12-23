
public class Edge {

/*declaring private variables for constructor*/
	private Node u;
	private Node v;
	private int type;
/*constructor for edge class 
 * initializes 
 */
	public Edge(Node u, Node v, int type) {
		this.u = u;
		this.v = v;
		this.type = type;
	}
//returns the first endpoint of the edge//
	public Node firstEndpoint() {
		return this.u;
	}
//returns the second endpoint of the edge//
	public Node secondEndpoint() {
		return this.v;
	}
//returns the type of the edge//
	public int getType() {
		return this.type;
	}
}

