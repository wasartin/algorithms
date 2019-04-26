package test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import main.Endpoint;
import main.Intervals;
import main.Node;
import main.Position;
import main.RBTree;

/**
 * 
 * @author Will Sartin & Josh Ramon
 *
 */
public class IntervalTest2 {
	
	@Rule
	public final ExpectedException thrown = ExpectedException.none();
	
	public static Intervals intervalEX;
	public Intervals intervals;
	
	
	public final static Node nilNode = new Node(new Endpoint(0, Position.NIL));
	private final static String FILENAME_BASE = "src\\res\\";
	
	@Before
	public void setUp() {
		intervalEX = new Intervals();
        intervals = new Intervals();
        
		int points[][] = {
							{0, 4}, 
							{1, 6}, 
							{3, 9}, 
							{7, 11}
							};
		for(int i=0; i<points.length; i++) {
			intervalEX.intervalInsert(points[i][0], points[i][1]);
		}
	}

	@Test
	public void small_1() {
		List<String> valuesInFile = fileReader("small_1.txt");
		List<Integer> possibleSolutions = possibleSolutions(valuesInFile.remove(0));
		Intervals intervals = createIntervals(valuesInFile);
		
		//System.out.println("Actual: " + intervals.findPOM() +", Choices: " + possibleSolutions.toString());
		assertTrue(possibleSolutions.contains(intervals.findPOM()));
	}
	
	@Test
	public void small_2() {
		List<String> valuesInFile = fileReader("small_2.txt");
		List<Integer> possibleSolutions = possibleSolutions(valuesInFile.remove(0));
		Intervals intervals = createIntervals(valuesInFile);
		
		//System.out.println("Actual: " + intervals.findPOM() +", Choices: " + possibleSolutions.toString());
		assertTrue(possibleSolutions.contains(intervals.findPOM()));
	}
	
	@Test
	public void small_3() {
		List<String> valuesInFile = fileReader("small_3.txt");
		List<Integer> possibleSolutions = possibleSolutions(valuesInFile.remove(0));
		Intervals intervals = createIntervals(valuesInFile);
		
		//System.out.println("Actual: " + intervals.findPOM() +", Choices: " + possibleSolutions.toString());
		assertTrue(possibleSolutions.contains(intervals.findPOM()));
	}

	@Test
	public void small_4() {
		List<String> valuesInFile = fileReader("small_4.txt");
		List<Integer> possibleSolutions = possibleSolutions(valuesInFile.remove(0));
		Intervals intervals = createIntervals(valuesInFile);
		
		//System.out.println("Actual: " + intervals.findPOM() +", Choices: " + possibleSolutions.toString());
		assertTrue(possibleSolutions.contains(intervals.findPOM()));
	}
	
	@Test
	public void smallFive() {
		List<String> valuesInFile = fileReader("small_5.txt");
		List<Integer> possibleSolutions = possibleSolutions(valuesInFile.remove(0));
		Intervals intervals = createIntervals(valuesInFile);
		
		//System.out.println("Actual: " + intervals.findPOM() +", Choices: " + possibleSolutions.toString());
		assertTrue(possibleSolutions.contains(intervals.findPOM()));
	}

	@Test
	public void med1() {
		List<String> valuesInFile = fileReader("medium_1.txt");
		List<Integer> possibleSolutions = possibleSolutions(valuesInFile.remove(0));
		Intervals intervals = createIntervals(valuesInFile);
		
		//System.out.println("Actual: " + intervals.findPOM() +", Choices: " + possibleSolutions.toString());
		assertTrue(possibleSolutions.contains(intervals.findPOM()));
	}

	@Test
	public void med2() {
		List<String> valuesInFile = fileReader("medium_2.txt");
		List<Integer> possibleSolutions = possibleSolutions(valuesInFile.remove(0));
		Intervals intervals = createIntervals(valuesInFile);
		
		//System.out.println("Actual: " + intervals.findPOM() +", Choices: " + possibleSolutions.toString());
		assertTrue(possibleSolutions.contains(intervals.findPOM()));
		
	}
	
	
	@Test
	public void med3() {
		List<String> valuesInFile = fileReader("medium_3.txt");
		List<Integer> possibleSolutions = possibleSolutions(valuesInFile.remove(0));
		Intervals intervals = createIntervals(valuesInFile);
		
		//System.out.println("Actual: " + intervals.findPOM() +", Choices: " + possibleSolutions.toString());
		assertTrue(possibleSolutions.contains(intervals.findPOM()));
	}
	
