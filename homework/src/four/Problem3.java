package four;

import java.util.Arrays;

public class Problem3 {
	
	public static int counter = 0;
	
	public static void main(String args[]) {
		
	}

	public static void swap(int arr[], int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
}