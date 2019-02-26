package redBlackTree;

public class RedBlackTree<Key extends Comparable<Key>, Value>{
	
	public static final int RED = 0;
	public static final int BLACK = 1;
	
	protected RedBlackNode root = null;
	protected RedBlackNode nil;
	
	public int internalNodesCount = 0;
	
	public RedBlackTree(){
		nil = new RedBlackNode();
	}
	
	public class RedBlackNode{
		public RedBlackNode left;
		public RedBlackNode right;
		public RedBlackNode parent;
		public Key key;
		int color;
		
		//will probably end up deleting one of these constructors, just screwing around'
		public RedBlackNode(Key key) {
			this.key = key;
			left = nil;
			right = nil;
			color = RED;
		}
		public RedBlackNode() {
			color = BLACK;
			left = right = parent = nil;
		}

		public RedBlackNode getLeft() {
			return left;
		}

		public void setLeft(RedBlackNode left) {
			this.left = left;
		}

		public RedBlackNode getRight() {
			return right;
		}

		public void setRight(RedBlackNode right) {
			this.right = right;
		}

		public RedBlackNode getParent() {
			return parent;
		}

		public void setParent(RedBlackNode parent) {
			this.parent = parent;
		}

		public Key getKey() {
			return key;
		}

		public void setKey(Key key) {
			this.key = key;
		}

		public int getColor() {
			return color;
		}

		public void setColor(int color) {
			this.color = color;
		}
	}
	
	private void leftRotate(RedBlackNode x) {
		RedBlackNode y = x.getRight();
		x.setRight(y.getLeft()); // turns y's left subtree into x's right subtree
		if(y.getLeft() == this.nil) {
			y.getLeft().setParent(x);
		}
		y.setParent(x.getParent());
		if(x.getParent() == this.nil) {
			this.root = y;
		}
		else if(x == x.getParent().getLeft()) {
			x.getParent().setLeft(y);
		}
		else {
			x.getParent().setLeft(y);
		}
		y.setLeft(x);
		x.setParent(y);
	}
	
	private void rightRotate(RedBlackNode x) {
		RedBlackNode y = x.getLeft();
		x.setLeft(y.getRight());//turns x's left subtree into y's right
		if(y.getRight() == this.nil) {
			y.getRight().setParent(x);
		}
		y.setParent(x.getParent());
		if(x.getParent() == this.nil) {
			this.root = y;
		}
		else if(x == x.getParent().getRight()) {
			x.getParent().setRight(y);
		}
		else {
			x.getParent().setRight(y);
		}
		y.setRight(x);
		x.setParent(y);
	}
	
	public void add(Key input) {
		RedBlackNode temp = new RedBlackNode(input);
		insert(temp);
	}
	
	public void insert(RedBlackNode z) {
		RedBlackNode y = this.nil;
		RedBlackNode x = this.root;
		while(x != this.nil) {
			y = x;
			if(z.getKey().compareTo(x.getKey()) < 0) { //a negative integer as this object is less than
				x = x.getLeft();
			}
			else {
				x = x.getRight();
			}
			x.setParent(y);
			if(y == this.nil) {
				this.root = z;
			}
			else if(z.getKey().compareTo(y.getKey()) < 0) {
				y.setLeft(z);
			}
			else {
				y.setRight(z);
			}
			z.setLeft(this.nil);
			z.setRight(this.nil);
			z.setColor(RED);
			insertFixup(z);
		}
	}
	
	private void insertFixup(RedBlackNode z) {
		RedBlackNode y;
		while(z.getParent().getColor() == RED) {
			if(z.getParent() == z.getParent().getParent().getLeft()) {
				y = z.getParent().getParent().getRight();
				if(y.getColor() == RED) { 						//Case 1
					z.getParent().setColor(BLACK);
					y.setColor(BLACK);
					z.getParent().getParent().setColor(RED);
					z = z.getParent().getParent();
				}
				else if(z == z.getParent().getRight()) { 		//case 2
					z = z.getParent();
					leftRotate(z);
					z.getParent().setColor(BLACK);				//case 3
					z.getParent().getParent().setColor(RED);
				}
			}
			else {
				y = z.getParent().getParent().getLeft();
				if(y.getColor() == RED) { 						
					z.getParent().setColor(BLACK);
					y.setColor(BLACK);
					z.getParent().getParent().setColor(RED);
					z = z.getParent().getParent();
				}
				else if(z == z.getParent().getLeft()) {
					z = z.getParent();
					rightRotate(z);
					z.getParent().setColor(BLACK);
					z.getParent().getParent().setColor(RED);
				}
			}
		}
		this.root.setColor(BLACK);
	}
	
	  /**
	   * Returns a representation of this tree as a multi-line string.
	   * The tree is drawn with the root at the left and children are
	   * shown top-to-bottom.  Leaves are marked with a "-" and non-leaves
	   * are marked with a "+".
	   */
	  @Override
	  public String toString(){
	    StringBuilder sb = new StringBuilder();
	    toStringRec(root, sb, 0);
	    return sb.toString();
	  }
	  
	  /**
	   * Preorder traversal of the tree that builds a string representation
	   * in the given StringBuilder.
	   * @param n root of subtree to be traversed
	   * @param sb StringBuilder in which to create a string representation
	   * @param depth depth of the given node in the tree
	   */
	  private void toStringRec(RedBlackNode n, StringBuilder sb, int depth){
	    for (int i = 0; i < depth; ++i){
	      sb.append("  ");
	    }
	    
	    if (n == null){
	      sb.append("-\n");
	      return;
	    }
	    
	    if (n.left != nil || n.right != nil){
	      sb.append("+ ");
	    }
	    else{
	      sb.append("- ");
	    }
	    sb.append(n.key.toString());
	    sb.append("\n");
	    if (n.left != nil || n.right != nil){
	      toStringRec(n.left, sb, depth + 1);   
	      toStringRec(n.right, sb, depth + 1);
	    }
	  }
	
}
