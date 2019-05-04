package project.pa1.test;

import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import project.pa1.main.Endpoint;
import project.pa1.main.Node;
import project.pa1.main.Position;
import project.pa1.main.RBTree;

public class RBTreeTests_LargeDataSet {
	
	
	private final static String FILENAME_BASE = "src\\res\\";
	
	/**
	 * Private helper methods to read files for testing
	 */
	
	private List<String> fileReader(String file_ext){
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
	
	private List<Node> stringToNodes(List<String> input){
		List<Node> nodes = new ArrayList<Node>();
		for(String currInterval : input) {
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
	
	private RBTree createTreeFromFile(List<String> valuesInFile) {
		List<Node> nodesInFile = stringToNodes(valuesInFile);

		RBTree result = new RBTree();
		result = new RBTree();
		for(Node node : nodesInFile) {
			result.RBInsert(node);
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
	
	@Test
	public void smallOne() {
		List<String> valuesInFile = fileReader("small_1.txt");
		List<Integer> possibleSolutions = possibleSolutions(valuesInFile.remove(0));
		RBTree tree = createTreeFromFile(valuesInFile);
		
		//System.out.println("Actual: " + tree.getRoot().getEmax().getValue() +", Choices: " + possibleSolutions.toString());
		assertTrue(possibleSolutions.contains(tree.getRoot().getEmax().getValue()));
	}

	@Test
	public void small_2() {
		List<String> valuesInFile = fileReader("small_2.txt");
		List<Integer> possibleSolutions = possibleSolutions(valuesInFile.remove(0));
		RBTree tree = createTreeFromFile(valuesInFile);
		
		//System.out.println("Actual: " + tree.getRoot().getEmax().getValue() +", Choices: " + possibleSolutions.toString());
		assertTrue(possibleSolutions.contains(tree.getRoot().getEmax().getValue()));
	}
	
	@Test
	public void small_3() {
		List<String> valuesInFile = fileReader("small_3.txt");
		List<Integer> possibleSolutions = possibleSolutions(valuesInFile.remove(0));
		RBTree tree = createTreeFromFile(valuesInFile);
		
		//System.out.println("Actual: " + tree.getRoot().getEmax().getValue() +", Choices: " + possibleSolutions.toString());
		assertTrue(possibleSolutions.contains(tree.getRoot().getEmax().getValue()));
	}
	
	@Test
	public void small_4() {
		List<String> valuesInFile = fileReader("small_4.txt");
		List<Integer> possibleSolutions = possibleSolutions(valuesInFile.remove(0));
		RBTree tree = createTreeFromFile(valuesInFile);
		
		//System.out.println("Actual: " + tree.getRoot().getEmax().getValue() +", Choices: " + possibleSolutions.toString());
		assertTrue(possibleSolutions.contains(tree.getRoot().getEmax().getValue()));
	}
	
	@Test
	public void small_5() {
		List<String> valuesInFile = fileReader("small_5.txt");
		List<Integer> possibleSolutions = possibleSolutions(valuesInFile.remove(0));
		RBTree tree = createTreeFromFile(valuesInFile);
		
		//System.out.println("Actual: " + tree.getRoot().getEmax().getValue() +", Choices: " + possibleSolutions.toString());
		assertTrue(possibleSolutions.contains(tree.getRoot().getEmax().getValue()));
	}
	
	/**
	 * From the TA result set
	 */
	
	@Test
	public void medium_1() {
		List<String> valuesInFile = fileReader("medium_1.txt");
		List<Integer> possibleSolutions = possibleSolutions(valuesInFile.remove(0));
		RBTree tree = createTreeFromFile(valuesInFile);
		
		//System.out.println("Actual: " + tree.getRoot().getEmax().getValue() +", Choices: " + possibleSolutions.toString());
		assertTrue(possibleSolutions.contains(tree.getRoot().getEmax().getValue()));
	}
	
	@Test
	public void medium_2() {
		List<String> valuesInFile = fileReader("medium_2.txt");
		List<Integer> possibleSolutions = possibleSolutions(valuesInFile.remove(0));
		RBTree tree = createTreeFromFile(valuesInFile);
		
		//System.out.println("Actual: " + tree.getRoot().getEmax().getValue() +", Choices: " + possibleSolutions.toString());
		assertTrue(possibleSolutions.contains(tree.getRoot().getEmax().getValue()));
	}
}
