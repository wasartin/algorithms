public class Communication implements Comparable {

    int c1, c2, timestamp;

    public Communication(int c1, int c2, int timestamp) {
        this.c1 = c1;
        this.c2 = c2;
        this.timestamp = timestamp;
    }
    public int getC1() { return this.c1; }
    public int getC2() { return this.c2; }
    public int getTimestamp() { return this.timestamp; }

    public int compareTo(Object compareComm) {
        int comparetimestamp = ((Communication)compareComm).getTimestamp();
        /* For Ascending order*/
        return this.timestamp - comparetimestamp;

        /* For Descending order do like this */
        //return compareage-this.studentage;
    }
}
