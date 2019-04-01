

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class RBT3Tests {

	// Instance variables
	RBTree rt1;
	Intervals i1;

	@Before
	public void before() {
		i1 = new Intervals();

	}

	@Test
	public void testInsert() {
		// initial
		rt1 = i1.getRBTree();
		assertEquals(RBTree.getNILNode(), rt1.getRoot());

		// first insertion
		i1.intervalInsert(0, 4); 
		rt1 = i1.getRBTree();
		assertEquals(0, rt1.getRoot().getKey());
		assertEquals(4, rt1.getRoot().getRight().getKey());

		// second insertion
		i1.intervalInsert(1, 6); 
		rt1 = i1.getRBTree();
		assertEquals(1, rt1.getRoot().getKey());
		assertEquals(0, rt1.getRoot().getLeft().getKey());
		assertEquals(4, rt1.getRoot().getRight().getKey());
		assertEquals(6, rt1.getRoot().getRight().getRight().getKey());

		// third insertion
		i1.intervalInsert(3, 9);
		rt1 = i1.getRBTree();
		assertEquals(1, rt1.getRoot().getKey());
		assertEquals(0, rt1.getRoot().getLeft().getKey());
		assertEquals(4, rt1.getRoot().getRight().getKey());
		assertEquals(6, rt1.getRoot().getRight().getRight().getKey());
		assertEquals(3, rt1.getRoot().getRight().getLeft().getKey());
		assertEquals(9, rt1.getRoot().getRight().getRight().getRight().getKey());

		// fourth insertion
		i1.intervalInsert(7, 11);
		rt1 = i1.getRBTree();
		assertEquals(4, rt1.getRoot().getKey());
		assertEquals(1, rt1.getRoot().getLeft().getKey());
		assertEquals(0, rt1.getRoot().getLeft().getLeft().getKey());
		assertEquals(3, rt1.getRoot().getLeft().getRight().getKey());
		assertEquals(7, rt1.getRoot().getRight().getKey());
		assertEquals(6, rt1.getRoot().getRight().getLeft().getKey());
		assertEquals(9, rt1.getRoot().getRight().getRight().getKey());
		assertEquals(11, rt1.getRoot().getRight().getRight().getRight().getKey()); 	
	}

	@Test
	public void testColor() {
		// initial
		rt1 = i1.getRBTree();
		assertEquals(Color.BLACK.ordinal(), rt1.getRoot().getColor());
		// first insertion
		i1.intervalInsert(0, 4); 
		rt1 = i1.getRBTree();
		assertEquals(Color.BLACK.ordinal(), rt1.getRoot().getColor());
		assertEquals(Color.RED.ordinal(), rt1.getRoot().getRight().getColor());

		// second insertion
		i1.intervalInsert(1, 6); 
		rt1 = i1.getRBTree();
		assertEquals(Color.BLACK.ordinal(), rt1.getRoot().getColor());
		assertEquals(Color.BLACK.ordinal(), rt1.getRoot().getLeft().getColor());
		assertEquals(Color.BLACK.ordinal(), rt1.getRoot().getRight().getColor());
		assertEquals(Color.RED.ordinal(), rt1.getRoot().getRight().getRight().getColor());

		// third insertion
		i1.intervalInsert(3, 9);
		rt1 = i1.getRBTree();
		assertEquals(Color.BLACK.ordinal(), rt1.getRoot().getColor());
		assertEquals(Color.BLACK.ordinal(), rt1.getRoot().getLeft().getColor());
		assertEquals(Color.RED.ordinal(), rt1.getRoot().getRight().getColor());
		assertEquals(Color.BLACK.ordinal(), rt1.getRoot().getRight().getRight().getColor());
		assertEquals(Color.BLACK.ordinal(), rt1.getRoot().getRight().getLeft().getColor());
		assertEquals(Color.RED.ordinal(), rt1.getRoot().getRight().getRight().getRight().getColor());

		// fourth insertion
		i1.intervalInsert(7, 11);
		rt1 = i1.getRBTree();
		assertEquals(Color.BLACK.ordinal(), rt1.getRoot().getColor());
		assertEquals(Color.RED.ordinal(), rt1.getRoot().getLeft().getColor());
		assertEquals(Color.BLACK.ordinal(), rt1.getRoot().getLeft().getLeft().getColor());
		assertEquals(Color.BLACK.ordinal(), rt1.getRoot().getLeft().getRight().getColor());
		assertEquals(Color.RED.ordinal(), rt1.getRoot().getRight().getColor());
		assertEquals(Color.BLACK.ordinal(), rt1.getRoot().getRight().getLeft().getColor());
		assertEquals(Color.BLACK.ordinal(), rt1.getRoot().getRight().getRight().getColor());
		assertEquals(Color.RED.ordinal(), rt1.getRoot().getRight().getRight().getRight().getColor());

	}

	@Test
	public void testSize() {
		// initial 
		rt1 = i1.getRBTree();
		assertEquals(0, rt1.getSize());

		// first insertion
		i1.intervalInsert(0, 4); 
		rt1 = i1.getRBTree();
		assertEquals(2, rt1.getSize());

		// second insertion
		i1.intervalInsert(1, 6); 
		rt1 = i1.getRBTree();
		assertEquals(4, rt1.getSize());

		// third insertion
		i1.intervalInsert(3, 9);
		rt1 = i1.getRBTree();
		assertEquals(6, rt1.getSize());

		// fourth insertion
		i1.intervalInsert(7, 11);
		rt1 = i1.getRBTree();
		assertEquals(8, rt1.getSize());
	}

	@Test
	public void testHeight() {
		//initial
		rt1 = i1.getRBTree();
		assertEquals(0, rt1.getHeight());

		// first insertion
		i1.intervalInsert(0, 4); 
		rt1 = i1.getRBTree();
		assertEquals(2, rt1.getHeight());

		// second insertion
		i1.intervalInsert(1, 6); 
		rt1 = i1.getRBTree();
		assertEquals(3, rt1.getHeight());

		// third insertion
		i1.intervalInsert(3, 9);
		rt1 = i1.getRBTree();
		assertEquals(4, rt1.getHeight());

		// fourth insertion
		i1.intervalInsert(7, 11);
		rt1 = i1.getRBTree();
		assertEquals(4, rt1.getHeight());

	}


	@Test
	public void testP() {
		i1.intervalInsert(0, 4); 
		i1.intervalInsert(1, 6); 
		i1.intervalInsert(3, 9);
		i1.intervalInsert(7, 11);
		rt1 = i1.getRBTree();
		assertEquals(-1, rt1.getRoot().getP());
		assertEquals(1, rt1.getRoot().getLeft().getP());
		assertEquals(1, rt1.getRoot().getLeft().getLeft().getP());
		assertEquals(1, rt1.getRoot().getLeft().getRight().getP());
		assertEquals(1, rt1.getRoot().getRight().getP());
		assertEquals(-1, rt1.getRoot().getRight().getLeft().getP());
		assertEquals(-1, rt1.getRoot().getRight().getRight().getP());
		assertEquals(-1, rt1.getRoot().getRight().getRight().getRight().getP()); 
	}

	@Test
	public void testVal() {
		i1.intervalInsert(0, 4); 
		i1.intervalInsert(1, 6); 
		i1.intervalInsert(3, 9);
		i1.intervalInsert(7, 11);
		rt1 = i1.getRBTree();
		assertEquals(0, rt1.getRoot().getVal());
		assertEquals(3, rt1.getRoot().getLeft().getVal());
		assertEquals(1, rt1.getRoot().getLeft().getLeft().getVal());
		assertEquals(1, rt1.getRoot().getLeft().getRight().getVal());
		assertEquals(-2, rt1.getRoot().getRight().getVal());
		assertEquals(-1, rt1.getRoot().getRight().getLeft().getVal());
		assertEquals(-2, rt1.getRoot().getRight().getRight().getVal());
		assertEquals(-1, rt1.getRoot().getRight().getRight().getRight().getVal());
	}

	@Test
	public void testMaxVal() {
		i1.intervalInsert(0, 4); 
		i1.intervalInsert(1, 6); 
		i1.intervalInsert(3, 9);
		i1.intervalInsert(7, 11);
		rt1 = i1.getRBTree();
		assertEquals(3, rt1.getRoot().getMaxVal());
		assertEquals(3, rt1.getRoot().getLeft().getMaxVal());
		assertEquals(1, rt1.getRoot().getLeft().getLeft().getMaxVal());
		assertEquals(1, rt1.getRoot().getLeft().getRight().getMaxVal());
		assertEquals(0, rt1.getRoot().getRight().getMaxVal());
		assertEquals(0, rt1.getRoot().getRight().getLeft().getMaxVal());
		assertEquals(0, rt1.getRoot().getRight().getRight().getMaxVal());
		assertEquals(0, rt1.getRoot().getRight().getRight().getRight().getMaxVal());
	}

	@Test
	public void testEmax() {		//TODO: ERROR
		i1.intervalInsert(0, 4); 
		i1.intervalInsert(1, 6); 
		i1.intervalInsert(3, 9);
		i1.intervalInsert(7, 11);
		rt1 = i1.getRBTree();
		rt1.printTree();
		assertEquals(3, rt1.getRoot().getEmax().getValue());
		assertEquals(3, rt1.getRoot().getLeft().getEmax().getValue());
		assertEquals(0, rt1.getRoot().getLeft().getLeft().getEmax().getValue());
		assertEquals(3, rt1.getRoot().getLeft().getRight().getEmax().getValue()); 
		assertEquals(7, rt1.getRoot().getRight().getEmax().getValue()); 
		assertEquals(rt1.getNILNode().getKey(), rt1.getRoot().getRight().getLeft().getEmax().getValue()); //nil node
		assertEquals(rt1.getNILNode().getKey(), rt1.getRoot().getRight().getRight().getEmax().getValue()); 				//ERROR, how is this not the nil?
		assertEquals(RBTree.getNILNode().getKey(), rt1.getRoot().getRight().getRight().getRight().getEmax().getValue()); 	

	}
	
	@Test
	public void testPOM() {
		i1.intervalInsert(0, 4); 
		i1.intervalInsert(1, 6); 
		i1.intervalInsert(3, 9);
		i1.intervalInsert(7, 11);
		rt1 = i1.getRBTree();
		assertEquals(3, i1.findPOM());

	}
	
//	@Test
//	public void testID() {
//		assertEquals(1, i1.ID);
//		i1.intervalInsert(0, 4); 
//		assertEquals(2, i1.ID);
//		i1.intervalInsert(1, 6); 
//		assertEquals(3, i1.ID);
//		i1.intervalInsert(3, 9);
//		assertEquals(4, i1.ID);
//		i1.intervalInsert(7, 11);
//		assertEquals(5, i1.ID);
//	}
}
