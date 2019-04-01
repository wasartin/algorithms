

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
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
public class OtherRedBlackTreeTests {

    // Insert
    private RBTree tree;
    private Node node1;
    private Node node2;
    private Node node3;
    private Node node4;
    
    //Red Black Tree
    private RBTree tree2;
    private Node node1_t2;
    private Node node2_t2;
    private Node node3_t2;
    private Node node4_t2;
    private Node node5_t2;
    private Node node6_t2;
    
    //Delete
    private RBTree tree3;
    private Node node1_t3;
    private Node node2_t3;
    private Node node3_t3;
    private Node node4_t3;
    private Node node5_t3;
    private Node node6_t3;

    @Before
    public void initialize() {
        tree = new RBTree();
        node1 = new Node(new Endpoint(1, Position.LEFT));
        node2 = new Node(new Endpoint(2, Position.LEFT));
        node3 = new Node(new Endpoint(3, Position.LEFT));
        node4 = new Node(new Endpoint(4, Position.LEFT));
        
        tree2 = new RBTree();
        node1_t2 = new Node(new Endpoint(1, Position.LEFT));
        node2_t2 = new Node(new Endpoint(2, Position.LEFT));
        node3_t2 = new Node(new Endpoint(3, Position.LEFT));
        node4_t2 = new Node(new Endpoint(4, Position.LEFT));
        node5_t2 = new Node(new Endpoint(5, Position.LEFT));
        node6_t2 = new Node(new Endpoint(6, Position.LEFT));
        
        tree3 = new RBTree();
        node1_t3 = new Node(new Endpoint(1, Position.LEFT));
        node2_t3 = new Node(new Endpoint(2, Position.LEFT));
        node3_t3 = new Node(new Endpoint(3, Position.LEFT));
        node4_t3 = new Node(new Endpoint(4, Position.LEFT));
        node5_t3 = new Node(new Endpoint(5, Position.LEFT));
        node6_t3 = new Node(new Endpoint(6, Position.LEFT));
    }

    @Test
    public void firstInsert() {
        tree.RBInsert(node1);
        assertEquals(tree.getRoot().getKey(), 1);
        assertEquals(tree.getRoot().getColorEnum(), Color.BLACK);
    }

    @Test
    public void leftInsert() {
        tree.RBInsert(node2);
        tree.RBInsert(node1);
        assertEquals(tree.getRoot().getKey(), 2);
        assertEquals(tree.getRoot().getColorEnum(), Color.BLACK);
        assertEquals(tree.getRoot().getLeft().getKey(), 1);
        assertEquals(tree.getRoot().getLeft().getColorEnum(), Color.RED);
        assertTrue(tree.getRoot().getRight().isNilNode());
    }

    @Test
    public void rightInsert() {
        tree.RBInsert(node2);
        tree.RBInsert(node3);
        assertEquals(tree.getRoot().getKey(), 2);
        assertEquals(tree.getRoot().getColorEnum(), Color.BLACK);
        assertEquals(tree.getRoot().getRight().getKey(), 3);
        assertEquals(tree.getRoot().getRight().getColorEnum(), Color.RED);
        assertTrue(tree.getRoot().getLeft().isNilNode());
    }

    @Test
    public void leftRightInsert() {

        // Build tree
        tree.RBInsert(node2);
        tree.RBInsert(node1);
        tree.RBInsert(node3);

        // Test initial insertion
        assertEquals(tree.getRoot().getKey(), node2.getKey());
        assertEquals(tree.getRoot().getColorEnum(), Color.BLACK);

        // Test root's children
        Node left = tree.getRoot().getLeft();
        Node right = tree.getRoot().getRight();
        assertEquals(left.getKey(), node1.getKey());
        assertEquals(right.getKey(), node3.getKey());
        assertEquals(left.getParent().getKey(), node2.getKey());
        assertEquals(right.getParent().getKey(), node2.getKey());
        assertEquals(left.getColorEnum(), Color.RED);
        assertEquals(right.getColorEnum(), Color.RED);

        // Test children's children are nil
        assertTrue(left.getLeft().isNilNode());
        assertTrue(left.getRight().isNilNode());
        assertTrue(right.getLeft().isNilNode());
        assertTrue(right.getRight().isNilNode());

    }

