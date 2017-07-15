/*
ID: avikjai1
LANG: JAVA
TASK: money
*/

import java.io.*;
import java.util.*;

public class money {
  public static void main(String[] args) throws Exception {
    Scanner in = new Scanner(new File("money.in"));
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("money.out")));
    int v = in.nextInt();
    int n = in.nextInt();
    long[][] dp = new long[n+1][v];
    for(int i = 0; i <= n; i++)
      for(int j = 0; j < v; j++)
        dp[i][j] = -1L;
    int[] coinValues = new int[v];
    for(int i = 0; i < v; i++)
      coinValues[i] = in.nextInt();
    out.println(solve(coinValues, n, v, 0, dp));
    out.close();
  }
  public static long solve(int[] coinValues, int n, int v, int i, long[][] dp) {
    if(n == 0) // found a valid case
      return 1;
    if(n <= 0) // went over the limit
      return 0;
    if(i == v) // no coins left to use
      return 0;
    if(dp[n][i] != -1)
      return dp[n][i];
    dp[n][i] = solve(coinValues, n-coinValues[i], v, i, dp) + solve(coinValues, n, v, i+1, dp);
    return dp[n][i];
  }
}