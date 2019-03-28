package test.java;


import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import main.java.Endpoint;
import main.java.Node;
import main.java.RBTree;

public class NodeTest {

	//This is the tree from the spec sheet
	public static RBTree rbt1;
	
	public final static int LEFT = 1;
	public final static int RIGHT = -1;
	public final static int RED = 0;
	public final static int BLACK = 1;
	
	public final static Node nilNode = new Node(new Endpoint(-1, 0));

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
	
	@Test
	public void sizeOfNode() {
		//Assert.assertTrue(rbt1.getRoot().getSize() == 17);
	}
	
	@Test
	public void value() {
		int[] correctValues = {1, 3, 1, 0, -1, -2, -2, -1};
		ArrayList<Node> x = rbt1.getNodesInOrder(rbt1.getRoot());
		for(int i = 0; i < x.size(); i++) {
			//System.out.println(x.get(i).toString());
			Assert.assertTrue(x.get(i).getVal() == correctValues[i]);
		}
		
		Node max = rbt1.maximum(rbt1.getRoot());
	//	System.out.println("11's left:" + max.getLeft().toString());
	//	System.out.println("11's right:" + max.getRight().toString());
	//	System.out.println("11 node info:" + max.toString());
	}
	
	@Test
	public void getMaxValue() {
	//	System.out.println("Root maxVal expected: 3, actual:  " + rbt1.getRoot().getMaxVal());
		Assert.assertTrue(rbt1.getRoot().getMaxVal() == 3);
		int[] correctMaxVals = {1, 3, 1, 3, 0, 0, 0, 0};
		ArrayList<Node> x = rbt1.getNodesInOrder(rbt1.getRoot());
		for(int i = 0; i < x.size(); i++) {
			Assert.assertTrue(x.get(i).getMaxVal() == correctMaxVals[i]);
		}
	}
	
	//TODO
	@Test
	public void getEmaxs() {
		Endpoint e1 = new Endpoint(0, LEFT);
		Endpoint e3 = new Endpoint(3, LEFT);
		Endpoint e6 = new Endpoint(7, LEFT);
		
		Endpoint[] eMax = {e1, e3, e3, e3, nilNode.getEndpoint(), e6, nilNode.getEndpoint(), nilNode.getEndpoint()};
		ArrayList<Node> x = rbt1.getNodesInOrder(rbt1.getRoot());
		for(int i = 0; i < x.size(); i++) {
		//	System.out.println("Current Node:" + x.get(i).toString());
		//	System.out.println("expected: " + eMax[i].getValue() + ", acutal: " + x.get(i).getEmax().getValue());
			Assert.assertTrue(x.get(i).getEmax().equals(eMax[i]));
		}
	}

}
