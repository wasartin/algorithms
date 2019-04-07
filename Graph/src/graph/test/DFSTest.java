package graph.test;

public class DFSTest {

	
	//Thm 22.7 (Parenthesis Thm)
	/**
	 * Exactly One thing holds
	 * 	1. the intervals [u.d, u.f] & [v.d, v.f] are disjoint
	 * 	2. the interval [u.d, u.f] is entirely contained within the interval [v.d, v.f]
	 * 	3. the interval [v.d, v.f] is entirely contained within the interval [u.d, u.f]
	 * 
	 * 
	 * - should i do an interval class or hold these values in the nodes?
	 */
	
	//Corollary 22.8 (Nesting of desc intervals
	/**
	 * Vertex v is a proper descendent of vertex u in the DFS-forest for a (un)directed graph G
	 * iff u.d < v.d < v.f < u.f
	 */
	
	//THm 22.9 White-Path THm
	/**
	 * No idea how to test this one. I feel like this is a strongly implied property that is inherit from the 
	 * previous thm and corollary
	 */
	
	//Thm 22.10 Edge
	/**
	 * In a DFS of an undirected graph G, every edge of G is either a tree edge or a back edge
	 * 
	 * gotta fully imp edge
	 */
}
