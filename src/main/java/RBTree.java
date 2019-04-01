package main.java;

import java.util.ArrayList;
import java.util.Stack;

/**
 * 
 * @author Will Sartin & Josh Ramon
 *
 */
public class RBTree{
	
	private Node root;
	//I think it makes sense to make this static. That way it's only made once, and it always exists. 
	//even w/o a tree being made
	private static Node nil = new Node(new Endpoint(0, Position.NIL));

	/**
	 * @Required
	 */
	public RBTree() {
		root = nil;
	}
	
	/**
	 * @Required
	 * Returns the root node.
	 * @return
	 */
	public Node getRoot() {
		return root;
	}
	
	public void setRoot(Node root) {
		this.root = root;
	}
	
	/**
	 * @Required
	 * Returns the nil node.
	 * Note. A red-black tree T must contain exactly one instance of T.nil.
	 * @return
	 */
	public static Node getNILNode() { return nil; }
	
	/** 
	 * @Required
	 * @Runtime O(1)
	 * Returns the number of internal nodes in the tree.
	 * @return
	 */
	public int getSize() {
		return root.getSize();
	}
	
	/** @Required - 
	 * 	@Runtime O(1)
	 *  Returns the height of the tree.
	 *  The height is the maximum number of edges from the root to its descendant leaves. 
	 *  Do not forget the sentinal Node in a RBTree
	 * @return
	 */
	public int getHeight() {
		return this.getRoot().getHeight();
	}
	
	public int getBlackHeight() {
		return getBlackHeight(this.root);
	}
	
	public int getBlackHeight(Node x) {
		if(x.equals(this.nil)) {
			return 1;
		}
		if(x.getColor() == Color.BLACK.ordinal()){
			return 1 + Math.max(getBlackHeight(x.getLeft()), getBlackHeight(x.getRight())); 
		}
		return Math.max(getBlackHeight(x.getLeft()), getBlackHeight(x.getRight())); 

	}
	
	/** From CLRS
	 * @param x
	 */
	public void RBInsert(Node z) {
		Node y = this.nil;
		Node x = this.root;
		while(!x.isNilNode()) {
			x.setSize(x.getSize() + 1); 					//1st phase update (Mentioned in CLRS 14.1)
			x.setHeight(x.getHeight() + 1);					
			y = x;
			if(z.getKey() < x.getKey()) {
				x = x.getLeft();
			}
			else {
				x = x.getRight();
			}
		}
		z.setParent(y);
		if(y.isNilNode()) {
			this.root = z;
		}
		else if(z.getKey() < y.getKey()) {
			y.setLeft(z);
		}
		else {
			y.setRight(z);
		}
		z.setLeft(this.nil);
		z.setRight(this.nil);
		updateNodeInfo(z);
		RBInsertFixup(z);
		updateNodeInfo(z);
		//z.setEmax(findEmax(z));
		Node a = y;
		while(!a.isNilNode()) { 
			updateNodeInfo(a);
			a = a.getParent();

		}
	}
	
	/** From CLRS
	 * @param x
	 */
	private void RBInsertFixup(Node z) {
		Node y = null;
		while(z.getParent().getColor() == Color.RED.ordinal()) {
			if(z.getParent().equals(z.getParent().getParent().getLeft())) {
				y = z.getParent().getParent().getRight();	
				if(y.getColor() == Color.RED.ordinal()) { 							//Case 1
					z.getParent().setColor(Color.BLACK.ordinal());
					y.setColor(Color.BLACK);
					z.getParent().getParent().setColor(Color.RED);
					z = z.getParent().getParent();
				}
				else {
					if(z.equals(z.getParent().getRight())) { 		//case 2
						z = z.getParent();
						leftRotate(z);
					}
					z.getParent().setColor(Color.BLACK);					//case 3
					z.getParent().getParent().setColor(Color.RED);
					rightRotate(z.getParent().getParent());
				}
			}
			else {													//Same as previous if statement, but left and right are flipped
				y = z.getParent().getParent().getLeft();
				if(y.getColor() == Color.RED.ordinal()) { 						
					z.getParent().setColor(Color.BLACK);
					y.setColor(Color.BLACK);
					z.getParent().getParent().setColor(Color.RED);
					z = z.getParent().getParent();
				}
				else {
					if(z.equals(z.getParent().getLeft())) {
						z = z.getParent();
						rightRotate(z);
					}
					z.getParent().setColor(Color.BLACK);
					z.getParent().getParent().setColor(Color.RED);
					leftRotate(z.getParent().getParent());
				}
			}
		}
		this.root.setColor(Color.BLACK);
	}
	