    @Test
    public void insertWithLeftRotation() {

        // Build tree
        tree.RBInsert(node1);
        tree.RBInsert(node2);
        tree.RBInsert(node3);

        // Test root after rotation
        assertEquals(tree.getRoot().getKey(), 2);
        assertEquals(tree.getRoot().getColorEnum(), Color.BLACK);

        // Test children
        Node left = tree.getRoot().getLeft();
        Node right = tree.getRoot().getRight();
        assertEquals(left.getKey(), 1);
        assertEquals(right.getKey(), 3);
        assertEquals(left.getParent(), tree.getRoot());
        assertEquals(right.getParent(), tree.getRoot());
        assertEquals(left.getColorEnum(), Color.RED);
        assertEquals(right.getColorEnum(), Color.RED);

        // Test grandchildren are nil
        assertTrue(left.getLeft().isNilNode());
        assertTrue(left.getRight().isNilNode());
        assertTrue(right.getLeft().isNilNode());
        assertTrue(right.getRight().isNilNode());
    }

    @Test
    public void insertWithRightRotation() {

        // Build tree
        tree.RBInsert(node3);
        tree.RBInsert(node2);
        tree.RBInsert(node1);

        // Test root after rotation
        assertEquals(tree.getRoot().getKey(), 2);
        assertEquals(tree.getRoot().getColorEnum(), Color.BLACK);

        // Test children
        Node left = tree.getRoot().getLeft();
        Node right = tree.getRoot().getRight();
        assertEquals(left.getKey(), 1);
        assertEquals(right.getKey(), 3);
        assertEquals(left.getParent(), tree.getRoot());
        assertEquals(right.getParent(), tree.getRoot());
        assertEquals(left.getColorEnum(), Color.RED);
        assertEquals(right.getColorEnum(), Color.RED);

        // Test grandchildren are nil
        assertTrue(left.getLeft().isNilNode());
        assertTrue(left.getRight().isNilNode());
        assertTrue(right.getLeft().isNilNode());
        assertTrue(right.getRight().isNilNode());
    }

    @Test
    public void insertCase3() {

        // Build tree to create case 3 scenario (from Wikipedia)
        tree.RBInsert(node3);
        tree.RBInsert(node2);
        tree.RBInsert(node4);
        tree.RBInsert(node1);

        // Test if colors were repainted correctly
        assertEquals(tree.getRoot().getColorEnum(), Color.BLACK);
        assertEquals(tree.getRoot().getLeft().getColorEnum(), Color.BLACK);
        assertEquals(tree.getRoot().getRight().getColorEnum(), Color.BLACK);
        assertEquals(tree.getRoot().getLeft().getLeft().getColorEnum(), Color.RED);
    }

    @Test
    public void insertCase4() {

        // Build tree to create case 4 scenario (from Wikipedia)
        tree.RBInsert(node3);
        tree.RBInsert(node1);
        tree.RBInsert(node4);
        tree.getRoot().getRight().setColor(Color.BLACK);    // Set color of uncle to black
        tree.RBInsert(node2);

        // Test rotations
        assertEquals(tree.getRoot().getKey(), 2);
        assertEquals(tree.getRoot().getLeft().getKey(), 1);
        assertEquals(tree.getRoot().getRight().getKey(), 3);
        assertEquals(tree.getRoot().getRight().getRight().getKey(), 4);
        assertTrue(tree.getRoot().getLeft().getLeft().isNilNode());
        assertTrue(tree.getRoot().getLeft().getRight().isNilNode());
        assertTrue(tree.getRoot().getRight().getLeft().isNilNode());
        assertTrue(tree.getRoot().getRight().getRight().getLeft().isNilNode());
        assertTrue(tree.getRoot().getRight().getRight().getRight().isNilNode());

        // Test colors
        assertEquals(tree.getRoot().getColorEnum(), Color.BLACK);
        assertEquals(tree.getRoot().getLeft().getColorEnum(), Color.RED);
        assertEquals(tree.getRoot().getRight().getColorEnum(), Color.RED);
        assertEquals(tree.getRoot().getRight().getRight().getColorEnum(), Color.BLACK);
    }
    
