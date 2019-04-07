package chapter22;

public class Vertex {

	private Color color;
	private Vertex predecessor;
	private int distance;
	
	public Vertex() {
		this.color = Color.UNKNOWN;
		this.distance = Integer.MAX_VALUE;
		this.predecessor = this;
	}
	
	public Vertex(Color color, Vertex predecessor, int distance) {
		this.color = color;
		this.predecessor = predecessor;
		this.distance = distance;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Vertex getPredecessor() {
		return predecessor;
	}

	public void setPredecessor(Vertex predecessor) {
		this.predecessor = predecessor;
	}

	public int getDistance() {
		return distance;
	}

	public void setDistance(int distance) {
		this.distance = distance;
	}
	
	
}
