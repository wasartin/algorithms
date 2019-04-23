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

	/**
	 * @Required
	 * Constructor with no parameters
	 */
	public CommunicationsMonitor() {
		computerMapping = new HashMap<>();
		createdGraph = false;
		commList = new ArrayList<Communication>();
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
		//Scan triplets in sorted order
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
	
	private void addEdge(ComputerNode one, ComputerNode two) {
		one.addNeighbor(two);
		two.addNeighbor(one);
	}
	
	private void addNewNodeToMapping(ComputerNode toAdd) {
		List<ComputerNode> tempList = new ArrayList<ComputerNode>();
		tempList.add(toAdd);
		computerMapping.put(toAdd.getID(), tempList);
	}
	
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
	 * 
	 * Ex 3:
	 * 		-In Example 1, an infection path would be 
	 * 			(C1, 4), (C2, 4), (c2, 8), (c4, 8), (C3, 8)
	 * 
	 * This method can assume that it will only be called after createGraph() and that
	 * x <= y. This method must run in O(m) time (Number of vertices). This method can
	 * also be called multiple times with different inputs once the graph is constructed
	 * (i.e., once createGraph() has been invoked).
	 * @param c1
	 * @param c2
	 * @param x
	 * @param y
	 * @return
	 */
	public List<ComputerNode> queryInfection(int c1, int c2, int x, int y){
		if(!computerMapping.containsKey(c1)) {
			throw new IllegalArgumentException("Computer Node: " +c1 + "Is not inside the computer mapping");
		}
		if(!computerMapping.containsKey(c2)) {
			throw new IllegalArgumentException("Computer Node: " +c2 + "Is not inside the computer mapping");
		}
		if(y > x) return null;
		//find c1 at time x' where x' >= x
		ComputerNode infectedNode = computerMapping.get(c1).get(0);//Get first instance of computer node 1
		int xNot = infectedNode.getTimestamp();
		if(xNot > x) return null; //right?
		int i = 0;
		while(xNot < x) {
			if(computerMapping.get(c1).get(++i) != null){
				infectedNode = computerMapping.get(c1).get(i);
			}
		}
		//Run BFS or DFS to get all neighbors of infectedNode
		List<ComputerNode> reachable = BFS(infectedNode);
		//if node (c2, y') is reachable from node (c1, x') where y' <= y 
			//Then return transmission seq
		//return null otherwise
		return null;
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
	 * @param c
	 * @return
	 */
	List<ComputerNode> getComputerMapping(int c){
		if(!computerMapping.containsKey(c)) {
			throw new IllegalArgumentException("Selected id is not found in the Computer Mapping. actual: " + computerMapping.keySet().toString()
			+ ", You requested:" + c);
		}
		return computerMapping.get(c);
	}
	
	public List<Communication> getCommunications(){
		return commList;
	}
	
	
	/**
	 * CLRS pseudocode code.
	 * private for now
	 * Finds shortest path from source node to all other connected nodes
	 * @param G
	 * @param s
	 * @return 
	 */
	public List<ComputerNode> BFS(ComputerNode sourceVertex) {
		List<ComputerNode> result = new ArrayList<ComputerNode>();
		ArrayList<Node<ComputerNode>> nodes = new ArrayList<Node<ComputerNode>>();
		for(ComputerNode u : sourceVertex.getOutNeighbors()) {// for all
			Node<ComputerNode> newNode = new Node(u, Color.WHITE, Integer.MAX_VALUE, null);
			nodes.add(newNode);
		}
		sourceVertex.setColor(Color.GRAY);
		Node<ComputerNode> source = new Node(sourceVertex, Color.GRAY, 0, null);
		Queue<Node<ComputerNode>> Q = new PriorityQueue<Node<ComputerNode>>();
		Q.add(source);
		while(!Q.isEmpty()) {
			Node<ComputerNode> u = (Node<ComputerNode>) Q.remove();
			for(Node<ComputerNode> v : nodes) {
				if(v.getColor() == Color.WHITE) {
					v.setColor(Color.GRAY);
					v.setDistance(0);
					v.setPredecessor(u);
					Q.add(v);
				}
			u.setColor(Color.BLACK);
			result.add(u.getData());
			}
		}
		return result;
	}
	
	public String BFS_toString(ComputerNode sourceVertex) {
		ComputerNode nodeInMapping = computerMapping.get(sourceVertex.getID()).get(0);
		List<ComputerNode> nodes = BFS(nodeInMapping);
		String result = "";
		for(int i = 0; i < nodes.size(); i++) {
			ComputerNode curr = nodes.get(i);
			result += curr.toString();
			if(i + 1 < nodes.size()) {
				result += "->";
			}
		}
		return result;
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
//	private void DFS(Graph G) {						//O(V + E)				
//		for(Vertex u : G.V) {						//O(|V|)
//			u.setColor(Color.WHITE);
//			u.setPredecessor(null);
//		}
//		this.time = 0;
//		for(Vertex u : G.V) {						//O(|V|)
//			if(u.getColor() == Color.WHITE) {
//				DFSVisit(G, u);						//O(|E|)
//			}
//		}
//	}
//	
//	private void DFSVisit(Graph G, Vertex u) {
//		this.time = this.time++;
//		u.setDistance(time);							//Discovery Time
//		u.setColor(Color.GRAY);
//		for each vertex in the adjlist Edge (u, v)		//Implement the ADJ list first				//O(|E|)
//			if(v.getColor() == Color.WHITE) {
//				v.setPredecessor(u);
//				DFSVisit(G, v);
//			}
//		u.setColor(Color.BLACK);
//		time++;
//		u.setFinished(time);							Finishing Time
//	}
	
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
    
    public String communicationLinksToString() {
    	String result = "";
    	for(Communication link : commList) {
    		result += link + "\n";
    	}
    	return result;
    }
    
}
