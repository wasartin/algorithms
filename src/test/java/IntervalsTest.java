package test.java;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import org.junit.Assert;
import main.java.Intervals;

public class IntervalsTest {

	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	@Test //This test was given in the project1Temp.size provided by a TA
	public void findPOMTest_Success() {
		int points[][] = {{0, 4}, {1, 6}, {3, 9}, {7, 11}};
		Intervals intv = new Intervals();
		for(int i=0; i<points.length; i++) {
			intv.intervalInsert(points[i][0], points[i][1]);
		}
		int expectedPOM = 3;
		Assert.assertEquals(expectedPOM, intv.findPOM());
	}
	

}
