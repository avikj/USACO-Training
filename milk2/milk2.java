/*
ID: avikjai1
LANG: JAVA
TASK: milk2
*/

import java.io.*;
import java.util.*;

public class milk2 {
  public static void main(String[] args) throws Exception {
    BufferedReader in = new BufferedReader(new FileReader("milk2.in"));
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("milk2.out")));
    int n = Integer.parseInt(in.readLine());
    ArrayList<Time> times = new ArrayList<Time>();
    for(int i = 0; i < n; i++) {
    	String[] farmer = in.readLine().split(" ");
    	times.add(new Time(Integer.parseInt(farmer[0]), 0));
    	times.add(new Time(Integer.parseInt(farmer[1]), 1));
    }
    Collections.sort(times);
    int cowsBeingMilked = 0;
    int maxTimeNotMilked = 0;
    int maxMilkingTime = 0;
    int startTime = times.get(0).value;
    for(int i = 0; i < times.size(); i++) {
    	if(times.get(i).type == 0) {
    		if(cowsBeingMilked == 0) {
    			startTime = times.get(i).value;
    		}
    		cowsBeingMilked++;
    	} else {
    		cowsBeingMilked--;
    	}
    	if(cowsBeingMilked == 0) {
    		if(i+1 < times.size()) {
    			int periodLength = times.get(i+1).value - times.get(i).value;
    			maxTimeNotMilked = Math.max(maxTimeNotMilked, periodLength);
    		}
    		maxMilkingTime = Math.max(maxMilkingTime, times.get(i).value-startTime);
    	}
    }
    out.println(maxMilkingTime+" "+maxTimeNotMilked);
    out.close();
  }
}
class Time implements Comparable<Time>{
	public int type;
	public int value;
	public int compareTo(Time other) {
		if(value == other.value) {
			return type-other.type;
		}
		return value - other.value;
	}
	public Time(int value, int type) {
		this.type = type;
		this.value = value;
	}
}