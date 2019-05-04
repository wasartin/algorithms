package main;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author Will Sartin & Josh Ramon
 *
 */
public enum Position{
	    RIGHT(-1),
	    NIL(0),
	    LEFT(1),
	    ERROR();

	    public final int value;
	    private static final Map<Integer, Position> enumMap = new HashMap<Integer, Position>();

	    private Position() {
	    	value = -1234;
	    }
	    
	    private Position(int value){
	        this.value = value;
	    }

	    static
	    {
	        for (Position pos : Position.values())
	        	enumMap.put(pos.value, pos);
	    }
	    
		public static Position getValueOf(int p){
			return enumMap.containsKey(p) ? enumMap.get(p): ERROR;
		}
}