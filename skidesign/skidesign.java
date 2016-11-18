/*
ID: avikjai1
LANG: JAVA
TASK: skidesign
*/

import java.io.*;
import java.util.*;

public class skidesign {
  public static void main(String[] args) throws Exception {
    Scanner in = new Scanner(new File("skidesign.in"));
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("skidesign.out")));
    int n = in.nextInt();
    int[] hills = new int[n];
    int minInitialHeight = Integer.MAX_VALUE;
    int maxInitialHeight = Integer.MIN_VALUE;
    for(int i = 0; i < n; i++) {
    	hills[i] = in.nextInt();
    	if(hills[i] > maxInitialHeight)
    		maxInitialHeight = hills[i];
    	if(hills[i] < minInitialHeight)
    		minInitialHeight = hills[i];
    }
    int minCost = Integer.MAX_VALUE;
    for(int targetMinHeight = minInitialHeight; targetMinHeight <= maxInitialHeight - 17; targetMinHeight++) {
    	int cost = 0;
    	for(int i = 0; i < n; i++) {
    		if(hills[i] < targetMinHeight) {
    			cost += Math.pow(targetMinHeight - hills[i], 2);
    		} else if(hills[i] > targetMinHeight+17) {
    			cost += Math.pow(targetMinHeight+17 - hills[i], 2);
    		}
    	}
    	minCost = Math.min(minCost, cost);
    }
    out.println(minCost);
    out.close();
  }
}