    /**
     * RBT TESTS
     */
    
    @Test
    public void testGetRoot() {

        // Root should initially be NIL
        assertTrue(tree2.getRoot().isNilNode());

        // Insert node, which is new root
        tree2.RBInsert(node1_t2);
        assertFalse(tree2.getRoot().isNilNode());
        assertEquals(tree2.getRoot().getKey(), 1);

        // New insert keeps the same root as before
        tree2.RBInsert(node2_t2);
        assertEquals(tree2.getRoot().getKey(), 1);

        // Rotation results in new root
        tree2.RBInsert(node3_t2);
        assertEquals(tree2.getRoot().getKey(), 2);
    }
    
    @Test
    public void testGetNILNode() {
        Node nilNode = tree.getNILNode();
        assertTrue(nilNode.isNilNode());
        assertEquals(nilNode.getColorEnum(), Color.BLACK);
        tree2.RBInsert(node1_t2);
        assertEquals(tree.getNILNode(), nilNode);   // NIL node shouldn't change after insert
    }

    @Test
    public void testGetSize() {
        assertEquals(tree2.getSize(), 0);
        tree2.RBInsert(node1_t2);
        assertEquals(tree2.getSize(), 1);
        tree2.RBInsert(node2_t2);
        assertEquals(tree2.getSize(), 2);
        tree2.RBInsert(node3_t2);
        assertEquals(tree2.getSize(), 3);
    }
    
    @Test
    public void testGetHeight() {
        assertEquals(tree2.getHeight(), 0);
        tree2.RBInsert(node3_t2);
        assertEquals(tree2.getHeight(), 1);
        tree2.RBInsert(node2_t2);
        assertEquals(tree2.getHeight(), 2);
        tree2.RBInsert(node4_t2);
        assertEquals(tree2.getHeight(), 2); //?
        tree2.RBInsert(node5_t2);
        assertEquals(tree2.getHeight(), 3); //?
        tree2.RBInsert(node6_t2);
        tree2.printTree();
        assertEquals(tree2.getRoot().getRight().getLeft().getHeight(), 1);
        assertEquals(tree2.getHeight(), 3);  // Rotation keeps height at 3 //?
    }
    
    @Test
    public void simpleDelete() {

        // Create simple tree3
        tree3.RBInsert(node3_t3);
        tree3.RBInsert(node2_t3);
        tree3.RBInsert(node4_t3);
        tree3.RBInsert(node1_t3);
        
        // Delete root.left
        tree3.RBDelete(node2_t3);

        // Test tree3 keys
        assertEquals(3, tree3.getRoot().getKey());
        assertEquals(1, tree3.getRoot().getLeft().getKey());
        assertEquals(4, tree3.getRoot().getRight().getKey());

        // Test tree3 parents
        assertTrue(tree3.getRoot().getParent().isNilNode());
        assertEquals(3, tree3.getRoot().getLeft().getParent().getKey());
        assertEquals(3, tree3.getRoot().getRight().getParent().getKey());

        // Test leaves
        assertTrue(tree3.getRoot().getLeft().getLeft().isNilNode());
        assertTrue(tree3.getRoot().getLeft().getRight().isNilNode());
        assertTrue(tree3.getRoot().getRight().getLeft().isNilNode());
        assertTrue(tree3.getRoot().getRight().getRight().isNilNode());

        // Test tree3 colors
        assertEquals(Color.BLACK, tree3.getRoot().getColorEnum());
        assertEquals(Color.BLACK, tree3.getRoot().getLeft().getColorEnum());
        assertEquals(Color.BLACK, tree3.getRoot().getRight().getColorEnum());

        // Test size
        assertEquals(3, tree3.getSize());
    }