	/** From CLRS
	 * @param x
	 */
	private void leftRotate(Node x) {
		Node y = x.getRight();
		x.setRight(y.getLeft());
		if(!y.getLeft().isNilNode()) {
			y.getLeft().setParent(x);
		}
		y.setParent(x.getParent());
		if(x.getParent().isNilNode()) {
			root = y;
		}
		else if(x.equals(x.getParent().getLeft())) {
			x.getParent().setLeft(y);
		}
		else {
			x.getParent().setRight(y);
		}
		y.setLeft(x);
		x.setParent(y);

		updateNodesInfo(x, y);
	}
	
	/** From CLRS
	 * Private helper method. Not sure if we need this for the 
	 * project, but it is a method for a RBTree so I made it.
	 * @param x
	 */
	private void rightRotate(Node x) {
		Node y = x.getLeft();
		x.setLeft(y.getRight());
		if(!y.getRight().isNilNode()) {
			y.getRight().setParent(x);
		}
		y.setParent(x.getParent());
		if(x.getParent().isNilNode()) {
			root = y;
		}
		else if(x.equals(x.getParent().getRight())) {
			x.getParent().setRight(y);
		}
		else {
			x.getParent().setLeft(y);
		}
		y.setRight(x);
		x.setParent(y);

		updateNodesInfo(x, y);
	}

	private void updateNodesInfo(Node x, Node y) {
		y.setSize(x.getSize());											//second phase update (Mentioned in CLRS 14.1)
		x.setSize(x.getLeft().getSize() + x.getRight().getSize() + 1);	//second phase update (Mentioned in CLRS 14.1)
		updateNodeInfo(x);
		updateNodeInfo(y);
	}
	
	private void updateNodeInfo(Node v) {//now if a node is changing, then every parent should be changed.
		if(!v.isNilNode()) {
			v.setSize(v.getLeft().getSize() + v.getRight().getSize() + 1);
			v.setHeight(Math.max(v.getLeft().getHeight(), v.getRight().getHeight()) + 1);
			v.setVal(getNodeVal_Iter(v));
			v.setMaxVal(getNodeMaxVal(v));
			v.setEmax(findEmax(v)); //change
			//updateNodeInfo(v.getParent());
		}

	}
	
	//TODO: left off here
	public int getNodeMaxVal(Node x) {
		if(x.isNilNode()) {//base case
			return x.getP(); //which is 0
		}
		int caseOne = getNodeMaxVal(x.getLeft());
		int caseTwo = x.getLeft().getVal() + x.getP();
		int caseThree = caseTwo + getNodeMaxVal(x.getRight());
		x.setMaxVal(max(caseOne, caseTwo, caseThree));
		return x.getMaxVal();
	}

	private int getNodeVal_Iter(Node input) {
		if(input.isNilNode()) {
			return 0;
		}
		return input.getLeft().getVal() + input.getP() + input.getRight().getVal();
	}
	
	private int getNodeVal_Rec(Node input) {
		if(input.isNilNode()) {
			return input.getP();
		}
		input.setVal(getNodeVal_Rec(input.getLeft()) + input.getP() + getNodeVal_Rec(input.getRight()));
		return input.getVal();
	}
	
	private int max(int one, int two, int three) {
		int temp = Math.max(one, two);
		return Math.max(temp, three);
	}

//	public Endpoint findEMax_Rec(Node v) {
//		if(v.isNilNode()) { //base case
//			return this.nil.getEndpoint();
//		}
//		int caseOne = v.getLeft().getVal();
//		int caseTwo = v.getLeft().getVal() + v.getP();
//		int caseThree = caseTwo + v.getRight().getVal();
//		
//		int maxNum = max(caseOne, caseTwo, caseThree);
//		if(maxNum == caseOne) {
//			v.getLeft().setEmax(findEMax_Rec(v.getLeft()));
//			return v.getLeft().getEmax();
//		}
//		if(maxNum == caseTwo) {
//			v.setEmax(v.getEndpoint());
//			return v.getEndpoint();
//		}
//		else {
//			v.getRight().setEmax(findEMax_Rec(v.getRight()));
//			return v.getRight().getEmax();
//		}
//	}
	
