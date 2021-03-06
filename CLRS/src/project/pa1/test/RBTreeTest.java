package project.pa1.test;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import project.pa1.main.Color;
import project.pa1.main.Endpoint;
import project.pa1.main.Node;
import project.pa1.main.Position;
import project.pa1.main.RBTree;
/**
 * 
 * @author Will Sartin & Josh Ramon
 *
 */
public class RBTreeTest {
	
	//This is the tree from the spec sheet
	public static RBTree rbt_example;

	public final static Node nilNode = new Node(new Endpoint(0, Position.NIL));

	@Before
	public void setUp() {
		rbt_example = new RBTree();
		
		rbt_example.RBInsert(new Node(new Endpoint(0, Position.LEFT)));
		rbt_example.RBInsert(new Node(new Endpoint(4, Position.RIGHT)));
		
		rbt_example.RBInsert(new Node(new Endpoint(1, Position.LEFT)));
		rbt_example.RBInsert(new Node(new Endpoint(6, Position.RIGHT)));
		
		rbt_example.RBInsert(new Node(new Endpoint(3, Position.LEFT)));
		rbt_example.RBInsert(new Node(new Endpoint(9, Position.RIGHT)));
		
		rbt_example.RBInsert(new Node(new Endpoint(7, Position.LEFT)));
		rbt_example.RBInsert(new Node(new Endpoint(11, Position.RIGHT)));
	}
		/**
		 * Every Node is either red or black
		 */
		@Test
		public void RBTreePropertyOne_Success() {
		ArrayList<Node> x = rbt_example.getNodesInOrder(rbt_example.getRoot());
		for(int i = 0; i < x.size(); i++) {
			assertTrue(x.get(i).getColor() == Color.RED.ordinal() || x.get(i).getColor() == Color.BLACK.ordinal());
		}
	}
	
	/**
	 * Root is Black
	 */
	@Test
	public void RBTreePropertyTwo_Success() {
		assertTrue(rbt_example.getRoot().getColor() == Color.BLACK.ordinal());
	}
	
	/**
	 * Every leaf(nil) is black
	 * Note: we are doing T.il in our RBTree
	 */
	@Test
	public void RBTreePropertyThree_Success() {
		assertTrue(rbt_example.getRoot().getParent().getColor() == Color.BLACK.ordinal());
		assertTrue(rbt_example.getRoot().getParent() == rbt_example.getRoot().getLeft().getLeft().getLeft());
		assertTrue(rbt_example.getRoot().getParent() == rbt_example.getRoot().getLeft().getLeft().getRight());
		assertTrue(rbt_example.getRoot().getParent() == rbt_example.getRoot().getLeft().getRight().getRight());
		assertTrue(rbt_example.getRoot().getParent() == rbt_example.getRoot().getLeft().getRight().getLeft());
		assertTrue(rbt_example.getRoot().getParent() == rbt_example.getRoot().getRight().getLeft().getLeft());
		assertTrue(rbt_example.getRoot().getParent() == rbt_example.getRoot().getRight().getLeft().getRight());
		assertTrue(rbt_example.getRoot().getParent() == rbt_example.getRoot().getRight().getRight().getLeft());
		assertTrue(rbt_example.getRoot().getParent() == rbt_example.getRoot().getRight().getRight().getRight().getLeft());
		assertTrue(rbt_example.getRoot().getParent() == rbt_example.getRoot().getRight().getRight().getRight().getRight());
	}
	
	/**
	 * If a node is red then both of its children are black
	 */
	@Test
	public void RBTreePropertyFour_Success() {
		ArrayList<Node> x = rbt_example.getNodesInOrder(rbt_example.getRoot());
		for(int i = 0; i < x.size(); i++) {
			Node temp = x.get(i);
			if(temp.getColor() == Color.RED.ordinal()) {
				assertTrue(temp.getLeft().getColor() == Color.BLACK.ordinal() && temp.getRight().getColor() == Color.BLACK.ordinal());
			}
		}
	}
	
	/**
	 * For each node, all simple paths from the node to the 
	 * descendant leaves contain the same number of black nodes (blackheight)
	 */
	@Test
	public void RBTreePropertyFive_Success() {
		assertTrue(rbt_example.getBlackHeight() == 3);
		assertTrue(rbt_example.getBlackHeight(rbt_example.getRoot()) == 3);
		assertTrue(rbt_example.getBlackHeight(rbt_example.getRoot().getLeft()) == 2);
		assertTrue(rbt_example.getBlackHeight(rbt_example.getRoot().getRight()) == 2);
		assertTrue(rbt_example.getBlackHeight(rbt_example.getRoot().getLeft().getLeft()) == 2);
		assertTrue(rbt_example.getBlackHeight(rbt_example.getRoot().getLeft().getRight()) == 2);
		assertTrue(rbt_example.getBlackHeight(rbt_example.getRoot().getRight().getLeft()) == 2);
		assertTrue(rbt_example.getBlackHeight(rbt_example.getRoot().getRight().getRight()) == 2);
		assertTrue(rbt_example.getBlackHeight(rbt_example.getRoot().getRight().getRight().getRight()) == 1);
		assertTrue(rbt_example.getBlackHeight(rbt_example.getRoot().getParent()) == 1);	
	}
	
