package main.java;

import java.util.ArrayList;

//TODO: Professor mentioned there will be test with mulitple same endpoints
//
public class Intervals {
	
	private int currID = 0;//Not sure how to handle a 'global' counter
	private RBTree rbtree;
	//Keep an arrayList of intervals? idk how else to do the id thing
	private ArrayList<Interval> intervalList; //the id of an interval can now just be it's position
	
	//Made public for testing
	/**
	 * Sub class that is the interval object (Two endpoints)
	 * @author 
	 *
	 */
	public class Interval {
		private Endpoint start;
		private Endpoint end;
		
		public Interval(int start, int end) {
			int left_pvalue = 1;
			int right_pvalue = -1;
			this.start = new Endpoint(start, left_pvalue);
			this.end = new Endpoint(end, right_pvalue);
		}
		
		public Interval(Endpoint e1, Endpoint e2) {
			this.start = e1;
			this.end = e2;
		}
		
		public Endpoint getStart() {
			return this.start;
		}
		
		public Endpoint getEnd() {
			return this.end;
		}
	}
	
	public Intervals() {
		rbtree = new RBTree();
		intervalList = new ArrayList<>();
	}
	
	/**
	 * Adds the interval with left endpoint a and right
	 * endpoint b to the collection of intervals. Each newly inserted interval must be assigned an
	 * ID. The IDs should be consecutive; that is, the ID of the interval inserted on the ith call of
	 * this method should be i. For example if intervalInsert is called successively to insert
	 * intervals [5, 7], [4, 9], [1, 8], then the IDs of these intervals should be 1, 2, 3, respectively.
	 * These IDs are permanent for the respective intervals. Keep track of the IDs, as multiple
	 * intervals that have the same endpoints on both sides can be added. intervalInsert
	 * should run in O(log n) time
	 * @param firstPoint
	 * @param secondPoint
	 */
	public void intervalInsert(int a, int b) throws IllegalArgumentException {
		if(a > b) {
			throw new IllegalArgumentException("The first point must precede the second point");
		}
		
		Interval toAdd = new Interval(a, b);
		intervalList.add(toAdd);
		updateRBTree(toAdd);
	}
	
	//TODO: delete, this is just a temp method used for testing
	public void updateRBTree(Interval toAdd) {
		Node newNodeOne = new Node(toAdd.getStart());
		Node newNodeTwo = new Node(toAdd.getEnd());
		rbtree.RBInsert(newNodeOne);
		rbtree.RBInsert(newNodeTwo);
	}
	
	/**TODO: Don't know if we need this, it was just in 14.3 of CLRS (which is pretty much our project)
	 * returns a pointer to an element x in the interval tree T
	 * such that x:int overlaps interval i, or a pointer to the sentinel T:nil if no such
	 * element is in the set.
	 */
	private Node intervalSearch(RBTree T, Interval i) {
		//TODO
		Node x = T.getRoot();
		//while(!x.equals(T.getNilNode()) && )
		return T.getNILNode();
	}
	
	private boolean isOverlap(Interval i, Interval j) {
		return (i.getStart().getValue() < i.getEnd().getValue()) || (j.getEnd().getValue() < i.getStart().getValue());
	}
	
	

	
	public Interval getInterval(int position) {
		if(position < 1) {
			throw new IllegalArgumentException("Interval starts at 1");
		}
		else if(position > intervalList.size()) {
			throw new IllegalArgumentException("The position you selected is larger than the size of current intervals: " + intervalList.size());
		}
		return intervalList.get(position - 1);
	}
	
	/** EXTRA CREDIT
	 * boolean intervalDelete(int intervalID): Deletes the interval whose ID (generated by intervalInsert) is intervalID. Returns true if deletion was successful. This
	 * method should run in O(log n) time.
	 * Note. The intervalDelete method is optional; that is, you are not required
	 * to implement it. However, your code must provide an intervalDelete method
	 * even if you choose not to implement interval deletion. If you do not implement
	 * deletion, the intervalDelete method should consist of just one line that returns
	 * false.
	 * @param intervalID
	 * @return
	 */
	public boolean intervalDelete(int intervalID) {
		//TODO
		return false;
	}
	
	/**
	 * Finds the endpoint that has maximum overlap and returns its value. This
	 * method should run in constant time.
	 * @return
	 */
	public int findPOM() {
		if(rbtree.getRoot().equals(rbtree.getNILNode())) {
			return -1;
		}
		return rbtree.getRoot().getEmax().getValue();
	}
	
	/**
	 * Returns the red-black tree used, which is an object of type RBTree.
	 * @return
	 */
	public RBTree getRBTree() {
		return rbtree;
	}
	
	
	/**TODO: put this in the test class
	 * This is a suggested way on how to add intervals and call POM()
	 * 
	 * @param args
	 */
	public static void main(String [] args) {
		int[][] points = {{0, 4}, {1, 6}, {3, 9}, {7, 11}};
		Intervals intv = new Intervals();
		
		for(int i=0; i<points.length; i++) {
			//System.out.println("Inserting: "+ Arrays.toString(points[i]));
			intv.intervalInsert(points[i][0], points[i][1]);
		}
		System.out.println("POM is: "+ intv.findPOM()); //Should return 3.
	}
	
}
