/*
ID: avikjai1
LANG: JAVA
TASK: inflate
*/

import java.io.*;
import java.util.*;

public class inflate {
  public static void main(String[] args) throws Exception {
    Scanner in = new Scanner(new File("inflate.in"));
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("inflate.out")));
    int m = in.nextInt();
    int n = in.nextInt();
    int[] points = new int[n];
    int[] minutes = new int[n];
    for(int i = 0; i < n; i++) {
      points[i] = in.nextInt();
      minutes[i] = in.nextInt();
    }
    int[] dp = new int[m+1]; // dp[i] represents max points attainable in i minutes
    for(int i = 0; i < n; i++) {
      for(int j = minutes[i]; j <= m; j++) {
        dp[j] = Math.max(dp[j-minutes[i]]+points[i], dp[j]);
      }
    }
    out.println(dp[m]);
    out.close();
  }
}