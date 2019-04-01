import org.junit.rules.ExpectedException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.junit.Before;
import org.junit.Rule;

/**
 * 
 * @author Will Sartin & Josh Ramon
 *
 */
public class EndpointTest {
	
	@Rule
	public final ExpectedException thrown = ExpectedException.none();

	@Before
	public void setUp() {
		
	}
	
	@Test
	public void constructorTest_Success() {
		Endpoint left = new Endpoint(1, Position.LEFT);
		Endpoint right = new Endpoint(2, Position.RIGHT);
		Endpoint nil = new Endpoint(0, Position.NIL);
	}
	
	@Test
	public void constructorTest_Fail() {
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("The value of p can only be -1 (right end point), 1 (left endpoint), and 0 (nil node)");
		Endpoint failure = new Endpoint(4, 2);
	}
	
	@Test
	public void constructorTest_Fail2() {
		thrown.expect(IllegalArgumentException.class);
		thrown.expectMessage("The value of p can only be -1 (right end point), 1 (left endpoint), and 0 (nil node)");
		Endpoint failure = new Endpoint(4, -2);
	}
	
	@Test
	public void getValueTest() {
		Endpoint left = new Endpoint(1, 1);
		Endpoint right = new Endpoint(2, -1);
		Endpoint nil = new Endpoint(0, 0);
		Assert.assertTrue(left.getValue() == 1);
		Assert.assertTrue(right.getValue() == 2);
		Assert.assertTrue(nil.getValue() == 0);
	}
	
	@Test
	public void getPValueTest() {
		Endpoint left = new Endpoint(1, 1);
		Endpoint right = new Endpoint(2, -1);
		Endpoint nil = new Endpoint(0, 0);
		Assert.assertTrue(left.getPValue() == Position.LEFT);
		Assert.assertTrue(right.getPValue() == Position.RIGHT);
		Assert.assertTrue(nil.getPValue() == Position.NIL);
	}

}
