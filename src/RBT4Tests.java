//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertTrue;
//
//import java.util.ArrayList;
//
//import org.junit.BeforeClass;
//import org.junit.Test;
//
//import Intervals.Interval;
//
/**
 * 
 * @author By people from the Piazza JUNIT post
 *
 */
//public class RBT4Tests {
//
//	private static final int SIZE = 100000;
//	private static final double RANGE = 10000.0;
//
//	private static ArrayList<Interval> intervals = new ArrayList<>();
//	private static ArrayList<Interval> possible = new ArrayList<>();
//
////	@BeforeClass
////	public static void setup() {
////		ArrayList<Interval> active = new ArrayList<>();
////
////		//Create large random dataset
////		for (int i = 0; i < SIZE; i++) {
////			int a = (int) (Math.random() * RANGE);
////			int b = (int) (Math.random() * RANGE);
////			if (a == b) continue;
////
////			if (a > b) {
////				int temp = a;
////				a = b;
////				b = temp;
////			}
////			
////			intervals.add(new Interval(a, b));
////		}
////
////		//Very naïve implementation of the project
////		int maxOpen = 0;
////		for (int i = 0; i < RANGE; i++) {
////			boolean changed = false;
////
////			for (Interval pair : intervals) {
////				if (pair.getStart().getValue() == i) {
////					active.add(pair);
////					changed = true;
////				}
////			}
////
////			if (active.size() > maxOpen) {
////				possible.clear();
////				maxOpen = active.size();
////				possible.add(i);
////			} else if (active.size() == maxOpen && changed) {
////				possible.add(i);
////			}
////
////			int remove = i;
////			active.removeIf(pair -> (pair.getValue() == remove));
////		}
////	}
//
//	@Test
//	public void testLarge() {
//		Intervals i = new Intervals();
//
//		i.intervalInsert(1, 50);
//		i.intervalInsert(2, 49);
//		i.intervalInsert(3, 48);
//		i.intervalInsert(4, 47);
//		i.intervalInsert(5, 46);
//		i.intervalInsert(99, 100);
//		i.intervalInsert(99, 100);
//		i.intervalInsert(99, 100);
//		i.intervalInsert(101, 102);
//
//		System.out.println(i.findPOM());
//		assertEquals(i.findPOM(), 5);
//	}
//
//	@Test
//	public void testOverlap() { //TODO: ERROR
//		Intervals i = new Intervals();
//
//		i.intervalInsert(4, 5);
//		i.intervalInsert(0, 5);
//		i.intervalInsert(5, 7);
//
//		assertEquals(i.findPOM(), 5);
//	}
//
//	@Test
//	public void testOverlap2() {
//		Intervals i = new Intervals();
//
//		i.intervalInsert(4, 5);
//		i.intervalInsert(5, 7);
//		i.intervalInsert(3, 8);
//
//		//printVisual(i.getRBTree());
//		assertEquals(i.findPOM(), 5);
//	}
////
////	@Test
////	public void testRandom() {
////		//printList()
////
////		Intervals i = new Intervals();
////		for (Pair<Integer, Integer> pair : intervals) {
////			i.intervalInsert(pair.getKey(), pair.getValue());
////		}
////
////		int pom = i.findPOM();
////		System.out.println("Yours: ");
////		System.out.println(pom);
////
////		boolean found = false;
////		for (Interval pair : intervals) {
////			if (pom == pair.getKey()) {
////				found = true;
////				break;
////			}
////		}
////
////		boolean poss = false;
////		for (int x : possible) {
////			if (pom == x) {
////				poss = true;
////				break;
////			}
////		}
////
////		System.out.println("Found in inserted list: " + found);
////		System.out.println("Found in possible list: " + poss);
////
////		//printVisual(i.getRBTree());
////
////		assertTrue(found);
////		assertTrue(poss);
////	}
////
////	private void printList() {
////		System.out.println("List: ");
////
////		int num = 0;
////		for (Pair<Integer, Integer> pair : intervals) {
////			double low = num - 0.005;
////			double high = num + 0.005;
////			int l = pair.getKey();
////			int r = pair.getValue();
////			System.out.println(low + "\\left\\{x>" + l + "\\right\\}\\le y\\le" + high + "\\left\\{x<" + r + "\\right\\}");
////			num++;
////		}
////
////		System.out.println("Possible: ");
////		for (int x : possible) {
////			System.out.println(x);
////		}
////	}
////
////	private void printVisual(RBTree rbTree) {
////		System.out.println("Visual");
////		printNode(rbTree.getRoot());
////	}
////
////	private void printNode(Node node) {
////		if (!node.getLeft().isNIL()) printNode(node.getLeft());
////		System.out.println("Color: " + (node.getColor() == Node.RED ? "RED" : "BLACK"));
////		System.out.println("Key:   " + node.getKey());
////		System.out.println("Val:   " + node.getVal());
////		System.out.println("p:     " + node.getP());
////		System.out.println("max:   " + node.getMaxVal());
////		System.out.println("Endpt: " + node.getEndpoint().getValue());
////		System.out.println("Depth: " + node.getHeight());
////		if (!node.getRight().isNIL()) printNode(node.getRight());
////	}
//}
