package chapter22;

public class Edge {

	private Vertex v;
	private int cost;
	
	public Edge() {
		
	}
	
	public Edge(Vertex v, int cost) {
		this.v = v;
		this.cost = cost;
	}
	
	public Vertex getV() {
		return v;
	}
	public void setV(Vertex v) {
		this.v = v;
	}
	public int getCost() {
		return cost;
	}
	public void setCost(int cost) {
		this.cost = cost;
	}
}
