package test.java;

import main.java.Endpoint;
import main.java.Intervals;
import main.java.RBTree;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * 
 * @author Will Sartin & Josh Ramon
 *
 */
public class IntervalsTest {
	
	@Rule
	public final ExpectedException thrown = ExpectedException.none();

	@Test
	public void intervalInsert_Fail1() throws IllegalArgumentException {
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("The first point must precede the second point");
		Intervals intv = new Intervals();
		intv.intervalInsert(4, 1);
	}
	
	@Test
	public void findPOM_Success() {
		int points[][] = {{0, 4}, {1, 6}, {3, 9}, {7, 11}};
		Intervals intv = new Intervals();
		for(int i=0; i<points.length; i++) {
			intv.intervalInsert(points[i][0], points[i][1]);
		}
		int expectedPOM = 3;
		int actualPOM = intv.findPOM();
		System.out.println("E: 3, actual: " +actualPOM);
		Assert.assertEquals(expectedPOM, actualPOM);
	}
	
	

    // Instance variables
    private Intervals intervals;

    @Before
    public void initialize() {
        intervals = new Intervals();
    }

    @Test
    public void testIntervalInsert() {

        // Test if one insert works
        intervals.intervalInsert(1, 3);
        RBTree tree = intervals.getRBTree();
        assertEquals(tree.getSize(), 2);
        assertEquals(tree.getRoot().getKey(), 1);
        assertEquals(tree.getRoot().getRight().getKey(), 3);
        assertTrue(tree.getRoot().getLeft().isNilNode());

        // Set up example from documentation
        intervals = new Intervals();
        insertIntervalsFromExample();

        // Test all nodes were inserted
        tree = intervals.getRBTree();
        assertEquals(tree.getSize(), 8);

        // Test left subtree
        assertEquals(tree.getRoot().getKey(), 4);
        assertEquals(tree.getRoot().getLeft().getKey(), 1);
        assertEquals(tree.getRoot().getLeft().getLeft().getKey(), 0);
        assertEquals(tree.getRoot().getLeft().getRight().getKey(), 3);

        // Test right subtree
        assertEquals(tree.getRoot().getRight().getKey(), 7);
        assertEquals(tree.getRoot().getRight().getLeft().getKey(), 6);
        assertEquals(tree.getRoot().getRight().getRight().getKey(), 9);
        assertEquals(tree.getRoot().getRight().getRight().getRight().getKey(), 11);
    }

    @Test
    public void testNodeValCalculation() {
        insertIntervalsFromExample();
        RBTree tree = intervals.getRBTree();

        // Test left subtree
        assertEquals(tree.getRoot().getVal(), 0);
        assertEquals(tree.getRoot().getLeft().getVal(), 3);
        assertEquals(tree.getRoot().getLeft().getLeft().getVal(), 1);
        assertEquals(tree.getRoot().getLeft().getRight().getVal(), 1);

        // Test right subtree
        assertEquals(tree.getRoot().getRight().getVal(), -2);
        assertEquals(tree.getRoot().getRight().getLeft().getVal(), -1);
        assertEquals(tree.getRoot().getRight().getRight().getVal(), -2);
        assertEquals(tree.getRoot().getRight().getRight().getRight().getVal(), -1);
    }

    @Test
    public void testNodeMaxvalCalculation() {
        insertIntervalsFromExample();
        RBTree tree = intervals.getRBTree();

        // Test left subtree
        assertEquals(3, tree.getRoot().getMaxVal());
        assertEquals(3, tree.getRoot().getLeft().getMaxVal());
        assertEquals(1, tree.getRoot().getLeft().getLeft().getMaxVal());
        assertEquals(1, tree.getRoot().getLeft().getRight().getMaxVal());

        // Test right subtree
        assertEquals(0, tree.getRoot().getRight().getMaxVal());
        assertEquals(0, tree.getRoot().getRight().getLeft().getMaxVal());
        assertEquals(0, tree.getRoot().getRight().getRight().getMaxVal());
        assertEquals(0, tree.getRoot().getRight().getRight().getRight().getMaxVal());
    }

    @Test
    public void testNodeEmaxCalculation() { //ERROR currently here
        insertIntervalsFromExample();
        RBTree tree = intervals.getRBTree();
        Endpoint nilEndpoint = tree.getNILNode().getEndpoint();

        // Test left subtree
        assertEquals(3, tree.getRoot().getEmax().getValue());
        assertEquals(3, tree.getRoot().getLeft().getEmax().getValue());
        assertEquals(0, tree.getRoot().getLeft().getLeft().getEmax().getValue());
        assertEquals(3, tree.getRoot().getLeft().getRight().getEmax().getValue());

        // Test right subtree
        assertEquals(0, tree.getRoot().getRight().getEmax().getValue());
        assertEquals(nilEndpoint, tree.getRoot().getRight().getLeft().getEmax());
        assertEquals(nilEndpoint, tree.getRoot().getRight().getRight().getEmax());
        assertEquals(nilEndpoint, tree.getRoot().getRight().getRight().getRight().getEmax());
    }

    @Test
    public void testFindPOM() {

        // Simple test
        intervals.intervalInsert(1, 3);
        intervals.intervalInsert(2, 4);
        assertEquals(2, intervals.findPOM());
        intervals.intervalInsert(6, 10);
        intervals.intervalInsert(5, 11);
        intervals.intervalInsert(6, 8);
        System.out.println("Expected POM:6, actual:" + intervals.findPOM()); 
        
        intervals.getRBTree().printTree();
        assertEquals(6, intervals.findPOM());

        // Test from example
        intervals = new Intervals();
        insertIntervalsFromExample();
        assertEquals(3, intervals.findPOM());
    }

    @Test
    public void testGetRBTree() {
        // Should return an empty tree
        assertEquals(intervals.getRBTree().getSize(), 0);
        assertTrue(intervals.getRBTree().getRoot().isNilNode());
    }

    /**
     * Helper method to insert the points from the example
     * in the documentation
     */
    private void insertIntervalsFromExample() {
        int[][] points = {{0, 4}, {1, 6}, {3, 9}, {7, 11}};
        for(int i=0; i<points.length; i++) {
            //System.out.println("Inserting: "+ Arrays.toString(points[i]));
            intervals.intervalInsert(points[i][0], points[i][1]);
        }
    }
}