    @Test
    public void doubleBlackLeftLeftTest() {

        // Create tree3
        tree3.RBInsert(node5_t3);
        tree3.RBInsert(node3_t3);
        tree3.RBInsert(node6_t3);
        tree3.RBInsert(node2_t3);
        tree3.RBInsert(node4_t3);
        tree3.printTree();
        // Delete root.right
        tree3.RBDelete(node6_t3);

        // Test tree3 keys
        assertEquals(3, tree3.getRoot().getKey());
        assertEquals(2, tree3.getRoot().getLeft().getKey());
        assertEquals(5, tree3.getRoot().getRight().getKey());
        assertEquals(4, tree3.getRoot().getRight().getLeft().getKey());

        // Test tree3 parents
        assertTrue(tree3.getRoot().getParent().isNilNode());
        assertEquals(3, tree3.getRoot().getLeft().getParent().getKey());
        assertEquals(3, tree3.getRoot().getRight().getParent().getKey());
        assertEquals(5, tree3.getRoot().getRight().getLeft().getParent().getKey());

        // Test leaves
        assertTrue(tree3.getRoot().getLeft().getLeft().isNilNode());
        assertTrue(tree3.getRoot().getLeft().getRight().isNilNode());
        assertTrue(tree3.getRoot().getRight().getRight().isNilNode());
        assertTrue(tree3.getRoot().getRight().getLeft().getLeft().isNilNode());
        assertTrue(tree3.getRoot().getRight().getLeft().getRight().isNilNode());

        // Test tree3 colors
        assertEquals(Color.BLACK, tree3.getRoot().getColorEnum());
        assertEquals(Color.BLACK, tree3.getRoot().getLeft().getColorEnum());
        assertEquals(Color.BLACK, tree3.getRoot().getRight().getColorEnum());
        assertEquals(Color.RED, tree3.getRoot().getRight().getLeft().getColorEnum());

        // Test size
        System.out.println("4, Actual: " + tree3.getSize());
        tree3.printTree();
        ArrayList<Node> x = tree3.getNodesInOrder(tree3.getRoot());
        for(int i = 0; i < x.size(); i++) {
        	System.out.println(x.get(i).structuredToString());
        }
        assertEquals(4, tree3.getSize());
    }

    @Test
    public void doubleBlackRightRightTest() {

        // Create tree3
        tree3.RBInsert(node3_t3);
        tree3.RBInsert(node2_t3);
        tree3.RBInsert(node5_t3);
        tree3.RBInsert(node4_t3);
        tree3.RBInsert(node6_t3);

        // Delete root.left
        tree3.RBDelete(node2_t3);

        // Test tree3 keys
        assertEquals(5, tree3.getRoot().getKey());
        assertEquals(3, tree3.getRoot().getLeft().getKey());
        assertEquals(4, tree3.getRoot().getLeft().getRight().getKey());
        assertEquals(6, tree3.getRoot().getRight().getKey());

        // Test tree3 parents
        assertTrue(tree3.getRoot().getParent().isNilNode());
        assertEquals(5, tree3.getRoot().getLeft().getParent().getKey());
        assertEquals(5, tree3.getRoot().getRight().getParent().getKey());
        assertEquals(3, tree3.getRoot().getLeft().getRight().getParent().getKey());

        // Test leaves
        assertTrue(tree3.getRoot().getLeft().getLeft().isNilNode());
        assertTrue(tree3.getRoot().getLeft().getRight().getLeft().isNilNode());
        assertTrue(tree3.getRoot().getLeft().getRight().getRight().isNilNode());
        assertTrue(tree3.getRoot().getRight().getLeft().isNilNode());
        assertTrue(tree3.getRoot().getRight().getRight().isNilNode());

        // Test tree3 colors
        assertEquals(Color.BLACK, tree3.getRoot().getColorEnum());
        assertEquals(Color.BLACK, tree3.getRoot().getLeft().getColorEnum());
        assertEquals(Color.RED, tree3.getRoot().getLeft().getRight().getColorEnum());
        assertEquals(Color.BLACK, tree3.getRoot().getRight().getColorEnum());

        // Test size
        assertEquals(4, tree3.getSize());
    }