	public Endpoint findEmax(Node v) {
		//either left node, right node, or self
		Endpoint result = new Endpoint();
		
		if(v.getLeft().isNilNode() && v.getRight().isNilNode()) {
			if(v.getP() == Position.RIGHT.value) {
				result = nil.getEndpoint();
				return result;
			}
			else {
				result = v.getEndpoint();
				return result;
			}
		}
		int caseOne = v.getLeft().getMaxVal(); 			// Max points is i the Left subtree
		int caseTwo = v.getLeft().getVal() + v.getP(); 	//This is the max point
		int caseThree = caseTwo + v.getRight().getMaxVal();		//Max point is in right subtree	 .... Error here... should be right
		int max = max(caseOne, caseTwo, caseThree);
		//cheat I know
		if(max == caseOne) {
			result = v.getLeft().getEmax();
		}
		if(max == caseTwo) {
			result = v.getEmax();//new change
		}
		else if(max == caseThree) {
			result = v.getRight().getEmax();
		}
		return result;
	}
	
	/**
	 * From CLRS
	 * @param u
	 * @param v
	 */
	private void RBTransplant(Node u, Node v) {
		if(u.getParent().isNilNode()) {
			this.root = v;
		}
		else if(u.equals(u.getParent().getLeft())) {
			u.getParent().setLeft(v);
		}
		else {
			u.getParent().setRight(v);
		}
		v.setParent(u.getParent());
	}
	
	public void RBDelete(Node z) {
		Node y = z;
		Node x = this.nil;
		int yOriginalColor = y.getColor();
		if(z.getLeft().isNilNode()) {			//Case 1 (delete right child)
			x = z.getRight();
			RBTransplant(z, z.getRight());
		}
		else if(z.getRight().isNilNode()) {		// case 2 (just delete Left Child)
			x = z.getLeft();
			RBTransplant(z, z.getLeft());
		}
		else {									//Case 3 and 4
			y = minimum(z.getRight());
			yOriginalColor  = y.getColor();
			x = y.getRight();
			if(y.getParent().equals(z)) {		//case 4 only
				x.setParent(y);
			}
			else {								//case 4
				RBTransplant(y, y.getRight());
				y.setRight(z.getRight());
				y.getRight().setParent(y);
			}
			RBTransplant(z, y);					//case 3 & 4 (in case 3 & 4, the size will change)
			y.setLeft(z.getLeft());
			y.getLeft().setParent(y);
			y.setColor(z.getColor());
			//update size here?
//			y.setSize(Math.max(y.getLeft().getSize(), y.getRight().getSize()) + 1);
			Node percolateUp = y;
			while(!percolateUp.isNilNode()) {
				percolateUp.setSize(percolateUp.getLeft().getSize() + percolateUp.getRight().getSize() + 1);
				percolateUp = percolateUp.getParent();
			}
		}
		Node percolateUp = x;
		while(!percolateUp.isNilNode()) {
			percolateUp.setSize(percolateUp.getLeft().getSize() + percolateUp.getRight().getSize() + 1);
			percolateUp = percolateUp.getParent();
		}
		if(yOriginalColor == Color.BLACK.ordinal()) {
			RBDeleteFixUp(x);
		}
	}
	
