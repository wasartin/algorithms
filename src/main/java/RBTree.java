package main.java;

import java.util.ArrayList;
import java.util.Stack;

public class RBTree{
	public static final int RED = 0;
	public static final int BLACK = 1;
	
	private Node root;
	private Node nil;

	public RBTree() {
		nil = new Node(new Endpoint(-1, 0));
		root = nil;
	}
	
	/**
	 * Returns the root node.
	 * @return
	 */
	public Node getRoot() {
		return root;
	}
	
	public void setRoot(Node root) {
		this.root = root;
	}
	
	/**Returns the nil node.
	 * Note. A red-black tree T must contain exactly one instance of T.nil.
	 * @return
	 */
	public Node getNILNode() {
		return nil;
	}
	
	/**
	 * Returns the number of internal nodes in the tree.
	 * @return
	 */
	public int getSize() {
		return root.getSize();
	}
	
	/**
	 *  Returns the height of the tree.
	 *  The height is the maximum number of edges from the root to its descendant leaves. 
	 *  Do not forget the sentinal Node in a RBTree
	 * @return
	 */
	public int getHeight() {
		return getHeight(this.root);
	}
	
	private int getHeight(Node x) {
		if(x.equals(this.nil)) {
			return -1;
		}
		return Math.max(getHeight(x.getLeft()), getHeight(x.getRight())) + 1; //Maximum descendent leaves + 1 for the nil node.
	}
	
	public int getBlackHeight() {
		return getBlackHeight(this.root);
	}
	
	public int getBlackHeight(Node x) {
		if(x.equals(this.nil)) {
			return 1;
		}
		if(x.getColor() == BLACK){
			return 1 + Math.max(getBlackHeight(x.getLeft()), getBlackHeight(x.getRight())); 
		}
		return Math.max(getBlackHeight(x.getLeft()), getBlackHeight(x.getRight())); 

	}

	//Additional RedBlackTree methods from CLRS
	
