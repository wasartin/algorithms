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
	Color color;

	public ComputerNode() {
		neighbors = new ArrayList<>();
	}
	
	public ComputerNode(int id, int timestamp) {
		this();
		this.id = id;
		this.timestamp = timestamp;
		this.color = Color.WHITE;
	}
	
	/**
	 * @Required
	 * Returns the ID of the associated computer
	 * @return
	 */
	public int getID() {
		return id;
	}
	
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
	
	public void setTimestamp(int timestamp) {
		this.timestamp = timestamp;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
	
	public Color getColor() {
		return this.color;
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

}