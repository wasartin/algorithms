package project.pa2.main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 
 * @author William Sartin & Josh Ramon
 *
 *The CommunicationsMonitor class represents the graph G build to answer infection
 *queries. It has the following methods.
 */
public class CommunicationsMonitor {
	
	private HashMap<Integer, List<ComputerNode>> computerMapping;
	private boolean createdGraph;
	List<Communication> commList;
	
	private List<ComputerNode> infectedPath;

	/**
	 * @Required
	 * Constructor with no parameters
	 */
	public CommunicationsMonitor() {
		computerMapping = new HashMap<>();
		createdGraph = false;
		commList = new ArrayList<Communication>();
		infectedPath = new ArrayList<ComputerNode>();
	}

	/**
	 * @Required
	 * Takes as input two integer c1, c2, and a timestamp. This triple represents the
	 * fact that the computers with IDs c1 and c2 have communicated at the given 
	 * timestamp. This method should run in O(1). Any invocation of this method after
	 * createGraph() is called will be ignored
	 * @param c1
	 * @param c2
	 * @param timestamp
	 */
	public void addCommunication(int c1, int c2, int timestamp) {
		if(!createdGraph) {
			commList.add(new Communication(c1, c2, timestamp));
		}
	}
	
	/**
	 * @Required
	 * Constructs the data structure as specified in section 2. 
	 * This method will run in O(n + mlogm) time
	 */
	public void createGraph() {
		createdGraph = true;//Now addCommunication(...) cannot be run
		Collections.sort(commList, new Comparator<Communication>() {
		    @Override
		    public int compare(Communication comm1, Communication comm2) {  
		        return Integer.signum(comm1.getTimestamp() - comm2.getTimestamp());  
		    }
		});
		for(Communication link : commList) {
			//Create Nodes if they do not exist
			ComputerNode compNode1 = new ComputerNode(link.getC1(), link.getTimestamp());
			ComputerNode compNode2 = new ComputerNode(link.getC2(), link.getTimestamp());
			addEdge(compNode1, compNode2);
			if(!computerMapping.containsKey(link.getC1())) {//Add new computerNode
				addNewNodeToMapping(compNode1);
			} else {//Computer has been referenced before, append to mapping
				appendNodeToComputerMapping(compNode1);
			}
			if(!computerMapping.containsKey(link.getC2())) {
				addNewNodeToMapping(compNode2);
			}else {
				appendNodeToComputerMapping(compNode2);
			}
		}
	}
	
	/**
	 * Private helper method for adding and edge between two nodes
	 * @param one
	 * @param two
	 */
	private void addEdge(ComputerNode one, ComputerNode two) {
		one.addNeighbor(two);
		two.addNeighbor(one);
	}
	
	/**
	 * Private helper method for adding a new node to the computer mapping 
	 * (First instance of this index)
	 * @param toAdd
	 */
	private void addNewNodeToMapping(ComputerNode toAdd) {
		List<ComputerNode> tempList = new ArrayList<ComputerNode>();
		tempList.add(toAdd);
		computerMapping.put(toAdd.getID(), tempList);
	}
	
	/**
	 * Private helper method for appending or updating a node in the already existing index.
	 * @param toAppend
	 */
	private void appendNodeToComputerMapping(ComputerNode toAppend) {
		List<ComputerNode> tempList = getComputerMapping(toAppend.getID());
		if(!tempList.contains(toAppend)) {
			ComputerNode prevNode = tempList.get(tempList.size() - 1);
			prevNode.addNeighbor((toAppend));
			tempList.add(toAppend);
			computerMapping.put(toAppend.getID(), tempList);
		}else {//it does contain this one already, but we need to update it with it's new neighbor
			ComputerNode tempNode = tempList.get(tempList.indexOf(toAppend));
			tempNode.addNeighbor(toAppend.getOutNeighbors().get(0));
		}
	}
	
