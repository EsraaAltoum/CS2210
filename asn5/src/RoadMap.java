import java.io.*;
import java.util.*;

public class RoadMap {
//declaring private variables for constructor//
	int scale;
	int startingNode, destinationNode;
	int width, length;
	int initialMoney, toll, gain;
	
	Graph graph;
	Stack<Node> path;
/*this is the constructor for the roadmap class
 * Initializes all road map parameters from file input.
 * creates a graph and populates the nodelist and adjacency matrix
 * from file input
 */

	public RoadMap(String inputFile) throws MapException, IOException{
		File f = new File(inputFile);
		BufferedReader f_in;
		try { f_in = new BufferedReader(new FileReader(f));} 
		catch (FileNotFoundException e) {throw new MapException();}

		//initialize all road map parameters
		this.scale = Integer.parseInt(f_in.readLine());
		this.startingNode = Integer.parseInt(f_in.readLine());
		this.destinationNode = Integer.parseInt(f_in.readLine());
		this.width = Integer.parseInt(f_in.readLine());
		this.length = Integer.parseInt(f_in.readLine());
		this.initialMoney = Integer.parseInt(f_in.readLine());
		this.toll = Integer.parseInt(f_in.readLine());
		this.gain = Integer.parseInt(f_in.readLine());
		this.path = new Stack<Node>();
		
		//create an graph with the correct number of nodes
		//construct a character matrix from file input 
		//to build the graph's structure from
		this.graph = new Graph(this.length*this.width);
		char preGraph[][] = new char[2*this.length - 1][2*this.width - 1];
		for(int i = 0; i< 2*this.length - 1; i++) {
			String s = f_in.readLine();
			for(int j = 0; j < s.length(); j++) {
				preGraph[i][j] = s.charAt(j);
			}
		}
		//populate the graph's edges from the character matrix
		for(int i = 0; i < 2*this.length - 1; i++) {
			for(int j = 0 ; j < 2*this.width - 1; j++) {
				int edgeType;
				if(preGraph[i][j] == 'F') edgeType = 0;
				else if(preGraph[i][j] == 'T') edgeType = 1;
				else if(preGraph[i][j] == 'C') edgeType = -1;
				else continue;
				
				if(i > 0 && i < 2*this.length -2 
						&& preGraph[i - 1][j] == '+' && preGraph[i + 1][j] == '+')
					this.graph.insertEdge(
							this.graph.getNode(((i + 1) / 2)*width + j/2), 
							this.graph.getNode(((i - 1) / 2)*width + j/2), 
							edgeType);
				
				if(j > 0 && j < 2*this.width -2 
						&& preGraph[i][j - 1] == '+' && preGraph[i][j + 1] == '+')
					this.graph.insertEdge(
							this.graph.getNode((i/2)*width + (j - 1) / 2), 
							this.graph.getNode((i/2)*width + (j + 1) / 2), 
							edgeType);
				else continue;
			}
		}
		//close file input
		f_in.close();
	}
	/* *
	 * recursive function that uses a modified version of DFS to
	 * find a path from 'start' to 'destination'. the path is stored in the
	 * member variable path
	 *  */
	public Iterator<Node> findPath(int start, int destination, int initialMoney){
		//visit current node, push it onto the path
		Node sNode = this.graph.getNode(start);
		sNode.setMark(true);
		this.path.push(sNode);
		
		//if we are at the destination, return the path.
		if(start == destination) return path.iterator();
		
		//get a list of all incident edges on the current node
		//iterate through the list looking for a node that wasn't visited
		//and is within budget. 
		Iterator<Edge> incidentEdges = graph.incidentEdges(sNode);
		while(incidentEdges.hasNext()) {
			Edge e = incidentEdges.next();
			Node nNode = e.secondEndpoint();
			if(nNode.getName() == sNode.getName()) nNode = e.firstEndpoint();
			
			if(!nNode.getMark() && initialMoney + cost(e) >= 0) {
				initialMoney = initialMoney + cost(e);
				//recursive call to the apply DFS on the next node.
				Iterator<Node> p = findPath(nNode.getName(), destination, initialMoney);
				
				//return the path up until this point
				if(p != null) return p;
				
				//restore budget to it's value before last step
				else initialMoney-=cost(e);
				}
		}
		
		//if node was part of an unsuccessful path, pop it off the path, and un-visit it.
		sNode.setMark(false);
		path.pop();
		return null;
	}

//helper function to calculate cost of going through an edge//
	private int cost(Edge e) {
		if(e.getType() > 0) return -toll;
		if(e.getType() < 0)	return gain;
		return 0;
	}
//returns the starting node//
	public int getStartingNode() {
		return this.startingNode;
	}
//returns the destination node//
	public int getDestinationNode() {
		return this.destinationNode;
	}
//returns the initial amount of money available to pay tolls//
	public int getInitialMoney() {
		return this.initialMoney;
	}
//returns the graph representing the road map//
	public Graph getGraph() {
		return this.graph;
	}

}