	/** From CLRS
	 * Private helper method. Not sure if we need this for the 
	 * project, but it is a method for a RBTree so I made it.
	 * @param x
	 */
	public void RBInsert(Node z) {
		Node y = this.nil;
		Node x = this.root;
		while(!x.equals(this.nil)) {
			y = x;
			if(z.getKey() < x.getKey()) {
				x = x.getLeft();
			}
			else {
				x = x.getRight();
			}
		}
		z.setParent(y);
		if(y.equals(this.nil)) {
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
		z.setColor(RED);
		RBInsertFixup(z);
	}
	
	/** From CLRS
	 * Private helper method. Not sure if we need this for the 
	 * project, but it is a method for a RBTree so I made it.
	 * @param x
	 */
	private void RBInsertFixup(Node z) {
		Node y = null;
		while(z.getParent().getColor() == RED) {
			if(z.getParent().equals(z.getParent().getParent().getLeft())) {
				y = z.getParent().getParent().getRight();	
				if(y.getColor() == RED) { 							//Case 1
					z.getParent().setColor(BLACK);
					y.setColor(BLACK);
					z.getParent().getParent().setColor(RED);
					z = z.getParent().getParent();
				}
				else {
					if(z.equals(z.getParent().getRight())) { 		//case 2
						z = z.getParent();
						leftRotate(z);
					}
					z.getParent().setColor(BLACK);					//case 3
					z.getParent().getParent().setColor(RED);
					rightRotate(z.getParent().getParent());
				}
			}
			else {													//Same as previous if statement, but left and right are flipped
				y = z.getParent().getParent().getLeft();
				if(y.getColor() == RED) { 						
					z.getParent().setColor(BLACK);
					y.setColor(BLACK);
					z.getParent().getParent().setColor(RED);
					z = z.getParent().getParent();
				}
				else {
					if(z.equals(z.getParent().getLeft())) {
						z = z.getParent();
						rightRotate(z);
					}
					z.getParent().setColor(BLACK);
					z.getParent().getParent().setColor(RED);
					leftRotate(z.getParent().getParent());
				}
			}
		}
		this.root.setColor(BLACK);
	}
	
	/** From CLRS
	 * Private helper method. Not sure if we need this for the 
	 * project, but it is a method for a RBTree so I made it.
	 * @param x
	 */
	private void leftRotate(Node x) {
		Node y = x.getRight();
		x.setRight(y.getLeft());
		if(!y.getLeft().equals(nil)) {
			y.getLeft().setParent(x);
		}
		y.setParent(x.getParent());
		if(x.getParent().equals(nil)) {
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
		if(!y.getRight().equals(nil)) {
			y.getRight().setParent(x);
		}
		y.setParent(x.getParent());
		if(x.getParent().equals(nil)) {
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
		updateNodesInfo(x, y); //TODO refactor this shit
	}
	
	//helper method, public for testing
	public void updateNodesInfo(Node x, Node y) {
		updateNodeSize(x, y); //Probably not needed
		updateNodeVal(x, y); //All Good
		updateNodeMaxVal(x, y); //TODO
		updateEMax(x, y); // TODO
	}
	
	//helper method, public for testing
	public void updateNodeSize(Node x, Node y) {
		y.setSize(x.getSize());
		x.setSize(x.getLeft().getSize() + x.getRight().getSize() + 1);
	}
	
	public void updateNodeMaxVal(Node x, Node y) {
		updateNodeMaxVal(x);
		updateNodeMaxVal(y);
	}
	
	public void updateNodeMaxVal(Node x) {
		int caseOne = x.getLeft().getMaxVal(); //rec?
		int caseTwo = x.getLeft().getVal() + x.getP();
		int caseThree = caseTwo + x.getRight().getMaxVal(); //rec?
		x.setMaxVal(max(caseOne, caseTwo, caseThree));
	}
	
	private void updateNodeVal(Node x, Node y) {
		updateNodeVal(x);
		updateNodeVal(y);
	}
	
	private void updateNodeVal(Node input) {
		input.setVal(getNodeVal(input));
	}
	
	//There is just no way this is O(log n)
	private int getNodeVal(Node input) {
		if(input.equals(this.nil)) {
			return input.getP();
		}
		input.setVal(getNodeVal(input.getLeft()) + input.getP() + getNodeVal(input.getRight()));
		return input.getVal();
	}
	
	private int max(int one, int two, int three) {
		int temp = Math.max(one, two);
		return Math.max(temp, three);
	}
	
	//TODO
	public void updateEMax(Node x, Node y) {
		
	}
	
	/**
	 * From CLRS
	 * @param u
	 * @param v
	 */
	private void RBTransplant(Node u, Node v) {
		if(u.getParent().equals(this.nil)) {
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
	
	/**
	 * From CLRS
	 * @param z
	 */
	private void RBDelete(Node z) {
		Node y = z;
		Node x = this.nil;
		int yOriginalColor = y.getColor();
		if(z.getLeft().equals(this.nil)) {
			x = z.getRight();
			RBTransplant(z, z.getRight());
		}
		else if(z.getRight().equals(this.nil)) {
			x = z.getLeft();
			RBTransplant(z, z.getLeft());
		}
		else {
			y = minimum(z.getRight());
			yOriginalColor  = y.getColor();
			x = y.getRight();
			if(y.getParent().equals(z)) {
				x.setParent(y);
			}
			else {
				RBTransplant(y, y.getRight());
				y.setRight(z.getRight());
				y.getRight().setParent(y);
			}
			RBTransplant(z, y);
			y.setLeft(z.getLeft());
			y.getLeft().setParent(y);
			y.setColor(z.getColor());
		}
		if(yOriginalColor == BLACK) {
			RBDeleteFixUp(x);
		}
	}
	
	private void RBDeleteFixUp(Node x) {
		while(!(x.equals(this.root)) && x.getColor() == BLACK){
			if(x.equals(x.getParent().getLeft())) {
				Node w = x.getParent().getRight();
				if(w.getColor() == RED) { 					//Case 1
					w.setColor(BLACK);
					x.getParent().setColor(RED);
					leftRotate(x.getParent());
					w = x.getParent().getRight();
				}
				if(w.getLeft().getColor() == BLACK && w.getRight().getColor() == BLACK) { // Case 2
					w.setColor(RED);						//Case 2
					x = x.getParent();
				}
				else { //Case 3 & 4
					if(w.getRight().getColor() == BLACK) {
						w.getLeft().setColor(BLACK);		//Case 3
						w.setColor(RED);
						rightRotate(w);
						w = x.getParent().getRight();
					}
					w.setColor(x.getParent().getColor());	//Case 4
					x.getParent().setColor(BLACK);
					w.getRight().setColor(BLACK);
					leftRotate(x.getParent());
					x = this.root;
				}
			}
			else { //same as then clause with right and left changed
				Node w = x.getParent().getLeft();	
				if(w.getColor() == RED) { 					//Case 1
					w.setColor(BLACK);
					x.getParent().setColor(RED);
					rightRotate(x.getParent());
					w = x.getParent().getLeft();
				}
				if(w.getRight().getColor() == BLACK && w.getLeft().getColor() == BLACK) { 
					w.setColor(RED); 						// Case 2
					x = x.getParent();
				}
				else { //Case 3 & 4
					if(w.getLeft().getColor() == BLACK) {
						w.getRight().setColor(BLACK);		//Case 3
						w.setColor(RED);
						leftRotate(w);
						w = x.getParent().getLeft();
					}
					w.setColor(x.getParent().getColor());	//Case 4
					x.getParent().setColor(BLACK);
					w.getLeft().setColor(BLACK);
					rightRotate(x.getParent());
					x = this.root; //This is probably wrong b/c each node
						//is it's own subtree.
				}
			}
		}
	}
	
	//BST Methods
	
	/** From CLRS
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
		if(!(x.getRight().equals(this.nil))) {
			return maximum(x.getLeft());
		}
		Node y = x.getParent();
		while(!(y.equals(this.nil)) && x.equals(y.getLeft())){
			x = y;
			y = y.getParent();
		}
		return y;
	}
}