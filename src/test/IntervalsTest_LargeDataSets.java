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
public class IntervalsTest_LargeDataSets {
	
	@Rule
	public final ExpectedException thrown = ExpectedException.none();
	
	public final static Node nilNode = new Node(new Endpoint(0, Position.NIL));
	private final static String FILENAME_BASE = "src\\res\\";
	
	@Before
	public void setUp() {

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
