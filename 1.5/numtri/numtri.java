/*
ID: avikjai1
LANG: JAVA
TASK: numtri
*/

import java.io.*;
import java.util.*;

public class numtri {
  public static void main(String[] args) throws Exception {
    Scanner in = new Scanner(new File("numtri.in"));
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("numtri.out")));
    // read input
    int n = in.nextInt();
    // populate jagged triangle array
    int[][] triangle = new int[n][];
    for(int row = 0; row < n; row++) {
    	triangle[row] = new int[row+1];
    	for(int col = 0; col <= row; col++) {
    		triangle[row][col] = in.nextInt();
    	}
    }	   
   	// work from bottom of the triangle to the top
    for(int row = n - 2; row >= 0; row--) {
    	for(int col = 0; col < triangle[row].length; col++) {
    		triangle[row][col] += Math.max(triangle[row+1][col], triangle[row+1][col+1]);
    	}
    }

    out.println(triangle[0][0]);
    out.close();
  }
}