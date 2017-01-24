/*
ID: avikjai1
LANG: JAVA
TASK: lamps
*/

import java.io.*;
import java.util.*;

public class lamps {
  static ArrayList<Integer> on = new ArrayList<Integer>();
  static ArrayList<Integer> off = new ArrayList<Integer>();
  static int n;
  static int c;

  public static void main(String[] args) throws Exception {
    Scanner in = new Scanner(new File("lamps.in"));
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("lamps.out")));
    n = in.nextInt();
    c = in.nextInt();
    int nextInt = in.nextInt();
    while(nextInt != -1) {
      on.add(nextInt);
      nextInt = in.nextInt();
    }
    nextInt = in.nextInt();
    while(nextInt != -1) {
      off.add(nextInt);
      nextInt = in.nextInt();
    }
    solve(new boolean[4], );
    out.close();
  }

  static void solve(boolean[] flipped, int k, int n)
}