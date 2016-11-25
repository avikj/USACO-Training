/*
ID: avikjai1
LANG: JAVA
TASK: subset
*/

import java.io.*;
import java.util.*;

public class subset {
  static long[][] cache;
  public static void main(String[] args) throws Exception {
    Scanner in = new Scanner(new File("subset.in"));
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("subset.out")));
    int n = in.nextInt();
    if(n*(n+1) % 4 != 0) {
      out.println(0);
      out.close();
      System.exit(0);
    }
    int target = n*(n+1)/4;
    cache = new long[n+1][target];
    for(int i = 0; i < n; i++)
      for(int j = 0; j < target; j++)
        cache[i][j] = -1;
    long result = knapsack(0, 0, target, n)/2;
    out.println(result);
    out.close();
  }

  static long knapsack(int current, int currentSum, int target, int max) {
    if(currentSum > target || current > max)
      return 0;
    if(currentSum == target)
      return 1;
    if(cache[current][currentSum] != -1)
      return cache[current][currentSum];
    long result = knapsack(current+1, currentSum, target, max) // don't include current
      + knapsack(current+1, currentSum+current, target, max); // include current
    cache[current][currentSum] = result;
    return result;
  }
}