    @Test
    public void doubleBlackLeftRightTest() {

        // Create tree3
        tree3.RBInsert(node4_t3);
        tree3.RBInsert(node2_t3);
        tree3.RBInsert(node5_t3);
        tree3.RBInsert(node3_t3);

        // Delete root.right
        tree3.RBDelete(node5_t3);

        // Test tree3 keys
        assertEquals(3, tree3.getRoot().getKey());
        assertEquals(2, tree3.getRoot().getLeft().getKey());
        assertEquals(4, tree3.getRoot().getRight().getKey());

        // Test tree3 parents
        assertTrue(tree3.getRoot().getParent().isNilNode());
        assertEquals(3, tree3.getRoot().getLeft().getParent().getKey());
        assertEquals(3, tree3.getRoot().getRight().getParent().getKey());

        // Test leaves
        assertTrue(tree3.getRoot().getLeft().getLeft().isNilNode());
        assertTrue(tree3.getRoot().getLeft().getRight().isNilNode());
        assertTrue(tree3.getRoot().getRight().getLeft().isNilNode());
        assertTrue(tree3.getRoot().getRight().getRight().isNilNode());

        // Test tree3 colors
        assertEquals(Color.BLACK, tree3.getRoot().getColorEnum());
        assertEquals(Color.BLACK, tree3.getRoot().getLeft().getColorEnum());
        assertEquals(Color.BLACK, tree3.getRoot().getRight().getColorEnum());

        // Test size
        assertEquals(3, tree3.getSize());
    }

    @Test
    public void doubleBlackRightLeftTest() {

        // Create tree3
        tree3.RBInsert(node3_t3);
        tree3.RBInsert(node2_t3);
        tree3.RBInsert(node5_t3);
        tree3.RBInsert(node4_t3);

        // Delete root.left
        tree3.RBDelete(node2_t3);

        // Test tree3 keys
        assertEquals(4, tree3.getRoot().getKey());
        assertEquals(3, tree3.getRoot().getLeft().getKey());
        assertEquals(5, tree3.getRoot().getRight().getKey());

        // Test tree3 parents
        assertTrue(tree3.getRoot().getParent().isNilNode());
        assertEquals(4, tree3.getRoot().getLeft().getParent().getKey());
        assertEquals(4, tree3.getRoot().getRight().getParent().getKey());

        // Test leaves
        assertTrue(tree3.getRoot().getLeft().getLeft().isNilNode());
        assertTrue(tree3.getRoot().getLeft().getRight().isNilNode());
        assertTrue(tree3.getRoot().getRight().getLeft().isNilNode());
        assertTrue(tree3.getRoot().getRight().getRight().isNilNode());

        // Test tree3 colors
        assertEquals(Color.BLACK, tree3.getRoot().getColorEnum());
        assertEquals(Color.BLACK, tree3.getRoot().getLeft().getColorEnum());
        assertEquals(Color.BLACK, tree3.getRoot().getRight().getColorEnum());

        // Test size
        assertEquals(3, tree3.getSize());
    }

    @Test
    public void siblingAndBothChildrenBlackTest() {	//ERROR HERE

        // Create tree3
        tree3.RBInsert(node2_t3);
        tree3.RBInsert(node1_t3);
        tree3.RBInsert(node3_t3);
        tree3.getRoot().getLeft().setColor(Color.BLACK);
        tree3.getRoot().getRight().setColor(Color.BLACK);

        // Delete root.left
        tree3.RBDelete(node1_t3);

        // Test tree3 keys
        assertEquals(2, tree3.getRoot().getKey());
        assertEquals(3, tree3.getRoot().getRight().getKey());

        // Test tree3 parents
        assertTrue(tree3.getRoot().getParent().isNilNode());
        assertEquals(2, tree3.getRoot().getRight().getParent().getKey());

        // Test leaves
        assertTrue(tree3.getRoot().getLeft().isNilNode());
        assertTrue(tree3.getRoot().getRight().getLeft().isNilNode());
        assertTrue(tree3.getRoot().getRight().getRight().isNilNode());

        // Test tree3 colors
        assertEquals(Color.BLACK, tree3.getRoot().getColorEnum());
        assertEquals(Color.RED, tree3.getRoot().getRight().getColorEnum());

        // Test size
        assertEquals(2, tree3.getSize());
    }

