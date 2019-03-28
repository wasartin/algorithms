package test.java;


import org.junit.Test;

import main.java.Endpoint;
import main.java.Node;
import main.java.RBTree;

import org.junit.Assert;
import org.junit.Before;

public class NodeTest {

	//This is the tree from the spec sheet
	public static RBTree rbt1;
	
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
	
	@Test
	public void sizeOfRoot() {
		//System.out.println(rbt1.getRoot().getSize());
	}
	
	
	@Test
	public void value() {
		System.out.println(rbt1.getRoot().getVal());
		System.out.println(rbt1.getRoot().getLeft().getVal());
	}
	
	@Test
	public void getMaxValue() {
		//System.out.println(rbt1.getRoot().getMaxVal());
		//Assert.assertTrue(rbt1.getRoot().getMaxVal() == 3);
	}

}