	/**
	 * 	 * @Required
	 * Determines whether computers c2 could be infected by time y if computer c1 
	 * was infected at time x. If so, the method returns an ordered list of 
	 * ComputerNode object that represents the transmission sequence. This sequence
	 * is a path in graph G. The first ComputerNode object on the path will correspond
	 * to c1. Similarly, the last ComputerNode object on the path will correspond to
	 * c2. If c2 cannot be infected, then return null

	 * This method can assume that it will only be called after createGraph() and that
	 * x <= y. This method must run in O(m) time (Number of vertices). This method can
	 * also be called multiple times with different inputs once the graph is constructed
	 * (i.e., once createGraph() has been invoked).
	 * @param c1
	 * @param c2
	 * @param x
	 * @param y
	 * @return list of the infection path. or Null if no path exists
	 */
	public List<ComputerNode> queryInfection(int c1, int c2, int x, int y){
		if(!isViablePath(c1, c2, x, y)) return null;
		
		ComputerNode infectedNode = getSourceNodeForQuery(c1, x);
		ComputerNode targetNode = getTargetNodeForQuery(c2, y);
		
		infectedNode.markedVisited();
		DFS(infectedNode, targetNode);
		if(targetNode.getVisited() == 0) {
			return null;
		}
		return this.infectedPath;
	}
	
	/**
	 * Private helper method that does the error checks for queryInfection
	 * Also resets the infectedPath, should probably do that somewhere else.
	 * @param c1
	 * @param c2
	 * @param x
	 * @param y
	 * @return true if the path is viable, false otherwise.
	 */
	private boolean isViablePath(int c1, int c2, int x, int y) {
		if(infectedPath.size() > 0) {//reset all nodes
			for(ComputerNode n : infectedPath) {
				n.setVisit(0);
			}
		}
		infectedPath = new ArrayList<ComputerNode>();//(re)set infectedPath
		//Error handling
		if(!computerMapping.containsKey(c1) || !computerMapping.containsKey(c2)) {//
			return false;//throw new IllegalArgumentException("Computer Id must be inside network");
		}
		if(y < x) {//target time must be before infect time
			return false;
		}
		//Get last node of each list to see if the infect or target time ever happen.
		if(getLastNodeInList(c1).getTimestamp() < x) return false; 	//If the last time is before the infect time then it's impossible.
		if(getFirstNodeInList(c2).getTimestamp() > y) return false; //if the first node in the list here is after the target time, then it doesn't happen
		return true;
	}
	
	private ComputerNode getSourceNodeForQuery(int c1, int x) {
		ComputerNode infectedNode = new ComputerNode(c1, x);
		int xNot;	//xNot >= x :: this is the first possible node of infection for Computer Node
		int i = 0;

		ComputerNode tempNode = new ComputerNode();		//Get a node actually in graph, if possible
		do {	
			tempNode = computerMapping.get(c1).get(i++);
			xNot = infectedNode.getTimestamp();
		}while(xNot < x && i < computerMapping.get(c1).size());//whaaat?
		infectedNode = tempNode;
		return infectedNode;
	}
	
	private ComputerNode getTargetNodeForQuery(int c2, int y) {
		ComputerNode targetNode = new ComputerNode(c2, y);
		ComputerNode tempNode = new ComputerNode();
		int yNot;	//yNot <= y
		int j = computerMapping.get(c2).size() - 1;
		do {
			tempNode = computerMapping.get(c2).get(j--);
			yNot = targetNode.getTimestamp();
		}while(yNot > y && j >= 0);
		targetNode = tempNode;
		return targetNode;
	}
	
	/** TODO probably delete
	 * Private helper method that returns the last node in a list.
	 * @param index
	 * @return
	 */
	private ComputerNode getLastNodeInList(int index) {
		List<ComputerNode> tempList = computerMapping.get(index);
		return tempList.get(tempList.size() - 1);
	}
	
	/** TODO probably delete
	 * Private helper node that returns first node in a list.
	 * @param index
	 * @return
	 */
	private ComputerNode getFirstNodeInList(int index) {
		List<ComputerNode> tempList = computerMapping.get(index);
		return tempList.get(0);
	}
	
	/**
	 * @Required
	 * Returns a HashMap that represents the mapping between an Integer and a list
	 * of ComputerNode objects. The Integer represents the ID of some computer Ci, 
	 * while the list consists of pairs (ci, t1), (ci, t2), ... , (ci, tk), 
	 * represents by ComputerNode objects, that specify that Ci has communicated
	 * with other computers at times t1, t2,...,tk. The list for each computer must
	 * be ordered by time: i.e., t1 < t2 < ... < tk
	 * 
	 * Notes: Key = Computer, Value = its List of neighbors
	 * @return
	 */
	public HashMap<Integer, List<ComputerNode>> getComputerMapping(){
		return computerMapping;
	}
	
