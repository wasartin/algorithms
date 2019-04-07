
public class Playground {

	public static void main(String args[]) {
		/**
		 * Write a program that takes a collection 'R' of 'm' tuples,
		 * corresponding to the trace data for 'n' computers, and 
		 * preprocesses 'R' in O(n + mlogm) time to build a data-
		 * structure 'G' that given teh IDS for any two computers
		 * Ca, and Cb, and two times 'x', 'y', where x<=y, returns
		 * one of two things in O(m) time
		 * 	-If there is a seq of communications such that a virus
		 * 		inserted into Computer Ca at time x could have 
		 * 		infected computer Cb by time y, then the program
		 * 		returns a list containing one such sequence.
		 * 
		 * 	-If no infection sequence exists, then the program 
		 * 		returns null.
		 * 
		 * General Take away
		 * 		-Imp BFS
		 * 		-Imp DFS
		 * 		-
		 */
	}
}
