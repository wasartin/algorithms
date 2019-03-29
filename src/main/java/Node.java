package main.java;

public class Node{
	
	public static final int RED = 0;
	public static final int BLACK = 1;

	private static final String FORMAT = "\n"
			+ "Color:%5s"
			+ "\n" 
			+ "KEY:   %s          Parent: %s" 
			+ "\n"
			+ "Value: %-8d ||  MaxValue: %d          LeftChild: %s"
			+ "\n"
			+ "Emax:  %s          RightChild: %s"
			+ "\n"
			+ "Height:%d, Size: %d";

	/**
	 * The tree is sorted by endpoints. the Key is the endpoint.
	 */
	private Endpoint key; //this is 'e', 
	private int p; //this is 'p(e)'
	private int color;
	private Node parent;
	private Node left;
	private Node right;
	
	private int height;
	private int size;
	private Endpoint eMax;
	public int val; 
	public int maxVal; 
	
	public Node() {
		color = RED; //all new nodes are red
		size = 1;
		p = maxVal = val = 0;
	}

	public Node(Endpoint e) {
		this(null, e, RED, 1);
		if(e.getPValue() == 0 && e.getValue() == -1) {//For nil node
			this.setColor(BLACK);
			this.setSize(0); 
			this.setHeight(0);
		}
	}
	
	public Node(Node parent, Endpoint e, int color, int size) {
		this.parent = parent;
		this.key = e;
		eMax = e;
		p = e.getPValue();
		this.color = color;
		this.size = size;
		maxVal = val = e.getPValue();
		//height = 1;//height of everyNode except the nil node is 1
		height = 1;
	}
	
	/** 
	 * @Required
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
	 * @Required
	 * Returns the left child.
	 * @return
	 */
	public Node getLeft() {
		return this.left;
	}
	
	public void setLeft(Node left) {
		this.left = left;
	}

	/**
	 * @Required
	 * Returns the right child.
	 * @return
	 */
	public Node getRight() {
		return this.right;
	}
	
	public void setRight(Node right) {
		this.right = right;
	}
	/**
	 * @Required for project
	 * TODO check if this is right. 
	 * i.e., do we just do int for this, or 
	 * stick with the generic
	 * Returns the endpoint value, which is an integer.
	 * @return 'e', which is the key
	 */
	public int getKey() {
		return this.key.getValue();
	}
	
	/** 
	 * @Required for project
	 * Returns the value of the function p based on this endpoint.
	 * @return
	 */
	public int getP() {
		return this.p;
	}
	
	/**@Required for project
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
	}
	
	public void setVal(int x) {
		this.val = x;
	}

	/** 
	 * @Required for project
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
	}
	
	public void setMaxVal(int max) {
		this.maxVal = max;
	}
	
	/** 
	 * @Required for project, needs to be O(1)
	 * Returns the Endpoint object that this node represents.
	 * @return
	 */
	public Endpoint getEndpoint() {
		return key;
	}
	
	/** @Required for project
	 * Returns an Endpoint object that represents emax. Calling this
	 * method on the root* node will give the Endpoint object whose getValue() provides a
	 * point of maximum overlap.
	 * 
	 * Which is a reference to an endpoint em, where
	 * 	m is the value of i that maximizes s(lv, i)
	 * 	over all i such that lv <= i <= rv.
	 * @return
	 */
	public Endpoint getEmax() { //the child that has the highest maxval
		return eMax;
	}

	public void setEmax(Endpoint e) {
		this.eMax = e;
	}
	
	/**
	 * @Required for project
	 * Returns 0 if red. Returns 1 if black
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
		return size;
	}
	
	public void setSize(int size) {
		this.size = size;
	}
	
	public int getHeight() {
		return height;
		
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	public int getNumOfNodes() {
		return getNumOfNodes(this);
	}
	
	private int getNumOfNodes(Node x) {
		if(x.getEndpoint().getPValue() == 0) {//base case
			return 1;
		}
		return 1 + getNumOfNodes(x.getLeft()) + getNumOfNodes(x.getRight());
	}
	
	@Override
	public boolean equals(Object other) {
		if(other instanceof Node){
			Node temp = (Node) other;
			if(temp.getEndpoint().equals(this.getEndpoint()) && (this.getColor() == temp.getColor())) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public String toString() {
		return (this.getEndpoint().getPValue() == 0 && this.getEndpoint().getValue() == -1)? "Nil Node" : "Key:" 
				+ this.getKey() + ", Color:" + ((this.getColor() == RED) ? "Red" : "Black");
	}
	
	public String structuredToString() {
		return String.format(FORMAT, ((this.getColor() == RED) ? "Red" : "Black"), this.getEndpoint().toString(), 
				this.getParent().toString(), this.getVal(), this.getMaxVal(), this.getLeft().toString(), 
				this.getEmax().toString(), this.getRight().toString(), this.getHeight(), this.getSize());
	}
	
	//A method just for testing
	public boolean isNilNode() {
		return (this.getEndpoint().getPValue() == 0 && this.getEndpoint().getValue() == -1) ? true : false;
	}
	
}