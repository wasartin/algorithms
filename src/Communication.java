public class Communication{

    int c1, c2, timestamp;
    public static final String FORMAT = "link [c%d:c%d] at %d";

    public Communication(int c1, int c2, int timestamp) {
        this.c1 = c1;
        this.c2 = c2;
        this.timestamp = timestamp;
    }

    public int getC1() { return this.c1; }
    public int getC2() { return this.c2; }
    public int getTimestamp() { return this.timestamp; }

//    @Override
//    public String toString() {
//    	return "c:" + c1 + " <=> c:" + c2 + " at " + timestamp;
//    }
    
    @Override
    public String toString() {
    	return String.format(FORMAT, c1, c2, timestamp);
    }
}
