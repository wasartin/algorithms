import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author Will Sartin & Josh Ramon
 *
 */
public class RBTreeTest {
	
	//This is the tree from the spec sheet
	public static RBTree rbt1;
	
	//TODO: make more trees to test
	
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
		/**
		 * Every Node is either red or black
		 */
		@Test
		public void RBTreePropertyOne_Success() {
		ArrayList<Node> x = rbt1.getNodesInOrder(rbt1.getRoot());
		for(int i = 0; i < x.size(); i++) {
			Assert.assertTrue(x.get(i).getColor() == Color.RED.ordinal() || x.get(i).getColor() == Color.BLACK.ordinal());
		}
	}
	
	/**
	 * Root is Black
	 */
	@Test
	public void RBTreePropertyTwo_Success() {
		Assert.assertTrue(rbt1.getRoot().getColor() == Color.BLACK.ordinal());
	}
	
	/**
	 * Every leaf(nil) is black
	 * Note: we are doing T.il in our RBTree
	 */
	@Test
	public void RBTreePropertyThree_Success() {
		Assert.assertTrue(rbt1.getRoot().getParent().getColor() == Color.BLACK.ordinal());
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
			if(temp.getColor() == Color.RED.ordinal()) {
				Assert.assertTrue(temp.getLeft().getColor() == Color.BLACK.ordinal() && temp.getRight().getColor() == Color.BLACK.ordinal());
			}
		}
	}
	
	/**
	 * For each node, all simple paths from the node to the 
	 * descendant leaves contain the same number of black nodes (blackheight)
	 */
	@Test
	public void RBTreePropertyFive_Success() {
		Assert.assertTrue(rbt1.getBlackHeight() == 3);
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
		Node one = new Node(nilNode, new Endpoint(4, Position.RIGHT), Color.BLACK, 17);
		Node two = new Node(one, new Endpoint(1, Position.LEFT), Color.RED, 7);
		Node three = new Node(one, new Endpoint(7, Position.LEFT), Color.RED, 9);
		Node four = new Node(two, new Endpoint(0, Position.LEFT), Color.BLACK, 3);
		Node five = new Node(two, new Endpoint(3, Position.LEFT), Color.BLACK, 3);
		Node six = new Node(three, new Endpoint(6, Position.RIGHT), Color.BLACK, 3);
		Node seven = new Node(three, new Endpoint(9, Position.RIGHT), Color.BLACK, 5);
		Node eight = new Node(seven, new Endpoint(11, Position.RIGHT), Color.RED, 3);
		
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
	
	/**
	 * @Required for project
	 * All the NilNodes are referencing the same node.
	 */
	@Test
	public void nilNodes() {
		ArrayList<Node> inOrder = rbt1.getNodesInOrder(rbt1.getRoot());
		for(int i = 0; i < inOrder.size(); i++) {
			Node temp = inOrder.get(i);
			if(temp.getParent().isNilNode()) {
				Assert.assertTrue(temp.getParent() == rbt1.getNILNode());
			}
			if(temp.getLeft().isNilNode()) {
				Assert.assertTrue(temp.getLeft() == rbt1.getNILNode());
			}
			if(temp.getRight().isNilNode()) {
				Assert.assertTrue(temp.getRight() == rbt1.getNILNode());
			}
		}
	}
	
	/**
	 * @Required for project
	 */
	@Test
	public void GetSizeTest_Success() {
		Assert.assertTrue(rbt1.getSize() == 8);
		RBTree emptyTree = new RBTree();
		Assert.assertTrue(emptyTree.getSize() == 0);
	}
	
	/**
	 * @Required for proejct
	 * 
	 */
	@Test
	public void GetHeightTest_Success() {
		Assert.assertTrue(rbt1.getHeight() == 4);
		RBTree emptyTree = new RBTree();
		Assert.assertTrue(emptyTree.getHeight() == 0);
	}
	
	@Test
	public void minimum_Success() {
		Node minOfRoot = new Node(nilNode, new Endpoint(0, Position.LEFT), Color.BLACK, 3);
		Assert.assertTrue(rbt1.minimum(rbt1.getRoot()).equals(minOfRoot));
		Assert.assertTrue(rbt1.minimum(rbt1.getRoot().getLeft().getLeft()).equals(minOfRoot));
	}
	
	@Test
	public void maximum_Success() {
		Node maxOfRoot = new Node(nilNode, new Endpoint(11, Position.RIGHT), Color.RED, 3);
		Assert.assertTrue(rbt1.maximum(rbt1.getRoot()).equals(maxOfRoot));
		Assert.assertTrue(rbt1.maximum(rbt1.getRoot().getRight().getRight().getRight()).equals(maxOfRoot));
	}
	
	@Test
	public void successor_Success() {
		
	}
	
	@Test
	public void predessor_Success() {
		
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
			Assert.assertTrue(acutalNodes.get(i).equals(expected[i]));
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
			Assert.assertTrue(acutalNodes.get(i).equals(expected[i]));
		}
	}
}