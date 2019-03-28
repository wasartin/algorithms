package main.java;

public class Node{
	
	public static final int RED = 0;
	public static final int BLACK = 1;
	
	/**
	 * The tree is sorted by endpoints. the Key is the endpoint.
	 */
	private int key; //this is 'e', 
	private int p; //this is 'p(e)'
	private Endpoint e;
	private int color;
	private Node parent;
	private Node left;
	private Node right;
	private Endpoint eMax;//TODO needs to be O(1)
	public int val; //TODO needs to be O(1)
	public int maxVal; //TODO needs to be O(1)
	
	private int size; //number of internal nodes of this subtree
	
	public Node() {
		//?
		color = RED; //all new nodes are red
		size = 1;
		maxVal = val = 0;
	}
	
	//Not sure how to handle the nil node yet, a -1 for key?
	public Node(Endpoint e) {
		this.e = e;
		this.key = e.getValue();
		p = e.getPValue();
		color = RED; //all new nodes are red, this is probably a bad way to do this.
		size = 1;
		val = e.getPValue();
		
	}
	
	public Node(Node Parent, Endpoint e, int color, int size) {
		this.parent = parent;
		this.e = e;
		this.key = e.getValue();
		p = e.getPValue();
		this.size = size;
	}
	
	/**
	 * Returns the parent of this node.
	 * @return
	 */
	public Node getParent() {
		return this.parent;
	}
	
	public void setParent(Node parent) {
		this.parent = parent;
	}
	/**
	 * Returns the left child.
	 * @return
	 */
	public Node getLeft() {
		return this.left;
	}
	
	public void setLeft(Node left) {
		this.left = left;
	}
	/**Returns the right child.
	 * 
	 * @return
	 */
	public Node getRight() {
		return this.right;
	}
	
	public void setRight(Node right) {
		this.right = right;
	}
	/**
	 * TODO check if this is right. 
	 * i.e., do we just do int for this, or 
	 * stick with the generic
	 * Returns the endpoint value, which is an integer.
	 * @return 'e', which is the key
	 */
	public int getKey() {
		return this.key;
	}
	
	public void setKey(int key) {
		this.key = key;
	}
	
	/** Returns the value of the function p based on this endpoint.
	 * 
	 * @return
	 */
	public int getP() {
		return this.p;
	}
	
	/**
	 * Returns the val of the node as described in this assignment.
	 * Which is->
	 * 	the sum of the p-values of the nodes in the subtree rooted
	 * 	at this node (including this node)
	 * v.val = s(lv, rv)
	 * 
	 *	v.val = (0 if this node == T.nil)
	 *			(v.left.val + v.p + v.right.val otherwise)
	 *
	 * @return
	 */
	public int getVal() {
		return val;
		//return getValue(this);
	}
	
	public void setVal(int x) {
		this.val = x;
	}
	
	/**TODO this is wrong, getValue needs to be O(1)
	 * Helper method for getVal(), recursive
	 * @param x
	 * @return
	 */
//	private int getValue(Node x) {
//		if(x.getP() == 0) {//NIL NODE, this is the base case
//			return x.getP();
//		}
//		return x.getP() + getValue(x.getLeft()) + getValue(x.getRight());
//	}
	
	/** TODO: This is wrong, maxVal needs to be found in O(1)
	 * Returns the maxval of the node as described in this assignment.
	 * Which is ->
	 * 	the max value obtained by the expression
	 * 	s(lv, i) for lv <= i <= rv.
	 * 
	 * 		Define s(i, j)
	 * 		s(i, j) = p(e1) + p(e2) + .... + p(en) #with n being the last node
	 *  	Any endpoint e, that maximizes s(l, i) is a point of maximum overlap 
	 *  		(the entire point of this project)
	 * Two possibilities ,
	 * 		1. If this node == T.nil, then v.maxVal = 0;
	 * 		2. this possibility has three vases
	 * 			a.) The max is in This Node's left subtree
	 * 			b.) the max is in This Node
	 * 			c.) The max is in This Node's right subtree
	 * Which leads to this expression
	 * 	thisNode.maxVal = max(this.left.maxval, 					#case 1
	 * 						this.left.val + v.p, 					#case 2
	 * 						this.left.val + v.p + v.right.maxval) 	#case 3	
	 * 		
	 * @return
	 */
	public int getMaxVal() { 
		return maxVal;
		
//		int caseOne = getMaxVal(this.getLeft());
//		int caseTwo = this.getLeft().getVal() + this.getP();
//		int caseThree = caseTwo + getMaxVal(this.getRight()); 
//		return max(caseOne, caseTwo, caseThree);
	}
	
	public void setMaxVal(int max) {
		this.maxVal = max;
	}
	
	public int getMaxVal(Node x) {
		if(x.getP() == 0) {
			return 0;
		}
		int caseOne = getMaxVal(x.getLeft());
		int caseTwo = x.getLeft().getVal() + x.getP();
		int caseThree = caseTwo + getMaxVal(x.getRight());
		return max(caseOne, caseTwo, caseThree);
	}
	
	private int max(int one, int two, int three) {
		int temp = Math.max(one, two);
		return Math.max(temp, three);
	}
	
	/**
	 * Returns the Endpoint object that this node represents.
	 * @return
	 */
	public Endpoint getEndpoint() {
		return e;
	}
	
	/**
	 * Returns an Endpoint object that represents emax. Calling this
	 * method on the root* node will give the Endpoint object whose getValue() provides a
	 * point of maximum overlap.
	 * 
	 * Which is a reference to an endpoint em, where
	 * 	m is the value of i that maximizes s(lv, i)
	 * 	over all i such that lv <= i <= rv.
	 * @return
	 */
	//This value is dynamic, which is why it is not an instance variable
	public Endpoint getEmax() { //the child that has the highest maxval
		return eMax;
		//return getEmax(this);
	}
	
	private Endpoint getEmax(Node x) {
		if(x.getP() == 0) { //base case, at the NIL Node
			return new Endpoint(0, 0);
		}
		Endpoint currMax = x.getEndpoint();
		if(x.getLeft().getMaxVal() >= x.getMaxVal()) {
			getEmax(x.getLeft());
		}
		else if(x.getRight().getMaxVal() >= x.getMaxVal()) {
			getEmax(x.getRight());
		}
		else {
			return currMax;
		}
		return currMax;
	}
	
	public void setEmax(Endpoint e) {
		this.eMax = e;
	}
	
	
	/**
	 * : Returns 0 if red. Returns 1 if black
	 * @return
	 */
	public int getColor() {
		return color;
	}
	
	public void setColor(int color) {
		this.color = color;
	}
	
	/**
	 * Helper method that returns the number of internal nodes
	 * @return
	 */
	public int getSize() {
		return getNumOfInternalNodes(this);
	}
	
	public void setSize(int size) {
		this.size = size;
	}
	
	/**
	 * TODO: Probably delete, b/c I think get size needs to be a constant.
	 * @param x
	 * @return
	 */
	private int getNumOfInternalNodes(Node x) {
		if(x.getEndpoint().getPValue() == 0) {//base case
			return 1;
		}
		return 1 + getNumOfInternalNodes(x.getLeft()) + getNumOfInternalNodes(x.getRight()); //this node, plus all of its left and right children
	}
}
