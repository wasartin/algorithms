package main.java;

public class Endpoint {
	
	public static final int RIGHT_POINT = -1;
	public static final int LEFT_POINT = 1;
	public static final int NIL_POINT = 0;
	
	private int value;
	private int pValue;
	
	public Endpoint() {
	}
	public Endpoint(int value, int p) {
		this.value = value;
		switch(p) {
		case NIL_POINT:
			pValue = NIL_POINT;
			break;
		case RIGHT_POINT:
			pValue = RIGHT_POINT;
			break;
		case LEFT_POINT:
			pValue = LEFT_POINT;
			break;
		}
	}
	
	/**
	 *  Returns the endpoint value. For example if the Endpoint object
	 *  represents the left endpoint of the interval [1, 3], this would return 1.
	 * @return
	 */
	public int getValue() {
		return value;
	}
	public int getPValue() {
		return pValue;
	}
}
