package test.java;


import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import main.java.Endpoint;
import main.java.Node;
import main.java.Position;
import main.java.RBTree;

/**
 * 
 * @author Will Sartin & Josh Ramon
 *
 */
public class NodeTest {

	//This is the tree from the spec sheet
	public static RBTree rbt1;
	
	public final static Node nilNode = new Node(new Endpoint(0, Position.NIL));

	@Before
	public void setUp() {
		rbt1 = new RBTree();
		
		rbt1.RBInsert(new Node(new Endpoint(0, Position.LEFT)));
		rbt1.RBInsert(new Node(new Endpoint(4, Position.RIGHT)));
		
		rbt1.RBInsert(new Node(new Endpoint(1, Position.LEFT)));
		rbt1.RBInsert(new Node(new Endpoint(6, Position.RIGHT)));
		
		rbt1.RBInsert(new Node(new Endpoint(3, Position.LEFT)));
		rbt1.RBInsert(new Node(new Endpoint(9, Position.RIGHT)));
		
		rbt1.RBInsert(new Node(new Endpoint(7, Position.LEFT)));
		rbt1.RBInsert(new Node(new Endpoint(11, Position.RIGHT)));
	}

	@Test
	public void sizeOfNode() {
		int[] correctValues_rbt1 = {1, 3, 1, 8, 1, 4, 2, 1};
		ArrayList<Node> x = rbt1.getNodesInOrder(rbt1.getRoot());
		assertTrue(x.size() == correctValues_rbt1.length);
		for(int i = 0; i < x.size(); i++) {
			Assert.assertTrue(x.get(i).getSize() == correctValues_rbt1[i]);
		}
	}
	
	@Test
	public void heightOfNodes() {
		ArrayList<Node> x = rbt1.getNodesInOrder(rbt1.getRoot());
		int[] correctValues_rbt1 = {1, 2, 1, 4, 1, 3, 2, 1};
		assertTrue(x.size() == correctValues_rbt1.length);
		for(int i = 0; i < x.size(); i++) {
			Assert.assertTrue(x.get(i).getHeight() == correctValues_rbt1[i]);
		}
	}
	
	@Test
	public void getPTest() {
		int[] correctValues_rbt1 = {1, 1, 1, -1, -1, 1, -1, -1};
		ArrayList<Node> x = rbt1.getNodesInOrder(rbt1.getRoot());
		assertTrue(x.size() == correctValues_rbt1.length);
		for(int i = 0; i < x.size(); i++) {
			Assert.assertTrue(x.get(i).getP() == correctValues_rbt1[i]);
		}
	}
	
	@Test
	public void value() {
		int[] correctValues_rbt1 = {1, 3, 1, 0, -1, -2, -2, -1};
		ArrayList<Node> x = rbt1.getNodesInOrder(rbt1.getRoot());
		assertTrue(x.size() == correctValues_rbt1.length);
		for(int i = 0; i < x.size(); i++) {
			//System.out.println("Expected: " + correctValues_rbt1[i] + ", actual: " +x.get(i).getVal());
			Assert.assertTrue(x.get(i).getVal() == correctValues_rbt1[i]);
		}
	}
	
	@Test
	public void getMaxValue() {
		Assert.assertTrue(rbt1.getRoot().getMaxVal() == 3);
		int[] correctValues_rbt1 = {1, 3, 1, 3, 0, 0, 0, 0};
		ArrayList<Node> x = rbt1.getNodesInOrder(rbt1.getRoot()); //node 6 should be 1
		assertTrue(x.size() == correctValues_rbt1.length);
		for(int i = 0; i < x.size(); i++) {
			Assert.assertTrue(x.get(i).getMaxVal() == correctValues_rbt1[i]);
		}
	}
	
	 // LO EMAX doesn't work
	@Test
	public void getEmaxs() {
		Endpoint e1 = new Endpoint(0, Position.LEFT);
		Endpoint e3 = new Endpoint(3, Position.LEFT);
		Endpoint e6 = new Endpoint(7, Position.LEFT);
		
		Endpoint[] correctValues_rbt1 = {e1, e3, e3, e3, nilNode.getEndpoint(), e6, nilNode.getEndpoint(), nilNode.getEndpoint()};
		ArrayList<Node> x = rbt1.getNodesInOrder(rbt1.getRoot());
		assertTrue(x.size() == correctValues_rbt1.length);
		//System.out.println("Root's right child: " + rbt1.getRoot().getRight().structuredToString());
		for(int i = 0; i < x.size(); i++) {
			System.out.println(x.get(i).structuredToString());
			System.out.println("correctVal: " + correctValues_rbt1[i] + ", actual: " + x.get(i).getEmax());
			Assert.assertTrue(x.get(i).getEmax().equals(correctValues_rbt1[i]));
		}
	}

}