	@Test
	public void med4() {
		List<String> valuesInFile = fileReader("medium_4.txt");
		List<Integer> possibleSolutions = possibleSolutions(valuesInFile.remove(0));
		Intervals intervals = createIntervals(valuesInFile);
		
		//System.out.println("Actual: " + intervals.findPOM() +", Choices: " + possibleSolutions.toString());
		assertTrue(possibleSolutions.contains(intervals.findPOM()));
	}
	
	@Test
	public void med5() {
		List<String> valuesInFile = fileReader("medium_5.txt");
		List<Integer> possibleSolutions = possibleSolutions(valuesInFile.remove(0));
		Intervals intervals = createIntervals(valuesInFile);
		
		//System.out.println("Actual: " + intervals.findPOM() +", Choices: " + possibleSolutions.toString());
		assertTrue(possibleSolutions.contains(intervals.findPOM()));
	}
	
	@Test
	public void large1() {
		List<String> valuesInFile = fileReader("large_1.txt");
		List<Integer> possibleSolutions = possibleSolutions(valuesInFile.remove(0));
		Intervals intervals = createIntervals(valuesInFile);
		
		//System.out.println("Actual: " + intervals.findPOM() +", Choices: " + possibleSolutions.toString());
		assertTrue(possibleSolutions.contains(intervals.findPOM()));
	}
	
	@Test
	public void large2() {
		List<String> valuesInFile = fileReader("large_2.txt");
		List<Integer> possibleSolutions = possibleSolutions(valuesInFile.remove(0));
		Intervals intervals = createIntervals(valuesInFile);
		
		//System.out.println("Actual: " + intervals.findPOM() +", Choices: " + possibleSolutions.toString());
		assertTrue(possibleSolutions.contains(intervals.findPOM()));
	}
	
	@Test
	public void large3() {
		List<String> valuesInFile = fileReader("large_3.txt");
		List<Integer> possibleSolutions = possibleSolutions(valuesInFile.remove(0));
		Intervals intervals = createIntervals(valuesInFile);
		
		//System.out.println("Actual: " + intervals.findPOM() +", Choices: " + possibleSolutions.toString());
		assertTrue(possibleSolutions.contains(intervals.findPOM()));
	}
	
	@Test
	public void large4() {
		List<String> valuesInFile = fileReader("large_4.txt");
		List<Integer> possibleSolutions = possibleSolutions(valuesInFile.remove(0));
		Intervals intervals = createIntervals(valuesInFile);
		
		//System.out.println("Actual: " + intervals.findPOM() +", Choices: " + possibleSolutions.toString());
		assertTrue(possibleSolutions.contains(intervals.findPOM()));
	}
	
