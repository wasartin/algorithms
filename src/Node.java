
public class Node<E> {
	private E data;
	private Color color;
	private int distance;
	private Node<E> predecessor;
	
	public Node() {
		this.data = null;
		this.color = Color.WHITE;
		this.distance = 0;
		this.predecessor = null;
	}
	
	public Node(E data) {
		this.data = data;
		
	}
	
	public Node(E data, Color color, int distance, Node <E> predecessor) {
		super();
		this.data = data;
		this.color = color;
		this.distance = distance;
		this.predecessor = predecessor;
	}
	
	public E getData() {
		return data;
	}
	public void setData(E data) {
		this.data = data;
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	public int getDistance() {
		return distance;
	}
	public void setDistance(int distance) {
		this.distance = distance;
	}
	public Node<E> getPredecessor() {
		return predecessor;
	}
	public void setPredecessor(Node<E> predecessor) {
		this.predecessor = predecessor;
	}

}