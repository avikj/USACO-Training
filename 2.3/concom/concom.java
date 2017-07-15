/*
ID: avikjai1
LANG: JAVA
TASK: concom
*/

import java.io.*;
import java.util.*;

public class concom {
  public static void main(String[] args) throws Exception {
    final int n = 100;
    Scanner in = new Scanner(new File("concom.in"));
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("concom.out")));
    int[][] ownership = new int[n][n]; // ownership[i][j] is the percent of j that i owns directly
    ArrayList<ArrayList<Integer>> childrenOf = new ArrayList<ArrayList<Integer>>();
    for(int i = 0; i < n; i++) { // each company owns and controls itself
      ownership[i][i] = 100;
      ArrayList<Integer> a = new ArrayList<Integer>();
      a.add(i);
      childrenOf.add(a);
    }
    int N = in.nextInt();
    for(int k = 0; k < N; k++) {
      int i = in.nextInt()-1;
      int j = in.nextInt()-1;
      int p = in.nextInt();
      ownership[i][j] = p;
    }
    for(int iteration = 0; iteration < n; iteration++) {
      for(int i = 0; i < n; i++) {
        int[] totalOwnership = new int[n]; // how much company i and its children own in total of each company
        for(int child : childrenOf.get(i))
          for(int j = 0; j < n; j++)
            totalOwnership[j] += ownership[child][j];
        for(int j = 0; j < n; j++)
          if(totalOwnership[j] > 50 && !childrenOf.get(i).contains(j))
            childrenOf.get(i).add(j);
      }
    }
    for(int i = 0; i < n; i++) {
      Collections.sort(childrenOf.get(i));
      for(int j : childrenOf.get(i))
        if(i != j)
          out.printf("%d %d\n", i+1, j+1);
    }
    out.close();
  }
}