	@Test
	public void large5() {
		List<String> valuesInFile = fileReader("large_5.txt");
		List<Integer> possibleSolutions = possibleSolutions(valuesInFile.remove(0));
		Intervals intervals = createIntervals(valuesInFile);
		
		//System.out.println("Actual: " + intervals.findPOM() +", Choices: " + possibleSolutions.toString());
		assertTrue(possibleSolutions.contains(intervals.findPOM()));
	}
	
//	@Test
//	public void intervalInsert_Fail1() throws IllegalArgumentException {
//		thrown.expect(IllegalArgumentException.class);
//		thrown.expectMessage("The first point must precede the second point");
//		Intervals intv = new Intervals();
//		intv.intervalInsert(4, 1);
//	}
//	
//	@Test
//	public void findPOM_Success() {
//		int expectedPOM = 3;
//		int actualPOM = intervalEX.findPOM();
//		Assert.assertEquals(expectedPOM, actualPOM);
//	}
//	
//	@Test
//	public void findPOM_Success2() {
//		Intervals i = new Intervals();
//
//		i.intervalInsert(1, 50);
//		i.intervalInsert(2, 49);
//		i.intervalInsert(3, 48);
//		i.intervalInsert(4, 47);
//		i.intervalInsert(5, 46);
//		i.intervalInsert(99, 100);
//		i.intervalInsert(99, 100);
//		i.intervalInsert(99, 100);
//		i.intervalInsert(101, 102);
//
//		assertEquals(i.findPOM(), 5);
//	}
//	
//	@Test
//	public void findPOM_Success3() {
//		Intervals i = new Intervals();
//
//		i.intervalInsert(4, 5);
//		i.intervalInsert(0, 5);
//		i.intervalInsert(5, 7);
//		assertEquals(i.findPOM(), 4);
//	}
//	
//	@Test
//	public void findPOM_Success4() {
//		Intervals i = new Intervals();
//
//		i.intervalInsert(4, 5);
//		i.intervalInsert(5, 7);
//		i.intervalInsert(3, 8);
//
//		assertEquals(i.findPOM(), 5);
//	}
//	
//	@Test
//	public void findPOM_Success5() {
//		Intervals i = new Intervals();
//
//		i.intervalInsert(1, 2);
//		i.intervalInsert(2, 4);
//		i.intervalInsert(1, 7);
//		i.intervalInsert(2, 4);
//
//		assertEquals(i.findPOM(), 2);
//	}
//	
//	@Test
//	public void intervalDelete_Fail1(){
//		Intervals intv = new Intervals();
//		intv.intervalInsert(1, 4);
//		Assert.assertFalse("Exception thrown and caught, this is out of bounds of the current collection of Intervals", intv.intervalDelete(2));
//	}
//	
//	@Test
//	public void intervalDelete_Fail2(){
//		Intervals intv = new Intervals();
//		intv.intervalInsert(1, 4);
//		Assert.assertFalse("Indexing start at 1", intv.intervalDelete(0));
//	}
//	
//	@Test 
//	public void intervalDelete_Fail3(){
//		Intervals intv = new Intervals();
//		intv.intervalInsert(1, 4);
//		intv.intervalDelete(1);
//		Assert.assertFalse("This has already been deleted", intv.intervalDelete(1));
//	}
//	
//	@Test
//	public void intervalDelete_Success1() {		
//		Assert.assertTrue(intervalEX.intervalDelete(1));
//		RBTree t = intervalEX.getRBTree();
//		Assert.assertTrue(t.getSize() == 6);
//	}
//    @Test
//    public void testIntervalInsert() {
//
//        // Test if one insert works
//        intervals.intervalInsert(1, 3);
//        RBTree tree = intervals.getRBTree();
//        assertEquals(tree.getSize(), 2);
//        assertEquals(tree.getRoot().getKey(), 1);
//        assertEquals(tree.getRoot().getRight().getKey(), 3);
//        assertTrue(tree.getRoot().getLeft().isNilNode());
//
//        // Set up example from documentation
//
//        // Test all nodes were inserted
//        tree = intervalEX.getRBTree();
//        assertEquals(tree.getSize(), 8);
//
//        // Test left subtree
//        assertEquals(tree.getRoot().getKey(), 4);
//        assertEquals(tree.getRoot().getLeft().getKey(), 1);
//        assertEquals(tree.getRoot().getLeft().getLeft().getKey(), 0);
//        assertEquals(tree.getRoot().getLeft().getRight().getKey(), 3);
//
//        // Test right subtree
//        assertEquals(tree.getRoot().getRight().getKey(), 7);
//        assertEquals(tree.getRoot().getRight().getLeft().getKey(), 6);
//        assertEquals(tree.getRoot().getRight().getRight().getKey(), 9);
//        assertEquals(tree.getRoot().getRight().getRight().getRight().getKey(), 11);
//    }
//
//    @Test
//    public void testNodeValCalculation() {
//        RBTree tree = intervalEX.getRBTree();
//
//        // Test left subtree
//        assertEquals(tree.getRoot().getVal(), 0);
//        assertEquals(tree.getRoot().getLeft().getVal(), 3);
//        assertEquals(tree.getRoot().getLeft().getLeft().getVal(), 1);
//        assertEquals(tree.getRoot().getLeft().getRight().getVal(), 1);
//
//        // Test right subtree
//        assertEquals(tree.getRoot().getRight().getVal(), -2);
//        assertEquals(tree.getRoot().getRight().getLeft().getVal(), -1);
//        assertEquals(tree.getRoot().getRight().getRight().getVal(), -2);
//        assertEquals(tree.getRoot().getRight().getRight().getRight().getVal(), -1);
//    }
//
//    @Test
//    public void testNodeMaxvalCalculation() {
//        RBTree tree = intervalEX.getRBTree();
//
//        // Test left subtree
//        assertEquals(3, tree.getRoot().getMaxVal());
//        assertEquals(3, tree.getRoot().getLeft().getMaxVal());
//        assertEquals(1, tree.getRoot().getLeft().getLeft().getMaxVal());
//        assertEquals(1, tree.getRoot().getLeft().getRight().getMaxVal());
//
//        // Test right subtree
//        assertEquals(0, tree.getRoot().getRight().getMaxVal());
//        assertEquals(0, tree.getRoot().getRight().getLeft().getMaxVal());
//        assertEquals(0, tree.getRoot().getRight().getRight().getMaxVal());
//        assertEquals(0, tree.getRoot().getRight().getRight().getRight().getMaxVal());
//    }
//
//    @Test
//    public void testNodeEmaxCalculation() { //ERROR currently here
//        RBTree tree = intervalEX.getRBTree();
//        Endpoint nilEndpoint = tree.getNILNode().getEndpoint();
//
//        // Test left subtree
//        assertEquals(3, tree.getRoot().getEmax().getValue());
//        assertEquals(3, tree.getRoot().getLeft().getEmax().getValue());
//        assertEquals(0, tree.getRoot().getLeft().getLeft().getEmax().getValue());
//        assertEquals(3, tree.getRoot().getLeft().getRight().getEmax().getValue());
//        
//        // Test right subtree
//        assertEquals(7, tree.getRoot().getRight().getEmax().getValue());
//        assertEquals(nilEndpoint, tree.getRoot().getRight().getLeft().getEmax());
//        assertEquals(nilEndpoint, tree.getRoot().getRight().getRight().getEmax());
//        assertEquals(nilEndpoint, tree.getRoot().getRight().getRight().getRight().getEmax());
//    }
//
//    @Test
//    public void testFindPOM() {
//        // Simple test
//        intervals.intervalInsert(1, 3);
//        intervals.intervalInsert(2, 4);
//        assertEquals(2, intervals.findPOM());
//        intervals.intervalInsert(6, 10);
//        intervals.intervalInsert(5, 11);
//        intervals.intervalInsert(6, 8);
//       
//        assertEquals(6, intervals.findPOM());
//    }
//
//    @Test
//    public void testGetRBTree() {
//        // Should return an empty tree
//        assertEquals(intervals.getRBTree().getSize(), 0);
//        assertTrue(intervals.getRBTree().getRoot().isNilNode());
//    }

	
	public List<String> fileReader(String file_ext){
		BufferedReader input;
		ArrayList<String> lines = new ArrayList<String>();
		try {
			input = new BufferedReader(new FileReader(new File(FILENAME_BASE+file_ext)));
			String line = null;
			while ((line = input.readLine()) != null) {
				lines.add(line);
			}
			input.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return lines;
	}
	
	public int[][] stringsToInt(List<String> input){
		int[][] endPointAsInts = new int[input.size()][2];
		for(int row = 0; row < input.size(); row++) {
			String toParse = input.get(row);
			String[] ends = toParse.split("\\s+");
			int leftInt = Integer.valueOf(ends[0]);
			int rightInt = Integer.valueOf(ends[1]);
			endPointAsInts[row][0] = leftInt;
			endPointAsInts[row][1] = rightInt;
		}
		return endPointAsInts;
	}
	
	private Intervals createIntervals(List<String> valuesInFile) {
		int[][] endpoints = stringsToInt(valuesInFile);
		Intervals result = new Intervals();
		for(int i = 0; i < endpoints.length; i++) {
			result.intervalInsert(endpoints[i][0], endpoints[i][1]);
		}
		return result;
	}

	private List<Integer> possibleSolutions(String input){
		List<Integer> result = new ArrayList<Integer>();
		String[] holder = input.split("\\s+");
		for(int i = 0; i < holder.length; i++) {
			result.add(Integer.valueOf(holder[i]));
		}
		return result;
	}
	
}
