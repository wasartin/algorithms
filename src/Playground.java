import java.util.ArrayList;
import java.util.List;

public class Playground {

	public static void main(String args[]) {
		CommunicationsMonitor cm;
		ArrayList<Communication> communications;
		cm = new CommunicationsMonitor();
		cm.addCommunication(1, 2, 4);
		cm.addCommunication(2, 4, 8);
		cm.addCommunication(3, 4, 8);
		cm.addCommunication(1, 4, 12);
		communications = (ArrayList<Communication>) cm.getCommunications();
		cm.createTimeMapping();
		List<ComputerNode> aList = cm.weirdQueryInfection(1, 3, 2, 8);
		for(ComputerNode n : aList) {
			System.out.println(n.toString());
		}
	}
}
