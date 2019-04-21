import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/**
 * 
 * @author William Sartin
 *
 *The CommunicationsMonitor class represents the graph G build to answer infection
 *queries. It has the following methods.
 */
public class CommunicationsMonitor {
	
	//AjacencyList instance variable
	private HashMap<Integer, List<ComputerNode>> adjacenyList;
	private boolean createdGraph;
	List<Communication> commList;

	/**
	 * @Required
	 * Constructor with no parameters
	 */
	public CommunicationsMonitor() {
		//TODO
		adjacenyList = new HashMap<>();
		createdGraph = false;
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
			commList = new ArrayList<Communication>();
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
		commList.sort(Communication::compareTo);
		for(Communication c : commList) {
			ComputerNode cn1, cn2;

			if(!adjacenyList.containsKey(c.c1)) {
				cn1 = new ComputerNode(c.c1, c.timestamp);
			} else {
				cn1 = new ComputerNode()
			}

			if(!adjacenyList.containsKey(c.c2)) {

			}


			//			if(!adjacenyList.containsKey(c.c1) && (!adjacenyList.get(c.c1).contains(c.c2))) {
//
//			}
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
		//TODO
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
		return adjacenyList;
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
		if(c > adjacenyList.size()) {
			throw new IllegalArgumentException("Selected id is out of bounds of current adjacenyList. The current size is:" + adjacenyList.size()
			+ ", You requested:" + c);
		}
		adjacenyList.get(c);
		return null;
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
	 */
//	private void BFS(Graph G, Vertex s) {
//		for(Vertex u : V) {
//			u.setColor(Color.WHITE);
//			u.setDistance(Integer.MAX_VALUE);
//			u.setPredecessor(null);
//		}
//		s.setColor(Color.GRAY);
//		s.setDistance(0);
//		s.setPredecessor(null);
//		Queue Q = new Queue();
//		Enqueue(Q, s);
//		while(!Q.isEmpty()) {
//			Vertex u = Q.dequeue();
//			for(Vertex v : G.Adj[u]) {
//				if(v.getColor() == Color.WHITE) {
//					v.setColor(Color.GRAY);
//					v.setDistance();
//					v.setPredecessor(u);
//					Enqueue(Q, v);
//				}
//			u.setColor(Color.BLACK;
//			}
//		}
//	}
	
	
	
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
	public void printAdjacenyList() {
		//TODO
		
	}
}