	@Test
	public void RBTreeInsert_Success() {
		Node one = new Node(nilNode, new Endpoint(4, Position.RIGHT), Color.BLACK, 17);
		Node two = new Node(one, new Endpoint(1, Position.LEFT), Color.RED, 7);
		Node three = new Node(one, new Endpoint(7, Position.LEFT), Color.RED, 9);
		Node four = new Node(two, new Endpoint(0, Position.LEFT), Color.BLACK, 3);
		Node five = new Node(two, new Endpoint(3, Position.LEFT), Color.BLACK, 3);
		Node six = new Node(three, new Endpoint(6, Position.RIGHT), Color.BLACK, 3);
		Node seven = new Node(three, new Endpoint(9, Position.RIGHT), Color.BLACK, 5);
		Node eight = new Node(seven, new Endpoint(11, Position.RIGHT), Color.RED, 3);
		
		assertTrue(one.equals(rbt_example.getRoot()));
		assertTrue(two.equals(rbt_example.getRoot().getLeft()));
		assertTrue(three.equals(rbt_example.getRoot().getRight()));
		assertTrue(four.equals(rbt_example.getRoot().getLeft().getLeft()));
		assertTrue(five.equals(rbt_example.getRoot().getLeft().getRight()));
		assertTrue(six.equals(rbt_example.getRoot().getRight().getLeft()));
		assertTrue(seven.equals(rbt_example.getRoot().getRight().getRight()));
		assertTrue(eight.equals(rbt_example.getRoot().getRight().getRight().getRight()));	
		assertTrue(nilNode.equals(rbt_example.getRoot().getParent()));
	}
	
	/**
	 * @Required for project
	 * All the NilNodes are referencing the same node.
	 */
	@Test
	public void nilNodes() {
		ArrayList<Node> inOrder = rbt_example.getNodesInOrder(rbt_example.getRoot());
		for(int i = 0; i < inOrder.size(); i++) {
			Node temp = inOrder.get(i);
			if(temp.getParent().isNilNode()) {
				assertTrue(temp.getParent() == rbt_example.getNILNode());
			}
			if(temp.getLeft().isNilNode()) {
				assertTrue(temp.getLeft() == rbt_example.getNILNode());
			}
			if(temp.getRight().isNilNode()) {
				assertTrue(temp.getRight() == rbt_example.getNILNode());
			}
		}
	}
	
	/**
	 * @Required for project
	 */
	@Test
	public void GetSizeTest_Success() {
		assertTrue(rbt_example.getSize() == 8);
		RBTree emptyTree = new RBTree();
		assertTrue(emptyTree.getSize() == 0);
	}
	
	/**
	 * @Required for proejct
	 * 
	 */
	@Test
	public void GetHeightTest_Success() {
		assertTrue(rbt_example.getHeight() == 4);
		RBTree emptyTree = new RBTree();
		assertTrue(emptyTree.getHeight() == 0);
	}
	
	@Test
	public void minimum_Success() {
		Node minOfRoot = new Node(nilNode, new Endpoint(0, Position.LEFT), Color.BLACK, 3);
		assertTrue(rbt_example.minimum(rbt_example.getRoot()).equals(minOfRoot));
		assertTrue(rbt_example.minimum(rbt_example.getRoot().getLeft().getLeft()).equals(minOfRoot));
	}
	
	@Test
	public void maximum_Success() {
		Node maxOfRoot = new Node(nilNode, new Endpoint(11, Position.RIGHT), Color.RED, 3);
		assertTrue(rbt_example.maximum(rbt_example.getRoot()).equals(maxOfRoot));
		assertTrue(rbt_example.maximum(rbt_example.getRoot().getRight().getRight().getRight()).equals(maxOfRoot));
	}
	
	@Test
	public void deleteCase1_Success() {
		RBTree case1 = new RBTree();
		
		Node a = new Node(new Endpoint(0, Position.LEFT));
		Node b = new Node(new Endpoint(1, Position.LEFT));
		Node c = new Node(new Endpoint(6, Position.RIGHT));
		case1.RBInsert(a);
		case1.RBInsert(new Node(new Endpoint(4, Position.RIGHT)));
		case1.RBInsert(b);
		case1.RBInsert(c);
		case1.RBDelete(case1.getRoot().getRight());
		
		ArrayList<Node> acutalNodes = case1.getNodesInOrder(case1.getRoot());
		a.setColor(Color.BLACK);
		b.setColor(Color.BLACK);
		c.setColor(Color.BLACK);
		Node [] expected = {a, b, c};
		assertTrue(acutalNodes.size() == expected.length);
		for(int i = 0; i < acutalNodes.size(); i++) {
			assertTrue(acutalNodes.get(i).equals(expected[i]));
		}
	}
	
	@Test
	public void deleteCase2_Success() {
		RBTree case2 = new RBTree();
		
		Node a = new Node(new Endpoint(0, Position.LEFT));
		Node b = new Node(new Endpoint(1, Position.LEFT));
		Node c = new Node(new Endpoint(6, Position.RIGHT));
		case2.RBInsert(a);
		case2.RBInsert(new Node(new Endpoint(4, Position.RIGHT)));
		case2.RBInsert(b);
		case2.RBInsert(c);
		case2.RBDelete(case2.getRoot().getRight());
		
		ArrayList<Node> acutalNodes = case2.getNodesInOrder(case2.getRoot());
		a.setColor(Color.BLACK);
		b.setColor(Color.BLACK);
		c.setColor(Color.BLACK);
		Node [] expected = {a, b, c};
		assertTrue(acutalNodes.size() == expected.length);
		for(int i = 0; i < acutalNodes.size(); i++) {
			assertTrue(acutalNodes.get(i).equals(expected[i]));
		}
	}


}