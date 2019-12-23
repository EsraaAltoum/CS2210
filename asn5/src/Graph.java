import java.util.Iterator;
import java.util.LinkedList;

public class Graph implements GraphADT {
//declaring private variables for constructor//
	private Node nodeList[];
	private Edge adjMatrix[][];
	private int n;

/*constructor for graph class
 * initializing variables
 * creates a graph with n nodes and no edges
 */
	public Graph(int n){
		this.n = n;
		nodeList = new Node[n];
		for(int i = 0; i < n; i++) {
			nodeList[i] = new Node(i);
		}
		adjMatrix = new Edge[n][n];
		for(int i = 0; i < n; i++) {
			for(int j = 0; j < n; j++) {
				adjMatrix[i][j] = null;
			}
		}
	}

/*adds an edge of the given type connecting u and v
 * throws a graph exception if node does not exist or edge is connected to given nodes
 */
	public void insertEdge(Node nodeu, Node nodev, int edgeType) throws GraphException {
		getNode(nodeu.getName());	//throw exception if u isn't in graph
		getNode(nodev.getName());	//throw exception if v isn't in graph
		
		if(adjMatrix[nodeu.getName()][nodev.getName()] != null) throw new GraphException();
		else{
			adjMatrix[nodeu.getName()][nodev.getName()] = new Edge(nodeu, nodev, edgeType);
			adjMatrix[nodev.getName()][nodeu.getName()] = new Edge(nodeu, nodev, edgeType);
		}
	}

/*
 * returns the node with the specified name
 * throws graph exception if no node of this name exists
 */
	public Node getNode(int name) throws GraphException {
		for(int i = 0; i < n; i++) {
			if (nodeList[i].getName() == name) return nodeList[i];
		}
		throw new GraphException();	
	}
	
/*returns java iterator storing all edges incident on node u
 * returns null if u does not have edges incident
 */
	public Iterator<Edge> incidentEdges(Node u) throws GraphException {
		getNode(u.getName());	//throws exception if u isn't in graph
		LinkedList<Edge> edgeList = new LinkedList<Edge>();
		for(int i = 0; i < this.n; i++) {
			if(adjMatrix[u.getName()][i] != null) edgeList.add(adjMatrix[u.getName()][i]);
		}
		if(!edgeList.isEmpty()) return (Iterator<Edge>)edgeList.iterator();		
		return null;
	}

/*returns the edge connecting nodes u and v
 * throws graph exception if there is no such edge
 */
	public Edge getEdge(Node u, Node v) throws GraphException {
		if(this.areAdjacent(u, v)) 
			return this.adjMatrix[u.getName()][v.getName()];
		throw new GraphException();
	}

/*
 * returns true if u or v are adjacent, else otherwise
 * throws graph exception
 */
	public boolean areAdjacent(Node u, Node v) throws GraphException {
		if(u.getName() >= n || u.getName() < 0) throw new GraphException();
		if(v.getName() >= n || v.getName() < 0) throw new GraphException();
		if(this.adjMatrix[u.getName()][v.getName()] == null) 
			return false;
		return true;
	}
	
}
