/*
USACO 2015 February Contest, Silver
Problem 2. Cow Hopscotch
*/

import java.io.*;
import java.util.*;

public class hopscotch {
  static final int MOD = 1000000007;
  static int r, c, k; 
  static int[][] grid;
  static int[][] cache; // NEW 
  public static void main(String[] args) throws Exception {
    Scanner in = new Scanner(new File("hopscotch.in"));
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("hopscotch.out")));
    r = in.nextInt();
    c = in.nextInt();
    k = in.nextInt();
    grid = new int[r][c];
    cache = new int[r][c]; // NEW
    for(int i = 0; i < r; i++) {
      for(int j = 0; j < c; j++) {
        grid[i][j] = in.nextInt();
        cache[i][j] = -1; // NEW
      }
    }
    out.println(solve(0, 0));
    out.close();
  }

  static int solve(int row, int col) {
    if(row == r-1 && col == c-1)
      return 1;
    if(cache[row][col] != -1) // NEW
      return cache[row][col]; // NEW
    int result = 0;
    for(int i = row+1; i < r; i++)
      for(int j = col+1; j < c; j++)
        if(grid[i][j] != grid[row][col]) 
          result = (result + solve(i, j)) % MOD;
    cache[row][col] = result; // NEW
    return result;
  }
}