	private void RBDeleteFixUp(Node x) {
		while(!(x.equals(this.root)) && x.getColor() == Color.BLACK.ordinal()){
			if(x.equals(x.getParent().getLeft())) {
				Node w = x.getParent().getRight();
				if(w.getColor() == Color.RED.ordinal()) { 					//Case 1 (x's silbing w is RED)
					w.setColor(Color.BLACK);
					x.getParent().setColor(Color.RED);
					leftRotate(x.getParent());
					w = x.getParent().getRight();
				}
				if(w.getLeft().getColor() == Color.BLACK.ordinal() && w.getRight().getColor() == Color.BLACK.ordinal()) { // Case 2
					w.setColor(Color.RED.ordinal());						//Case 2
					x = x.getParent();
				}
				else { //Case 3 & 4
					if(w.getRight().getColor() == Color.BLACK.ordinal()) {
						w.getLeft().setColor(Color.BLACK);		//Case 3
						w.setColor(Color.RED);
						rightRotate(w);
						w = x.getParent().getRight();
					}
					w.setColor(x.getParent().getColor());	//Case 4
					x.getParent().setColor(Color.BLACK);
					w.getRight().setColor(Color.BLACK);
					leftRotate(x.getParent());
					x = this.root;
				}
			}
			else { //same as then clause with right and left changed
				Node w = x.getParent().getLeft();	
				if(w.getColor() == Color.RED.ordinal()) { 					//Case 1
					w.setColor(Color.BLACK);
					x.getParent().setColor(Color.RED);
					rightRotate(x.getParent());
					w = x.getParent().getLeft();
				}
				if(w.getRight().getColor() == Color.BLACK.ordinal() && w.getLeft().getColor() == Color.BLACK.ordinal()) { 
					w.setColor(Color.RED); 						// Case 2
					x = x.getParent();
				}
				else { //Case 3 & 4
					if(w.getLeft().getColor() == Color.BLACK.ordinal()) {
						w.getRight().setColor(Color.BLACK);		//Case 3
						w.setColor(Color.RED);
						leftRotate(w);
						w = x.getParent().getLeft();
					}
					w.setColor(x.getParent().getColor());	//Case 4
					x.getParent().setColor(Color.BLACK);
					w.getLeft().setColor(Color.BLACK);
					rightRotate(x.getParent());
					x = this.root;
				}
			}
		}
		x.setColor(Color.BLACK);
	}

	
	//BST Methods
	/** From CLRS We might need this for delete by ID
	 * Return a reference to the node y in the subtree rooted at x
	 * such that y.key == k. Return null if no such y exists.
	 */
	private Node searchIterative(Node x, int k) {
		Node currNode = this.root;
		while(!currNode.equals(this.nil)) {
			if(currNode.getKey() == k) {
				return currNode;
			}
			else if(currNode.getKey() < k) {
				currNode = currNode.getLeft();
			}
			else {
				currNode = currNode.getRight();
			}
		}
		return this.nil;
	}
	
	/** From CLRS
	 * Return a reference to the node in the subtree rooted at x
	 * that contains the minimum key-value
	 */
	public Node minimum(Node x) {
		while(!x.getLeft().equals(this.nil)) {
			x = x.getLeft();
		}
		return x;
	}
	
	/** From CLRS
	 * Return a reference to the node in the subtree rooted at x
	 * that contains the maximum key-value.
	 * @param x
	 * @return
	 */
	public Node maximum(Node x) {
		while(!x.getRight().equals(this.nil)) {
			x = x.getRight();
		}
		return x;
	}
	
	/** From CLRS
	 *  Return a reference to node containing the key-value
	 *  immediately following x.key in the BST that contains x.
	 *  Return null if x has the largest key in the tree.
	 * @param x
	 * @return
	 */
	public Node successor(Node x) {
		if(!x.getRight().equals(this.nil)) {
			return minimum(x.getRight());
		}
		Node y = x.getParent();
		while(!(y.equals(this.nil)) && x.equals(y.getRight())){
			x = y;
			y = y.getParent();
		}
		return y;
	}
	
	public ArrayList<Node> getNodesInOrder(Node x){
		ArrayList<Node> toReturn = new ArrayList<>();
		Stack<Node> s = new Stack<>();
		Node curr = x;
		while(!s.isEmpty() || !curr.equals(nil)) {
			while(!curr.equals(nil)) {
				s.push(curr);
				curr = curr.getLeft();
			}
			curr = s.pop();
			toReturn.add(curr);
			curr = curr.getRight();
		}
		return toReturn;
	}
	
	/** From CLRS
	 * Return a reference to node containing the key-value
	 * immediately preceding x.key in the BST that contains x.
	 * Return null if x has the smallest key in the tree.
	 * @param x
	 * @return
	 */
	public Node predecessor(Node x) {
		if(!(x.getRight().isNilNode())) {
			return maximum(x.getLeft());
		}
		Node y = x.getParent();
		while(!(y.isNilNode()) && x.equals(y.getLeft())){
			x = y;
			y = y.getParent();
		}
		return y;
	}
	
	public void printTree() {
		print("", this.getRoot());
	}
	
	private void print(String prefix, Node n) {
	    if (!n.isNilNode()) {	
	        print(prefix + "     ", n.getRight());
	        System.out.println (prefix + ("|-- ") + n.moreInfoString());
	        print(prefix + "     ", n.getLeft());
	    }
	}
	
	//TODO
	public boolean propertyFive() {
		
		return false;
	}

}