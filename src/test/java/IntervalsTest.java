package test.java;

import main.java.Intervals;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

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
		Assert.assertEquals(expectedPOM, actualPOM);
	}
}
