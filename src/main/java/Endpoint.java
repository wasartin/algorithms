package main.java;

public class Endpoint {
	
	//TODO: How to handle left/right or nil node?
		//Idea 1: boolean for left and right
		//Idea 2: enums
	//Like isLeft or isRight? or just do an int like we do with colors for nodes OR do true/false
	
	public static final int RIGHT_POINT = -1;
	public static final int LEFT_POINT = 1;
	public static final int NIL_POINT = 0;
	
	private int value;
	private boolean left; //idea 1
	private int pValue;
	
	public Endpoint() {
	}
	
	//Idea 1
	/* 	
	public Endpoint(int value, boolean left) {
		this.value = value;
		this.left = left;
	}
	*/
	//Idea 2
	public Endpoint(int value, int p) {
		this.value = value;
		switch(p) {
		case NIL_POINT:
			pValue = 0;
			break;
		case RIGHT_POINT:
			pValue = -1;
			break;
		case LEFT_POINT:
			pValue = 1;
			break;
		}
	}
	
	//Idea 1 & 2?
	/*
	public Endpoint(int value, int p, boolean left) {
		this.value = value;
		switch(p) {
		case NIL_POINT:
			pValue = 0;
			left = false;
		case RIGHT_POINT:
			left = false;
			pValue = -1;
			break;
		case LEFT_POINT:
			left = true;
			pValue = 1;
			break;
		}
	}
	*/
	
	/**
	 *  Returns the endpoint value. For example if the Endpoint object
	 *  represents the left endpoint of the interval [1, 3], this would return 1.
	 * @return
	 */
	public int getValue() {
		return value;
	}
	
	//Idea 1
	/*
	public boolean isLeft() {
		return left;
	}
	*/
	
	//Idea 2
	public int getPValue() {
		return pValue;
	}
}
