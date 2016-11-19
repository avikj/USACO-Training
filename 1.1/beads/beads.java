/*
ID: avikjai1
LANG: JAVA
TASK: beads
*/

import java.io.*;
import java.util.*;

public class beads {
  public static void main(String[] args) throws Exception {
    BufferedReader in = new BufferedReader(new FileReader("beads.in"));
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("beads.out")));
    int n = Integer.parseInt(in.readLine());
    String x = in.readLine();
    int max = -1;
    boolean allWhite = true;
    for(int i = 0; i < n; i++) {
    	if(x.charAt(i) != 'w')
    		allWhite = false;
    }
    if(allWhite){
    	out.println(n);
    	out.close();
    	return;
    }

    for(int potentialBreakpoint = 0; potentialBreakpoint < n; potentialBreakpoint++) {
    	String s = x; // dont modify the original string
    	System.out.println("potentialBreakpoint: "+potentialBreakpoint);
    	// count same color beads before the breakpoint
    	char last = s.charAt((potentialBreakpoint-1+n)%n);
    	int lastIndex = (potentialBreakpoint-1+n)%n;
    	while(last == 'w') {
    		lastIndex = (lastIndex-1+n)%n;
    		last = s.charAt(lastIndex);
    	}
    	char first = s.charAt(potentialBreakpoint);
    	int firstIndex = potentialBreakpoint;
    	while(first == 'w') {
    		firstIndex = (firstIndex+1)%n;
    		first = s.charAt(firstIndex);
    	}
    	if(first == last) {
    		continue;
    	}
    	int countBefore = 0;
    	for(int i = (potentialBreakpoint-1+n)%n; s.charAt(i) == 'w' || s.charAt(i) == last; i = (i-1+n)%n) {
    		if(s.charAt(i) == 'w') {
    			s = s.substring(0, i)+last+s.substring(i+1);
    		}
    		countBefore++;
    		System.out.println(s.charAt(i));
    	}
    	System.out.println("countbefore: "+countBefore);
    	int countAfter = 0;
    	for(int i = potentialBreakpoint; s.charAt(i) == 'w' || s.charAt(i) == first; i = (i+1)%n) {
    		if(s.charAt(i) == 'w') {
    			s = s.substring(0, i)+first+s.substring(i+1);
    		}
    		countAfter++;
    		System.out.println(s.charAt(i));
    	}
    	System.out.println("countafter: "+countAfter);
    	System.out.println("TOTAL: "+(countAfter+countBefore));
    	max = Math.max(max, countAfter+countBefore);
    	System.out.println("\n\n");
    }
    if(max == -1)
    	out.println(n);
    else
    	out.println(max);
    out.close();
  }
}