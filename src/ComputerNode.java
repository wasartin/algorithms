import java.util.List;

/**
 * 
 * @author watis
 * The ComputerNode class represents the nodes of the graph G, 
 * which are pairs (ci, t)
 */
public class ComputerNode {

	public ComputerNode() {
		
	}
	
	/**
	 * @Required
	 * Returns the ID of the associated computer
	 * @return
	 */
	public int getID() {
		//TODO
		return -1;
	}
	
	/**
	 * @Required
	 * REturns the timestamp associated with this node
	 * @return
	 */
	public int getTimestamp() {
		//TDOD
		return -1;
	}
	
	/**
	 * @Required
	 * Returns a list of ComputerNode objects to which there is an outgoing edge 
	 * from this ComputerNode object
	 * @return
	 */
	List<ComputerNode> getOutNeighbors(){
		//TODO
		return null;
	}
	
}