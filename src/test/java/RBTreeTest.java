package test.java;

import static org.junit.jupiter.api.Assertions.fail;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import org.junit.jupiter.api.BeforeAll;

import main.java.Endpoint;
import main.java.Node;
import main.java.RBTree;

public class RBTreeTest {
	
	//This is the tree from the spec sheet
	public static RBTree rbt1;
	
	public final static int LEFT = 1;
	public final static int RIGHT = -1;
	public final static int RED = 0;
	public final static int BLACK = 1;
	

	@BeforeAll
	public static void setUp() {
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
//		ArrayList<Node> x = rbt1.getNodesInOrder(rbt1.getRoot());
//		for(int i = 0; i < x.size(); i++) {
//			Assert.assertTrue(x.get(i).getColor() == RED || x.get(i).getColor() == BLACK);
//		}
//		System.out.println(x.toString());
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
		
	}
	
	/**
	 * If a node is red then both of its children are black
	 */
	@Test
	public void RBTreePropertyFour_Success() {
		
	}
	
	/**
	 * For each node, all simple paths from the node to the 
	 * descendant leaves contain the same number of black nodes (blackheight)
	 */
	@Test
	public void RBTreePropertyFive_Success() {
		
	}
	
	@Test
	public void RBTree_AllNilNodesAreTheSame() {
		
	}
	
	@Test
	public void RBTreeInsert_Success() {
		int left = 1;
		int right = -1;
		Endpoint a = new Endpoint(0, left);
		Endpoint b = new Endpoint(4, right);
		Node newNodeA = new Node(a);
		Node newNodeB = new Node(b);
		RBTree rbtree = new RBTree();
		rbtree.RBInsert(newNodeA);
		rbtree.RBInsert(newNodeB);
	}
	
	

}
