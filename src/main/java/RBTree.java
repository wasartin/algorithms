package main.java;

public class RBTree {
	
	private Node root;
	private Node nil;

	public RBTree() {
		
	}
	
	/**
	 * Returns the root node.
	 * @return
	 */
	public Node getRoot() {
		//TODO
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
		//TODO
		return null;
	}
	
	/**
	 * Returns the number of internal nodes in the tree.
	 * @return
	 */
	public int getSize() {
		//TODO
		return -1;
	}
	
	/**
	 *  Returns the height of the tree
	 * @return
	 */
	public int getHeight() {
		//TODO
		return -1;
	}
	
	/**
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
	}
	
	/**
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
	}
	
	private void RBInsert(Node z) {
		Node y = nil;
		Node x = root;
		while(!x.equals(nil)) {
			y = x;
			if(z.getKey().compareTo(x.getKey()) < 0) {
				x = x.getLeft();
			}
			else {
				x = x.getRight();
			}
		}
		z.setParent(y);
		if(y.equals(nil)) {
			root = z;
		}
		else if(z.getKey().compareTo(y.getKey()) < 0) {
			y.setLeft(z);
		}
		else {
			y.setRight(z);
		}
		z.setLeft(nil);
		z.setRight(nil);
		z.setColor(0);
		RBInsertFixup(z);
	}
	
	private void RBInsertFixup(Node z) {
		//TODO
	}
}