	/**
	 * @Required
	 * Returns the list of ComputerNode objects associated with computer c by 
	 * performing a lookup in the mapping
	 *
	 * @param c index of computer node
	 * @return computer mapping at the index given
	 */
	List<ComputerNode> getComputerMapping(int c){
		if(!computerMapping.containsKey(c)) {
			throw new IllegalArgumentException("Selected id is not found in the Computer Mapping. actual: " + computerMapping.keySet().toString()
			+ ", You requested:" + c);
		}
		return computerMapping.get(c);
	}
	
	/**
	 * Returns the communication links in this computer mapping.
	 * @return List of communication links.
	 */
	public List<Communication> getCommunications(){
		return commList;
	}
	
	/**
	 * Method to return the infected path.
	 * @return path of infected nodes
	 */
    public List<ComputerNode> getInfectedPath() {
    	return this.infectedPath;
    }
	
	/**
	 * CLRS
	 * Creates depth-first forest
	 * ALso timestamps each vertex. 
	 * 			v.d when first discovered
	 * 			v.f when it is blackened (when v is done examing adj list)
	 * 			Between the numbers 1 and 2 |V|
	 * 			For every vertex u,
	 * 				u.d < u.f
	 */
	private void DFS(ComputerNode source, ComputerNode targetNode) {	
		//mark all nodes unvisisted
		infectedPath = new ArrayList<ComputerNode>();
		for(ComputerNode u : source.getOutNeighbors()) {						//O(|V|)
			if(u.getVisited() == 0 && u.getTimestamp() <= targetNode.getTimestamp()) {
				u.setVisit(DFSVisit(u, targetNode));
				if(u.getVisited() == 1) {
					infectedPath.add(u);
				}					//O(|E|)
			}
		}
		if(infectedPath.size() > 0) {
			infectedPath.add(source);
			Collections.reverse(infectedPath);
		}
	}

	/**
	 * 
	 * @param sourceNode
	 * @param targetNode
	 * @return integer 
	 */
	private int DFSVisit(ComputerNode sourceNode, ComputerNode targetNode) {
		if(sourceNode.equals(targetNode)) {
			//sourceNode.markedVisited();
			return 1;//Base case
		}
		for(ComputerNode v : sourceNode.getOutNeighbors()) {
			sourceNode.markedVisited();
			if(v.getVisited() == 0 && v.getTimestamp() <= targetNode.getTimestamp()) {
				v.setVisit(DFSVisit(v, targetNode));
				if(v.getVisited() == 1) {
					infectedPath.add(v);
					return 1;
				}
			}
		}
		return 0;
	}
	
	/**
	 * Helper method
	 * Just to see what an infected list looks like for debugging.
	 * @param infectedNodes
	 * @return
	 */
	public String infectedPathToString(List<ComputerNode> infectedNodes) {
		String result ="";
		for(int i = 0; i < infectedNodes.size(); i++) {
			result += infectedNodes.get(i);
			if(i + 1 < infectedNodes.size()) {
				result += "->";
			}
			
		}
		return result;
	}
	
	/**
	 * Helper method just to visualize the graph
	 */
    public String computerMappingToString() {
    	String result = "";;
    	for(Integer i : computerMapping.keySet()) {
    		result += String.valueOf(i) + ": ";
    		List<ComputerNode> currList = computerMapping.get(i);
    		for(int j = 0; j < currList.size(); j++) {
    			ComputerNode currNode = currList.get(j);
        		result += currNode.toString();
        		if(j < currList.size() - 1) {
        			result += "->";
        		}
    		}
    		result += "\n";
    	}
    	return result;
    }
    
    /**TODO delete
     * Helper method to visualize the communication links. This should jsut be done in communication
     * @return
     */
    public String communicationLinksToString() {
    	String result = "";
    	for(Communication link : commList) {
    		result += link + "\n";
    	}
    	return result;
    }   
}