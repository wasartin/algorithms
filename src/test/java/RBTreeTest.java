package test.java;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import main.java.Endpoint;
import main.java.Node;
import main.java.RBTree;

public class RBTreeTest {
	
	/**
	 * Every Node is either red or black
	 */
	@Test
	public void RBTreePropertyOne_Success() {
		
	}
	
	/**
	 * Root is Black
	 */
	@Test
	public void RBTreePropertyTwo_Success() {
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
	 * descendant leaves contain the same number of black nodes
	 */
	@Test
	public void RBTreePropertyFive_Success() {
		
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
	
	@Test
	public void RBTreePrint() {
		//Example from PDF
		int left = 1;
		int right = -1;
		Node aL = new Node(new Endpoint(0, left));
		Node aR = new Node(new Endpoint(4, right));
		Node bL = new Node(new Endpoint(1, left));
		Node bR = new Node(new Endpoint(6, right));
		
		Node cL = new Node(new Endpoint(3, left));
		Node cR = new Node(new Endpoint(9, right));
		Node dL = new Node(new Endpoint(7, left));
		Node dR = new Node(new Endpoint(11, right));
		
		RBTree rbtree = new RBTree();
		rbtree.RBInsert(aL);
		rbtree.RBInsert(aR);
		
		rbtree.RBInsert(bL);
		rbtree.RBInsert(bR);
		rbtree.RBInsert(cL);
		rbtree.RBInsert(cR);
		rbtree.RBInsert(dL);
		rbtree.RBInsert(dR);
		
		System.out.println(rbtree.toString());
	}

}
