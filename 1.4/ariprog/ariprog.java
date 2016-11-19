/*
ID: avikjai1
LANG: JAVA
TASK: ariprog
*/

import java.io.*;
import java.util.*;

public class ariprog {
  public static void main(String[] args) throws Exception {
    Scanner in = new Scanner(new File("ariprog.in"));
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("ariprog.out")));

    int n = in.nextInt();
    int m = in.nextInt();

    // isBisquare[i] is true if i is a bisquare made of squares <= m
    boolean[] isBisquare = new boolean[m*m*2+1];
    for(int p = 0; p <= m; p++) {
    	for(int q = p; q <= m; q++) {
    		isBisquare[p*p + q*q] = true;
    	}
    }

    boolean none = true;
    for(int r = 1; r <= m*m*2; r++) {
    	for(int a = 0; a+r*(n-1) <= m*m*2; a++) {
    		boolean isValidArithmeticProgression = true;
    		for(int b = 0; b < n; b++) {
    			if(a+b*r >= isBisquare.length || !isBisquare[a+b*r]) {
    				isValidArithmeticProgression = false;
    				b = n;
    			}
    		}
    		if(isValidArithmeticProgression) {
    			out.println(a+" "+r);
    			none = false;
    		}
    	}
    }
    if(none)
    	out.println("NONE");
    out.close();
  }
}