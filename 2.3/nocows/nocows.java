/*
ID: avikjai1
LANG: JAVA
TASK: nocows
*/

import java.io.*;
import java.util.*;

public class nocows {
  public static void main(String[] args) throws Exception {
    Scanner in = new Scanner(new File("nocows.in"));
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("nocows.out")));
    int n = in.nextInt();
    int k = in.nextInt();
    int[][] dp = new int[n+1][k+1];
    for(int i = 0; i <= n; i++)
      for(int j = 0; j <= k; j++)
        dp[i][j] = -1;
    dp[1][1] = 1;
    out.println(countValidTrees(n, k, dp));
    out.close();
  }
  static int countValidTrees(int n, int k, int[][] dp) {
    if(dp[n][k] != -1) 
      return dp[n][k];
    // Assume that the left node has height k-1, and the right has height <= k-1
    int result = 0;
    int leftChildK = k-1;
    // increment by two because it's always zero for even N (root + even children must be odd)
    for(int leftChildN = 1; leftChildN < n; leftChildN+=2) {
      int rightChildN = n-leftChildN-1;
      for(int rightChildK = 1; rightChildK < k; rightChildK++) {
        int rightChildPossibilities = countValidTrees(rightChildN, rightChildK, dp);
        int leftChildPossibilities = countValidTrees(leftChildN, leftChildK, dp);
        int totalPossibilities = rightChildPossibilities*leftChildPossibilities;
        // if it isn't symmetrical, count the reflection
        if(rightChildK != leftChildK)
          totalPossibilities *= 2;
        result += totalPossibilities;
        result %= 9901;
      }
    }
    dp[n][k] = result;
    return result;
  }
}