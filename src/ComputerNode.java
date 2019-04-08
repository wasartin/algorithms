import java.util.List;

/**
 * 
 * @author Will Sartin
 * The ComputerNode class represents the nodes of the graph G, 
 * which are pairs (ci, t)
 */
public class ComputerNode {
	
	private int id;
	private int timestamp;
	List<ComputerNode> neighbors;

	public ComputerNode() {
		
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
		return "Vertex:" + id;
	}
	
	
}