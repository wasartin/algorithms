package binaryHeap;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;

public class Temp {
	
	
	public static final String ORDER = "(%s, %s, %s, %s, %s, %s, %s),"
				+ "\n";
	  
	public static void main(String args[]) throws IOException { 
		BufferedReader input = new BufferedReader(new FileReader(new File("c:\\Users\\watis\\ISU\\cs309\\project\\info.txt")));     
		Writer output = new BufferedWriter(new FileWriter(new File("c:\\Users\\watis\\ISU\\cs309\\project\\result.txt")));
		try {
		  String line = null;
		  String[] st = null;
		  //id, name, protein, carb, fat, calorie, location
		  ArrayList<String> list = new ArrayList<String>() {{
			    add("BURGER");
			    add("SIGNATURE");
			    add("CHICKEN");
			    add("BREAKFAST");
			    add("SNACKSIDE");
			}};
		  String name, protein, carb, fat, calorie, currType;
		  int currID = 99;
		  int mcID = 1;
		  output.write("INSERT INTO food VALUES \n");
		  while ((line = input.readLine()) != null) {
			name = "";
		    st = line.replace("\"","").split(",");
		    String tmp = st[0];//needs apo
		    tmp = tmp.replaceAll("\'", "");
		    name = "\'";
		    name += tmp;
		    name += "\'";
		    protein = st[10];
		    carb = st[7];
		    fat = st[2];
		    calorie = st[1];
		    currType = st[11];
		    if(list.contains(currType)) {
		    	output.write("\t");
			    output.write(String.format(ORDER, currID++, name, protein, carb, fat, calorie, mcID));
		    }
		  }
		} finally {
		  input.close();
		  output.close();
		}
	}
}
