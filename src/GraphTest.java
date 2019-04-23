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
 * Test class to run general tests on graphs, such as BFS and DFS
 * @author watis
 *
 */
public class GraphTest {

    private static CommunicationsMonitor cm;
    private static ArrayList<Communication> communications;

    @BeforeAll
    public static void before() { //From Spec sheet example
        cm = new CommunicationsMonitor();
        cm.addCommunication(1, 2, 4);
        cm.addCommunication(2, 4, 8);
        cm.addCommunication(3, 4, 8);
        cm.addCommunication(1, 4, 12);
        communications = (ArrayList<Communication>) cm.getCommunications();
    }
    
    @Test
    public void BFS_Test() {
    	
    }
    
    @Test
    public void DFS_Test() {
    	
    }
    
    
    
}
