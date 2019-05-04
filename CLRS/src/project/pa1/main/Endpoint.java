package project.pa1.main;

/**
 * 
 * @author Will Sartin
 *
 */
public class Endpoint {
	
//	private static final String FORMAT = "[Point:%3d || %9s]";
	private int value;
	private Position pValue;
	
	public Endpoint() {
	}
	
	
	public Endpoint(int value, int p) {
		this(value, Position.getValueOf(p));
	}
	
	public Endpoint(int value, Position p) {
		if(p.equals(Position.ERROR)) {
			throw new IllegalArgumentException("The value of p can only be -1 (right end point), 1 (left endpoint), and 0 (nil node)");
		}
		this.value = value;
		this.pValue = p;
	}
	
	/**
	 *  Returns the endpoint value. For example if the Endpoint object
	 *  represents the left endpoint of the interval [1, 3], this would return 1.
	 * @return
	 */
	public int getValue() {
		return value;
	}
	
	public Position getPValue() {
		return pValue;
	}
	
	@Override
	public boolean equals(Object other) {
		if(other instanceof Endpoint) {
			Endpoint temp = (Endpoint) other;
			if(this.getPValue() == temp.getPValue() && this.getValue() == temp.getValue()) {
				return true;
			}
		}
		return false;
	}
	
//	@Override
//	public String toString() {
//		return String.format(FORMAT, this.getValue(), ((this.getPValue() == Position.LEFT)? "Left(+1)" : "Right(-1)"));
//	}
}
