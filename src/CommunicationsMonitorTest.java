import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.fail;
/**

/**
 * 
 * @author Will Sartin
 *
 */
public class CommunicationsMonitorTest {

	@Test
	public void test() {
		fail("Not yet implemented");
	}
	
	@Test
	public void exampleOne_fromSpecSheet() {
		/*
		 * Suppose n = 4, and the trace data consists of triples
		 * (C1, C2, 4), (c2, c4, 8), (C3, C4, 8), (C1, C4, 12)
		 * and that the virus was inserted into computer C1 at time 2. 
		 * Then C3 would be infected at time 8 by a sequence of three steps
		 * 		1. c2 becomes infected at time 4, 
		 * 		2. c4 gets the virus from c2 at time 8
		 * 		3. c3 gets the cirus from c4 at time 8
		 */
		//TODO
	}
	
	@Test
	public void exampleTwo_fromSpecSheet() {
		/*
		 * Suppose c = 4, and the trace data consists of
		 * (c2, c3, 8), (c1, c4, 12), (c1, c2, 14)
		 * and that the virus was inserted into C1 at time 2.
		 * 		then c3 would NOT become infected during the
		 * 		period of observation.
		 * although c2 becomes infected at time 14, c3 only 
		 * communicates with c2 before c2 was infected. 
		 */
		
		//TODO
	}
	
	//@Required
	@Test
	public void addCommunicationTest() {
		//TODO
	}
	
	//@Required
	@Test
	public void createGraphTest() {
		//TODO
	}
	
	//@Required
	@Test
	public void queryInfectionTest() {
		//TODO
	}
	
	//@Required
	@Test
	public void getComputerMapping_OfListTest() {
		//TODO
	}
	
	//@Required
	@Test
	public void getComputerMapping_ofOneVertexTest() {
		//TODO
	}
	
}
