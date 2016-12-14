/*
USACO December 2015 Contest, Gold
Problem 2. Fruit Feast
*/

import java.io.*;
import java.util.*;

public class feast {
  static int t, a, b;
  static int[] cache;
  static boolean[] seen;
  static boolean firstPass = true;
  public static void main(String[] args) throws Exception {
    Scanner in = new Scanner(new File("feast.in"));
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("feast.out")));
    t = in.nextInt();
    a = in.nextInt();
    b = in.nextInt();
    cache = new int[t+1];
    seen = new boolean[t+1];

    knapsack(t);
    firstPass = false;
    for(int i = t; i >= 0; i--) {
      if(seen[i]) {
        int fullness = t - i;
        fullness /= 2;
        int r = fullness + knapsack(t-fullness);
        if(r <= t)
          cache[t-fullness] = r;
      }
    }
    int result = 0;
    for(int i = 0; i <= t; i++)
      result = Math.max(result, cache[i]);
    out.println(result);
    out.close();
  }

  static int knapsack(int capacity) {

    if(firstPass)
      seen[capacity] = true;
    if(a > capacity && b > capacity)
      return 0;
    if(cache[capacity] != 0)
      return cache[capacity];
    if(capacity % a == 0 || capacity % b == 0) // hacky but it speeds it up so that cases 7 and 8 work
      return capacity;
    int result = 0;
    if(a <= capacity && b <= capacity)
      result = Math.max(a+knapsack(capacity-a), b+knapsack(capacity-b));
    else if(a <= capacity)
      result = a+knapsack(capacity-a);
    else if(b <= capacity)
      result = b+knapsack(capacity-b);
    cache[capacity] = result;
    return result;
  }
}