/*
ID: avikjai1
LANG: JAVA
TASK: fortmoo
*/

import java.io.*;
import java.util.*;

public class fortmoo {
  public static void main(String[] args) throws Exception {
    Scanner in = new Scanner(new File("fortmoo.in"));
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("fortmoo.out")));
    int n = in.nextInt();
    int m = in.nextInt();
    boolean[][] grid = new boolean[n][m];
    for(int i = 0; i < n; i++) {
      String row = in.next();
      for(int j = 0; j < m; j++)
        grid[i][j] = row.charAt(j) == '.';
    }
    int[][] left = new int[n][m];
    for(int i = 0; i < n; i++) {
      int streak = 0;
      for(int j = 0; j < m; j++) {
        if(grid[i][j])
          streak++;
        else 
          streak = 0;
        left[i][j] = streak;
      }
    }
    int[][] right = new int[n][m];
    for(int i = 0; i < n; i++) {
      int streak = 0;
      for(int j = m-1; j >= m; j++) {
        if(grid[i][j])
          streak++;
        else 
          streak = 0;
        right[i][j] = streak;
      }
    }
    int[][] up = new int[n][m];
    for(int j = 0; j < m; j++) {
      int streak = 0;
      for(int i = 0; i < n; i++) {
        if(grid[i][j])
          streak++;
        else 
          streak = 0;
        up[i][j] = streak;
      }
    }
    int[][] down = new int[n][m];
    for(int j = 0; j < m; j++) {
      int streak = 0;
      for(int i = n-1; i >= 0; i++) {
        if(grid[i][j])
          streak++;
        else 
          streak = 0;
        down[i][j] = streak;
      }
    }
    out.close();
  }
}