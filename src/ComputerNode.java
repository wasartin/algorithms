import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Will Sartin & Josh Ramon
 * The ComputerNode class represents the nodes of the graph G, 
 * which are pairs (ci, t)
 */
public class ComputerNode {
	
	private int id;
	private int timestamp;
	List<ComputerNode> neighbors;
	private int visited;

	public ComputerNode() {
		this.visited = 0;
		this.neighbors = new ArrayList<>();
	}
	
	public ComputerNode(int id, int timestamp) {
		this();
		this.id = id;
		this.timestamp = timestamp;
		this.visited = 0; 
	}
	
	/**
	 * @Required
	 * Returns the ID of the associated computer
	 * @return
	 */
	public int getID() {
		return id;
	}
	
	/**
	 * Sets the id of the computerNode
	 * @param id
	 */
	public void setID(int id) {
		this.id = id;
	}
	
	/**
	 * @Required
	 * REturns the timestamp associated with this node
	 * @return
	 */
	public int getTimestamp() {
		return timestamp;
	}
	
	/**
	 * Sets the timestamp of the ComputerNode
	 * @param timestamp
	 */
	public void setTimestamp(int timestamp) {
		this.timestamp = timestamp;
	}
	
	/**
	 * @Required
	 * Returns a list of ComputerNode objects to which there is an outgoing edge 
	 * from this ComputerNode object
	 * @return
	 */
	List<ComputerNode> getOutNeighbors(){
		return neighbors;
	}
	
	/*
	 * 
	 */
	public boolean addNeighbor(ComputerNode vertex) {
		//if this node is already connected, then return false;
		if(neighbors.contains(vertex)) return false;
		neighbors.add(vertex);
		return true;
	}
	
	@Override
	public String toString() {
		return "[" + id + " : " + timestamp + "]";
	}
	
	/**
	 * Checks 
	 */
	@Override
	public boolean equals(Object other) {
		if(other != null) {// null? 1st commandment 
			if(other instanceof ComputerNode){
				ComputerNode temp = (ComputerNode) other;
				if((temp.getID() == this.getID()) && (temp.getTimestamp() == this.getTimestamp()) ) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * Method for determining if the node is part of the infection path
	 */
	public void markedVisited() {
		this.visited = 1;
	}
	
	/**
	 * Finds out of the node has been visited or not
	 * @return 1 if visited, 0 otherwise
	 */
	public int getVisited() {
		return this.visited;
	}
	
	/**
	 * (Re)set visit
	 * @param visit
	 */
	public void setVisit(int visit) {
		this.visited = visit;
	}

}