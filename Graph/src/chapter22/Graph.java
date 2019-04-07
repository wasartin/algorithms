package chapter22;

import java.util.List;

public class Graph {

	private List<Vertex> V;
	private List<List<Edge>> adjacenyList;  //Gotta hammer this out
	//TODO make a nil node
	
	private int time = 0;
	
	public Graph() {
		
	}
	
	/**
	 * TODO page 595
	 * CLRS pseduo code.
	 * private for now
	 * Finds shortest path from source node to all other connected nodes
	 * @param G
	 * @param s
	 */
	private void BFS(Graph G, Vertex s) {
		for(Vertex u : V) {
			u.setColor(Color.WHITE);
			u.setDistance(Integer.MAX_VALUE);
			u.setPredecessor(null);
		}
		s.setColor(Color.GRAY);
		s.setDistance(0);
		s.setPredecessor(null);
		//QUEUE
	}
	
	//TODO
	/**
	 * CLRS
	 * page 601
	 * BFS method, might need to move
	 * @param G
	 * @param s
	 * @param v
	 */
	public void printPath(Graph G, Vertex s, Vertex v) {
		if(s == v) {
			System.out.println(s);
		}
		else if(v.getPredecessor() == null) {
			System.out.println("No Path from " + s + " to " + v + " exists");
		}
		else {
			printPath(G, s, v.getPredecessor());
			System.out.println(v);
		}
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
	private void DFS(Graph G) {						//O(V + E)				
		for(Vertex u : G.V) {						//O(|V|)
			u.setColor(Color.WHITE);
			u.setPredecessor(null);
		}
		this.time = 0;
		for(Vertex u : G.V) {						//O(|V|)
			if(u.getColor() == Color.WHITE) {
				DFSVisit(G, u);						//O(|E|)
			}
		}
	}
	
	private void DFSVisit(Graph G, Vertex u) {
		this.time = this.time++;
		u.setDistance(time);							//Discovery Time
		u.setColor(Color.GRAY);
//		for each vertex in the adjlist Edge (u, v)		//Implement the ADJ list first				//O(|E|)
//			if(v.getColor() == Color.WHITE) {
//				v.setPredecessor(u);
//				DFSVisit(G, v);
//			}
//		u.setColor(Color.BLACK);
//		time++;
//		u.setFinished(time);							Finishing Time
	}
	
	
	
	//TODO 22.3-13* (hard)
	/**
	 * A directed graph G = (V, E) is singly connected if u -> v implies that G contains @ most
	 * one simple path from u to v for all vertices u, v in V. Give a eff algo.
	 */
	public boolean isSinglyConnected() {
		
		return false;
	}
}