    @Test
    public void siblingRedLeftTest() {

        // Create tree3
        tree3.RBInsert(node4_t3);
        tree3.RBInsert(node2_t3);
        tree3.RBInsert(node5_t3);
        tree3.RBInsert(node1_t3);
        tree3.RBInsert(node3_t3);
        tree3.getRoot().getLeft().setColor(Color.RED);
        tree3.getRoot().getLeft().getLeft().setColor(Color.BLACK);
        tree3.getRoot().getLeft().getRight().setColor(Color.BLACK);

        // Delete root.right
        tree3.RBDelete(node5_t3);

        // Test tree3 keys
        assertEquals(2, tree3.getRoot().getKey());
        assertEquals(1, tree3.getRoot().getLeft().getKey());
        assertEquals(4, tree3.getRoot().getRight().getKey());
        assertEquals(3, tree3.getRoot().getRight().getLeft().getKey());

        // Test tree3 parents
        assertTrue(tree3.getRoot().getParent().isNilNode());
        assertEquals(2, tree3.getRoot().getLeft().getParent().getKey());
        assertEquals(2, tree3.getRoot().getRight().getParent().getKey());
        assertEquals(4, tree3.getRoot().getRight().getLeft().getParent().getKey());

        // Test leaves
        assertTrue(tree3.getRoot().getLeft().getLeft().isNilNode());
        assertTrue(tree3.getRoot().getLeft().getRight().isNilNode());
        assertTrue(tree3.getRoot().getRight().getRight().isNilNode());
        assertTrue(tree3.getRoot().getRight().getLeft().getLeft().isNilNode());
        assertTrue(tree3.getRoot().getRight().getLeft().getRight().isNilNode());

        // Test tree3 colors
        assertEquals(Color.BLACK, tree3.getRoot().getColorEnum());
        assertEquals(Color.BLACK, tree3.getRoot().getLeft().getColorEnum());
        assertEquals(Color.BLACK, tree3.getRoot().getRight().getColorEnum());
        assertEquals(Color.RED, tree3.getRoot().getRight().getLeft().getColorEnum());

        // Test size
        assertEquals(4, tree3.getSize());
    }

    @Test
    public void siblingRedRightTest() {

        // Create tree3
        tree3.RBInsert(node2_t3);
        tree3.RBInsert(node1_t3);
        tree3.RBInsert(node4_t3);
        tree3.RBInsert(node3_t3);
        tree3.RBInsert(node5_t3);
        tree3.getRoot().getRight().setColor(Color.RED);
        tree3.getRoot().getRight().getLeft().setColor(Color.BLACK);
        tree3.getRoot().getRight().getRight().setColor(Color.BLACK);

        // Delete root.left
        tree3.RBDelete(node1_t3);

        // Test tree3 keys
        assertEquals(4, tree3.getRoot().getKey());
        assertEquals(2, tree3.getRoot().getLeft().getKey());
        assertEquals(3, tree3.getRoot().getLeft().getRight().getKey());
        assertEquals(5, tree3.getRoot().getRight().getKey());

        // Test tree3 parents
        assertTrue(tree3.getRoot().getParent().isNilNode());
        assertEquals(4, tree3.getRoot().getLeft().getParent().getKey());
        assertEquals(4, tree3.getRoot().getRight().getParent().getKey());
        assertEquals(2, tree3.getRoot().getLeft().getRight().getParent().getKey());

        // Test leaves
        assertTrue(tree3.getRoot().getLeft().getLeft().isNilNode());
        assertTrue(tree3.getRoot().getLeft().getRight().getLeft().isNilNode());
        assertTrue(tree3.getRoot().getLeft().getRight().getRight().isNilNode());
        assertTrue(tree3.getRoot().getRight().getLeft().isNilNode());
        assertTrue(tree3.getRoot().getRight().getRight().isNilNode());

        // Test tree3 colors
        assertEquals(Color.BLACK, tree3.getRoot().getColorEnum());
        assertEquals(Color.BLACK, tree3.getRoot().getLeft().getColorEnum());
        assertEquals(Color.RED, tree3.getRoot().getLeft().getRight().getColorEnum());
        assertEquals(Color.BLACK, tree3.getRoot().getRight().getColorEnum());

        // Test size
        assertEquals(4, tree3.getSize());
    }
}
