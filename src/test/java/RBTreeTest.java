package test.java;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import main.java.Endpoint;
import main.java.Node;
import main.java.RBTree;

public class RBTreeTest {
	
	//This is the tree from the spec sheet
	public static RBTree rbt1;
	
	public final static Node nilNode = new Node(new Endpoint(-1, 0));
	public final static int LEFT = 1;
	public final static int RIGHT = -1;
	public final static int RED = 0;
	public final static int BLACK = 1;

	@Before
	public void setUp() {
		rbt1 = new RBTree();
		
		rbt1.RBInsert(new Node(new Endpoint(0, LEFT)));
		rbt1.RBInsert(new Node(new Endpoint(4, RIGHT)));
		
		rbt1.RBInsert(new Node(new Endpoint(1, LEFT)));
		rbt1.RBInsert(new Node(new Endpoint(6, RIGHT)));
		
		rbt1.RBInsert(new Node(new Endpoint(3, LEFT)));
		rbt1.RBInsert(new Node(new Endpoint(9, RIGHT)));
		
		rbt1.RBInsert(new Node(new Endpoint(7, LEFT)));
		rbt1.RBInsert(new Node(new Endpoint(11, RIGHT)));
		
	}
		/**
		 * Every Node is either red or black
		 */
		@Test
		public void RBTreePropertyOne_Success() {
		ArrayList<Node> x = rbt1.getNodesInOrder(rbt1.getRoot());
		for(int i = 0; i < x.size(); i++) {
			Assert.assertTrue(x.get(i).getColor() == RED || x.get(i).getColor() == BLACK);
		}
		//System.out.println(x.toString());
	}
	
	/**
	 * Root is Black
	 */
	@Test
	public void RBTreePropertyTwo_Success() {
		Assert.assertTrue(rbt1.getRoot().getColor() == BLACK);
	}
	
	/**
	 * Every leaf(nil) is black
	 * Note: we are doing T.il in our RBTree
	 */
	@Test
	public void RBTreePropertyThree_Success() {
		Assert.assertTrue(rbt1.getRoot().getParent().getColor() == BLACK);
		Assert.assertTrue(rbt1.getRoot().getParent() == rbt1.getRoot().getLeft().getLeft().getLeft());
		Assert.assertTrue(rbt1.getRoot().getParent() == rbt1.getRoot().getLeft().getLeft().getRight());
		Assert.assertTrue(rbt1.getRoot().getParent() == rbt1.getRoot().getLeft().getRight().getRight());
		Assert.assertTrue(rbt1.getRoot().getParent() == rbt1.getRoot().getLeft().getRight().getLeft());
		Assert.assertTrue(rbt1.getRoot().getParent() == rbt1.getRoot().getRight().getLeft().getLeft());
		Assert.assertTrue(rbt1.getRoot().getParent() == rbt1.getRoot().getRight().getLeft().getRight());
		Assert.assertTrue(rbt1.getRoot().getParent() == rbt1.getRoot().getRight().getRight().getLeft());
		Assert.assertTrue(rbt1.getRoot().getParent() == rbt1.getRoot().getRight().getRight().getRight().getLeft());
		Assert.assertTrue(rbt1.getRoot().getParent() == rbt1.getRoot().getRight().getRight().getRight().getRight());
	}
	
	/**
	 * If a node is red then both of its children are black
	 */
	@Test
	public void RBTreePropertyFour_Success() {
		ArrayList<Node> x = rbt1.getNodesInOrder(rbt1.getRoot());
		for(int i = 0; i < x.size(); i++) {
			Node temp = x.get(i);
			if(temp.getColor() == RED) {
				Assert.assertTrue(temp.getLeft().getColor() == BLACK && temp.getRight().getColor() == BLACK);
			}
		}
	}
	
	/**
	 * For each node, all simple paths from the node to the 
	 * descendant leaves contain the same number of black nodes (blackheight)
	 */
	@Test
	public void RBTreePropertyFive_Success() {
		Assert.assertTrue(rbt1.getBlackHeight(rbt1.getRoot()) == 3);
		Assert.assertTrue(rbt1.getBlackHeight(rbt1.getRoot().getLeft()) == 2);
		Assert.assertTrue(rbt1.getBlackHeight(rbt1.getRoot().getRight()) == 2);
		Assert.assertTrue(rbt1.getBlackHeight(rbt1.getRoot().getLeft().getLeft()) == 2);
		Assert.assertTrue(rbt1.getBlackHeight(rbt1.getRoot().getLeft().getRight()) == 2);
		Assert.assertTrue(rbt1.getBlackHeight(rbt1.getRoot().getRight().getLeft()) == 2);
		Assert.assertTrue(rbt1.getBlackHeight(rbt1.getRoot().getRight().getRight()) == 2);
		Assert.assertTrue(rbt1.getBlackHeight(rbt1.getRoot().getRight().getRight().getRight()) == 1);
		Assert.assertTrue(rbt1.getBlackHeight(rbt1.getRoot().getParent()) == 1);	
	}
	
	@Test
	public void RBTreeInsert_Success() {
		Node one = new Node(nilNode, new Endpoint(4, RIGHT), BLACK, 17);
		Node two = new Node(one, new Endpoint(1, LEFT), RED, 7);
		Node three = new Node(one, new Endpoint(7, LEFT), RED, 9);
		Node four = new Node(two, new Endpoint(0, LEFT), BLACK, 3);
		Node five = new Node(two, new Endpoint(3, LEFT), BLACK, 3);
		Node six = new Node(three, new Endpoint(6, RIGHT), BLACK, 3);
		Node seven = new Node(three, new Endpoint(9, RIGHT), BLACK, 5);
		Node eight = new Node(seven, new Endpoint(11, RIGHT), RED, 3);
		
		Assert.assertTrue(one.equals(rbt1.getRoot()));
		Assert.assertTrue(two.equals(rbt1.getRoot().getLeft()));
		Assert.assertTrue(three.equals(rbt1.getRoot().getRight()));
		Assert.assertTrue(four.equals(rbt1.getRoot().getLeft().getLeft()));
		Assert.assertTrue(five.equals(rbt1.getRoot().getLeft().getRight()));
		Assert.assertTrue(six.equals(rbt1.getRoot().getRight().getLeft()));
		Assert.assertTrue(seven.equals(rbt1.getRoot().getRight().getRight()));
		Assert.assertTrue(eight.equals(rbt1.getRoot().getRight().getRight().getRight()));	
		Assert.assertTrue(nilNode.equals(rbt1.getRoot().getParent()));
	}
	
	@Test
	public void minimum_Success() {
		Node minOfRoot = new Node(nilNode, new Endpoint(0, LEFT), BLACK, 3);
		Assert.assertTrue(rbt1.minimum(rbt1.getRoot()).equals(minOfRoot));
		Assert.assertTrue(rbt1.minimum(rbt1.getRoot().getLeft().getLeft()).equals(minOfRoot));
	}
	
	@Test
	public void maximum_Success() {
		Node maxOfRoot = new Node(nilNode, new Endpoint(11, RIGHT), RED, 3);
		Assert.assertTrue(rbt1.maximum(rbt1.getRoot()).equals(maxOfRoot));
		Assert.assertTrue(rbt1.maximum(rbt1.getRoot().getRight().getRight().getRight()).equals(maxOfRoot));
	}
	
	@Test
	public void successor_Success() {
		
	}
	
	@Test
	public void predessor_Success() {
		
	}
	
}