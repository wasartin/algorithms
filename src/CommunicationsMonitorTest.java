
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
/**
 * 
 * @author Will Sartin, Josh Ramon & some anonymous person on Piazza
 *
 */
public class CommunicationsMonitorTest {
	
	/**
	 * Random Guy code starts here
	 */
	
    private static CommunicationsMonitor cm;
    private static ArrayList<Communication> communications;

    @BeforeAll
    public static void before() {
        cm = new CommunicationsMonitor();
        cm.addCommunication(1, 2, 4);
        cm.addCommunication(2, 4, 8);
        cm.addCommunication(3, 4, 8);
        cm.addCommunication(1, 4, 12);
        communications = (ArrayList<Communication>) cm.getCommunications();
    }
    
    @Test
    public void computerMappingToStringTest() {
//    	cm.createGraph();
//    	System.out.println(cm.computerMappingToString());
//    	System.out.println(cm.communicationLinksToString());
    }

    @Test
    public void testComputerNodeEquality() {
        ComputerNode t1 = new ComputerNode(1, 5);
        ComputerNode t2 = new ComputerNode(1, 5);
        ComputerNode t3 = new ComputerNode(2, 5);

        assertFalse(t1.equals(null));

        assertTrue(t1.equals(t2));
        assertFalse(t1.equals(t3));
    }


    @Test
    public void testAddCommunication() {
        assertEquals(4, communications.size());
        assertEquals(1, communications.get(0).c1);
        assertEquals(2, communications.get(1).c1);
        assertEquals(3, communications.get(2).c1);
        assertEquals(1, communications.get(3).c1);

        assertEquals(2, communications.get(0).c2);
        assertEquals(4, communications.get(1).c2);
        assertEquals(4, communications.get(2).c2);
        assertEquals(4, communications.get(3).c2);

        assertEquals(4, communications.get(0).timestamp);
        assertEquals(8, communications.get(1).timestamp);
        assertEquals(8, communications.get(2).timestamp);
        assertEquals(12, communications.get(3).timestamp);
    }
    
    @Test
    public void testAddAfterGraph() {
        cm.createGraph();
        cm.addCommunication(6, 7, 3);
        assertEquals(4, cm.getCommunications().size());
    }

    @Test //Current
    public void testCreateGraph() {
        cm.createGraph();
        HashMap<Integer, List<ComputerNode>> adjList = cm.getComputerMapping();
        //C1
        List<ComputerNode> c1 = adjList.get(1);
        assertEquals(2, c1.size());
        //C1[0]
        
        List<ComputerNode> c1Neighbors = c1.get(0).getOutNeighbors();
        assertEquals(2, c1Neighbors.size());
        assertEquals(2, c1Neighbors.get(0).getID());
        assertEquals(4, c1Neighbors.get(0).getTimestamp());
        assertEquals(1, c1Neighbors.get(1).getID());
        assertEquals(12, c1Neighbors.get(1).getTimestamp());
        //C1[1]
        c1Neighbors = c1.get(1).getOutNeighbors();
        assertEquals(1, c1Neighbors.size());
        assertEquals(4, c1Neighbors.get(0).getID());
        assertEquals(12, c1Neighbors.get(0).getTimestamp());

        //C2
        List<ComputerNode> c2 = adjList.get(2);
        assertEquals(2, c2.size());
        //C2[0]
        List<ComputerNode> c2Neighbors = c2.get(0).getOutNeighbors();
        assertEquals(2, c2Neighbors.size());
        assertEquals(1, c2Neighbors.get(0).getID());
        assertEquals(4, c2Neighbors.get(0).getTimestamp());
        assertEquals(2, c2Neighbors.get(1).getID());
        assertEquals(8, c2Neighbors.get(1).getTimestamp());
        //C2[1]
        c2Neighbors = c2.get(1).getOutNeighbors();
        assertEquals(1, c2Neighbors.size());
        assertEquals(4, c2Neighbors.get(0).getID());
        assertEquals(8, c2Neighbors.get(0).getTimestamp());

        //C3
        List<ComputerNode> c3 = adjList.get(3);
        assertEquals(1, c3.size());
        //C3[0]
        List<ComputerNode> c3Neighbors = c3.get(0).getOutNeighbors();
        assertEquals(1, c3Neighbors.size());
        assertEquals(4, c3Neighbors.get(0).getID());
        assertEquals(8, c3Neighbors.get(0).getTimestamp());

        //C4
        List<ComputerNode> c4 = adjList.get(4);
        assertEquals(2, c4.size());
        //C4[0]
        List<ComputerNode> c4Neighbors = c4.get(0).getOutNeighbors();
        assertEquals(3, c4Neighbors.size());
        
        assertEquals(2, c4Neighbors.get(0).getID());
        assertEquals(8, c4Neighbors.get(0).getTimestamp());
        
        assertEquals(3, c4Neighbors.get(1).getID());
        assertEquals(8, c4Neighbors.get(1).getTimestamp());
        
        assertEquals(4, c4Neighbors.get(2).getID());
        assertEquals(12, c4Neighbors.get(2).getTimestamp());
        //C4[1]
        c4Neighbors = c4.get(1).getOutNeighbors();
        assertEquals(1, c4Neighbors.size());
        assertEquals(1, c4Neighbors.get(0).getID());
        assertEquals(12, c4Neighbors.get(0).getTimestamp());

    }

