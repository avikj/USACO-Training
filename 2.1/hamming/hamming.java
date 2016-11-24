/*
ID: avikjai1
LANG: JAVA
TASK: hamming
*/

import java.io.*;
import java.util.*;

public class hamming {
	static int n;
	static int b;
	static int d;
  public static void main(String[] args) throws Exception {
    Scanner in = new Scanner(new File("hamming.in"));
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("hamming.out")));

    n = in.nextInt();
    b = in.nextInt();
    d = in.nextInt();

    int[] result = solve(new int[n], 0);
    for(int i = 0; i < n; i++)
      out.print(result[i]+ ((i + 1) % 10 == 0 || i + 1 == n ? "\n" : " "));
    out.close();
  }

  // k is how many codewords have been chosen so far
  static int[] solve(int[] set, int k) {
  	if(k < n) {
			for(int val = k > 0 ? set[k-1] : 0; val < Math.pow(2, b); val++) {
				if(works(set, k, val)) {
					set[k] = val;
					int[] result = solve(set, k+1);
					if(result != null)
						return result;
				}
			}
			return null;
  	}
  	return set;
  }

  // checks if val can be added at index k while maintaining a min hamming distance of d
  static boolean works(int[] set, int k, int val) {
    for(int i = 0; i < k; i++)
      if(hammingDistance(set[i], val) < d) 
        return false; 
    return true;
  }

 	static int hammingDistance(int x, int y) {
 		int result = 0;
 		int[] xBin = toBinArr(x);
 		int[] yBin = toBinArr(y);
 		for(int i = 0; i < b; i++)
 			if(xBin[i] != yBin[i])
 				result++;
 		return result;
 	}

 	static int[] toBinArr(int x) {
 		int[] result = new int[b];
 		for(int place = b-1; place >= 0; place--)
 			if(x >= Math.pow(2, place)) {
 				result[b-place-1] = 1;
 				x -= Math.pow(2, place);
 			}
 		return result;
 	}
}