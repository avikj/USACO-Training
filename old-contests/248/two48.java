/*
ID: avikjai1
LANG: JAVA
TASK: 248
*/

import java.io.*;
import java.util.*;

public class two48 {
  public static void main(String[] args) throws Exception {
    Scanner in = new Scanner(new File("248.in"));
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("248.out")));
    int n = in.nextInt();
    int[] seq = new int[n];
    for(int i = 0; i < n; i++) seq[i] = in.nextInt();

    int[/*start index*/][/*end index*/] dp = new int[n][n]; // stores max value made from range
    int result = 0;
    for(int len = 1; len <= n; len++) {
      for(int start = 0; start+len <= n; start++) {
        int end = start + len - 1;
        if(len == 1)
          dp[start][end] = seq[start];
        for(int mid = start; mid < end; mid++)
          // try to merge ranges [start, mid] and (mid, end]
          if(dp[start][mid] == dp[mid+1][end] && dp[start][mid] != 0)
            dp[start][end] = Math.max(dp[start][end], dp[start][mid]+1);
        result = Math.max(result, dp[start][end]);
      }
    }
    out.println(result);
    out.close();
  }
}