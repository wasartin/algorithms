package main.java;

public class Node<Key extends Comparable<Key>, Value> {
	
	public static final int RED = 0;
	public static final int BLACK = 1;
	
	/**
	 * (I think) the keys are the endpoints. 
	 */
	private Key key; //this is 'e', 
	/**
	 * is the sum of the p-values of the nodes in 
	 * the subtree rooted at v (including v itself)
	 * that is, v.val = s(`v,rv ).
	 */
	private Value val; 
	private int p; //this is 'p(e)'
	private Endpoint e;
	private int color;
	
	private Node parent;
	private Node left;
	private Node right;
	
	public Node() {
		
	}
	
	public Node(Key key, Node parent) {
		this.parent = parent;
		this.key = key;
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
	public Key getKey() {
		return this.key;
	}
	
	public void setKey(Key key) {
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
		//TODO
		return -1;
	}
	
	/**
	 * Returns the maxval of the node as described in this assignment.
	 * Which is ->
	 * 	the max value obtained by the expression
	 * 	s(lv, i) for lv <= i <= rv.
	 * 
	 * 		Define s(i, j)
	 * 		s(i, j) = p(e1) + p(e2) + .... + p(en) #with n being the last node
	 *  	Any endpoint e, that maximizes s(l, i) is a point of maximum overlap 
	 *  		(the entire point of this project)
	 * 
	 * 
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
		int caseOne = this.left.getMaxVal();
		int caseTwo = this.left.getVal() + this.getP();
		int caseThree = caseTwo + this.right.getMaxVal();
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
		//TODO
		return null;
	}
	
	/**
	 * Returns an Endpoint object that represents emax. Calling this
	 * method on the root node will give the Endpoint object whose getValue() provides a
	 * point of maximum overlap.
	 * 
	 * Which is a reference to an endpoint em, where
	 * 	m is teh value of i that maximizes s(lv, i)
	 * 	over all i such that lv <= i <= rv.
	 * @return
	 */
	public Endpoint getEmax() {
		//TODO
		return null;
	}
	
	
	/**
	 * : Returns 0 if red. Returns 1 if black
	 * @return
	 */
	public int getColor() {
		//TODO
		return -1;
	}
	
	public void setColor(int color) {
		this.color = color;
	}
	
	/**
	 * Comparable
	 * positive int if current obj is greater than specific obj
	 * negative int if the current obj is less than specific obj
	 * zero if the current obj is equal to the specific obj
	 */
}
