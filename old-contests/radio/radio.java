/*
USACO 2016 January Contest, Gold
Problem 2. Radio Contact
*/

import java.io.*;
import java.util.*;

public class radio {
  static int n;
  static int m;
  static String fPath;
  static String bPath;
  static int[][] dp;
  public static void main(String[] args) throws Exception {
    Scanner in = new Scanner(new File("radio.in"));
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("radio.out")));
    n = in.nextInt();
    m = in.nextInt();
    int fx = in.nextInt();
    int fy = in.nextInt();
    int bx = in.nextInt();
    int by = in.nextInt();
    fPath = in.next();
    bPath = in.next();

    dp = new int[n+1][m+1];

    out.println(solve(fx, fy, bx, by, 0, 0));
    out.close();
  }

  public static int solve(int fx, int fy, int bx, int by, int fi, int bi) {
    if(fi == n && bi == m) {
      return 0;
    }
    if(dp[fi][bi] != 0)
      return dp[fi][bi];
    int result = Integer.MAX_VALUE;
    int nextFx = 0, nextBx = 0, nextFy = 0, nextBy = 0;
    if(fi < n) { // move Farmer Joe forward but not Bessie
      nextFx = fPath.charAt(fi) == 'W' ? fx - 1 : fPath.charAt(fi) == 'E' ? fx + 1 : fx;
      nextFy = fPath.charAt(fi) == 'S' ? fy - 1 : fPath.charAt(fi) == 'N' ? fy + 1 : fy;
      result = Math.min(distSquared(nextFx, nextFy, bx, by) + solve(nextFx, nextFy, bx, by, fi + 1, bi), result);
    }
    if(bi < m) { // move Bessie forward but not Farmer Joe
      nextBx = bPath.charAt(bi) == 'W' ? bx - 1 : bPath.charAt(bi) == 'E' ? bx + 1 : bx;
      nextBy = bPath.charAt(bi) == 'S' ? by - 1 : bPath.charAt(bi) == 'N' ? by + 1 : by;
      result = Math.min(distSquared(fx, fy, nextBx, nextBy) + solve(fx, fy, nextBx, nextBy, fi, bi + 1), result);
    }
    if(fi < n && bi < m) { // move both forward simultaneously
      result = Math.min(distSquared(nextFx, nextFy, nextBx, nextBy) + solve(nextFx, nextFy, nextBx, nextBy, fi + 1, bi + 1), result);
    }
    dp[fi][bi] = result;
    return result;
  }

  public static int distSquared(int fx, int fy, int bx, int by) {
    return (fx - bx)*(fx - bx) + (fy - by)*(fy - by);
  }
}