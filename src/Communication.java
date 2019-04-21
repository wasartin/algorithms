public class Communication {

    int c1, c2, timestamp;

    public Communication(int c1, int c2, int timestamp) {
        this.c1 = c1;
        this.c2 = c2;
        this.timestamp = timestamp;
    }

	public int getC1() {
		return c1;
	}

	public void setC1(int c1) {
		this.c1 = c1;
	}

	public int getC2() {
		return c2;
	}

	public void setC2(int c2) {
		this.c2 = c2;
	}

	public int getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(int timestamp) {
		this.timestamp = timestamp;
	}
    
}