    @Test
    public void testQueryInfectionExample1() {
        cm.createGraph();
        List<ComputerNode> path = cm.queryInfection(1, 3, 2, 9);
        HashMap<Integer, List<ComputerNode>> adjList = cm.getComputerMapping();
        System.out.println(cm.infectedPathToString(path));
        assertEquals(adjList.get(1).get(0), path.get(0));
        assertEquals(adjList.get(2).get(0), path.get(1));
        assertEquals(adjList.get(2).get(1), path.get(2));
        assertEquals(adjList.get(4).get(0), path.get(3));
        assertEquals(adjList.get(3).get(0), path.get(4));
    }

    @Test
    public void testQueryInfectionNoNode() {
        cm.createGraph();
        List<ComputerNode> path = cm.queryInfection(1, 3, 2, 9);
        path = cm.queryInfection(10, 4, 0, 10);
        assertEquals(null, path);
    }

    @Test
    public void testQueryInfectionNoPath() {
        cm.createGraph();
        List<ComputerNode> path = cm.queryInfection(1, 3, 2, 9);

        path = cm.queryInfection(1, 4, 2, 7);
        assertEquals(null, path);
    }

    @Test
    public void testQueryInfectionShortPath() {
        cm.createGraph();
        List<ComputerNode> path = cm.queryInfection(1, 3, 2, 9);
        HashMap<Integer, List<ComputerNode>> adjList = cm.getComputerMapping();

        path = cm.queryInfection(1, 4, 2, 8);
        assertEquals(adjList.get(1).get(0), path.get(0));
        assertEquals(adjList.get(2).get(0), path.get(1));
        assertEquals(adjList.get(2).get(1), path.get(2));
        assertEquals(adjList.get(4).get(0), path.get(3));
    }

    /**
     * End of Random Guy Code
     */
  
	@Test
	public void exampleOne_fromSpecSheet() {
		CommunicationsMonitor cm2 = new CommunicationsMonitor();
        cm2.addCommunication(1, 2, 4);
        cm2.addCommunication(2, 4, 8);
        cm2.addCommunication(3, 4, 8);
        cm2.addCommunication(1, 4, 12);
        List<Communication> communications2 = (ArrayList<Communication>) cm.getCommunications();
        
        cm2.createGraph();
        List<ComputerNode> actualPath = cm2.queryInfection(1, 3, 2, 8);
        

        List<ComputerNode> expectedPath = new ArrayList<ComputerNode>() {{
        	add(new ComputerNode(1, 4));
        	add(new ComputerNode(2, 4));
        	add(new ComputerNode(2, 8));
        	add(new ComputerNode(4, 8));
        	add(new ComputerNode(3, 8));
        }};
        
        List<ComputerNode> otherMethod = cm2.getInfectedPath();
        //System.out.println(cm2.infectedPathToString(otherMethod));
        
        for(int i = 0; i < actualPath.size(); i++) {
        	//System.out.println("Expected Node:" +expectedPath.get(i) + ", actual:" +otherMethod.get(i));
        	assertEquals(expectedPath.get(i), otherMethod.get(i));
        }
       //System.out.println("ExpectedSize: " + expectedPath.size() + ", actual:" +otherMethod.size());
        assertEquals(expectedPath.size(), otherMethod.size());
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
