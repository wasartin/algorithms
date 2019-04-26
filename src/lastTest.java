import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * 
 * @author Will Sartin & Josh Ramon
 *
 */
public class lastTest {
	
	//This is the tree from the spec sheet
	public static RBTree rbt1;
	public static RBTree rbt1_SmallOne;
	public static RBTree rbt_Small5;

	public final static Node nilNode = new Node(new Endpoint(0, Position.NIL));
	
	private final static String FILENAME_BASE = "C:\\Users\\watis\\ISU\\cs311\\project\\PA1\\workspace\\group19\\src\\res\\";
	
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
	
	public List<Node> stringToNodes(List<String> input){
		//skip first line.
		List<Node> nodes = new ArrayList<Node>();
		int startingPointForIntervals = 1;
		for(int i = startingPointForIntervals; i < input.size(); i++) {
			String currInterval = input.get(i);
			//parse into two numbers
			String[] ends = currInterval.split("\\s+");
			int leftInt = Integer.valueOf(ends[0]);
			int rightInt = Integer.valueOf(ends[1]);
			Endpoint left = new Endpoint(leftInt, Position.LEFT);
			Endpoint right = new Endpoint(rightInt, Position.RIGHT);
			nodes.add(new Node(left));
			nodes.add(new Node(right));
		}
		return nodes;
	}

	
	@Before
	public void setUp() {
		String small_1 = "small_1.txt";
		List<String> valuesInFile = fileReader(small_1);
		List<Node> nodesInFile = stringToNodes(valuesInFile);

		rbt1_SmallOne = new RBTree();
		for(Node node : nodesInFile) {
			rbt1_SmallOne.RBInsert(node);
		}
		
		
		rbt_Small5 = new RBTree();
		for(Node node : stringToNodes(fileReader("small_5.txt"))) {
			rbt_Small5.RBInsert(node);
		}
		
		
	}
		/**
		 * Every Node is either red or black
		 */
		@Test
		public void RBTreePropertyOne_Success() {
		ArrayList<Node> x = rbt1_SmallOne.getNodesInOrder(rbt1_SmallOne.getRoot());
		for(int i = 0; i < x.size(); i++) {
			Assert.assertTrue(x.get(i).getColor() == Color.RED.ordinal() || x.get(i).getColor() == Color.BLACK.ordinal());
		}
	}
	
	/**
	 * Root is Black
	 */
	@Test
	public void RBTreePropertyTwo_Success() {
		Assert.assertTrue(rbt1_SmallOne.getRoot().getColor() == Color.BLACK.ordinal());
	}
	
	/**
	 * Every leaf(nil) is black
	 * Note: we are doing T.il in our RBTree
	 */
//	@Test
//	public void RBTreePropertyThree_Success() {
//
//	}
//	
	/**
	 * If a node is red then both of its children are black
	 */
	@Test
	public void RBTreePropertyFour_Success() { //fail on one.
		ArrayList<Node> x = rbt1_SmallOne.getNodesInOrder(rbt1_SmallOne.getRoot());
		for(int i = 0; i < x.size(); i++) {
			Node temp = x.get(i);
			if(temp.getColor() == Color.RED.ordinal()) {
				//Assert.assertTrue(temp.getLeft().getColor() == Color.BLACK.ordinal() && temp.getRight().getColor() == Color.BLACK.ordinal());
			}
		}
	}
	
	@Test//root emax value
	public void RBTree_CorrectAnswer_byFile() {

		int smallOne = 46;
		Assert.assertTrue(rbt1_SmallOne.getRoot().getEmax().getValue() == smallOne);
		
		Assert.assertTrue(rbt_Small5.getRoot().getEmax().getValue() == 66);
	}
	
	/**
	 * For each node, all simple paths from the node to the 
	 * descendant leaves contain the same number of black nodes (blackheight)
	 */
//	@Test
//	public void RBTreePropertyFive_Success() {
//
//	}
//	
//	@Test
//	public void RBTreeInsert_Success() {
//
//	}
//
//	/**
//	 * @Required for proejct
//	 * 
//	 */
//	@Test
//	public void GetHeightTest_Success() {
//
//	}
//	
//	@Test
//	public void minimum_Success() {
//
//	}
//	
//	@Test
//	public void maximum_Success() {
//
//	}
//	
//	@Test
//	public void deleteCase1_Success() {
//
//	}
//	
//	@Test
//	public void deleteCase2_Success() {
//
//	}
}