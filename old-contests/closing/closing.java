/*
USACO 2016 US Open Contest, Gold
Problem 2. Closing the Farm
*/

import java.io.*;
import java.util.*;

public class closing {
  static int[] parent; // disjoint set union data structure
  static ArrayList<Integer>[] adjacencyList;
  static boolean[] open;
  public static void main(String[] args) throws Exception {
    Scanner in = new Scanner(new File("closing.in"));
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("closing.out")));
    int n = in.nextInt();
    int m = in.nextInt();

    adjacencyList = new ArrayList[n];
    for(int i = 0; i < n; i++)
      adjacencyList[i] = new ArrayList<Integer>();
    for(int i = 0; i < m; i++) {
      int a = in.nextInt()-1;
      int b = in.nextInt()-1;
      adjacencyList[a].add(b);
      adjacencyList[b].add(a);
    }
    int[] openingOrder = new int[n];
    for(int i = 0; i < n; i++) openingOrder[i] = in.nextInt()-1;
    open = new boolean[n];
    parent = new int[n];
    for(int i = 0; i < n; i++) parent[i] = i; // initialize unconnected disjoint set

    boolean[] output = new boolean[n];
    int componentCount = 0;
    for(int i = n-1; i >= 0; i--) {
      int newOpen = openingOrder[i];
      componentCount++;
      open[newOpen] = true;

      for(int j = 0; j < adjacencyList[newOpen].size(); j++) {
        int neighbor = adjacencyList[newOpen].get(j);
        if(open[neighbor]) {
          if(mergeSets(newOpen, neighbor)) {
            componentCount--;
          }
        }
      }
      output[i] = componentCount <= 1;
    }
    for(int i = 0; i < n; i++)
      out.println(output[i] ? "YES" : "NO");
    out.close();
  }

  static boolean mergeSets(int i, int j) {
    i = findUnion(i);
    j = findUnion(j);
    if(i == j) // they are in the same set
      return false;
    parent[i] = j;
    return true;
  }

  // returns representative of i's set
  static int findUnion(int i) {
    int result = i;
    while(parent[result] != result)
      result = parent[result];

    // update all parents of i to result
    while(parent[i] != i) {
      int temp = parent[i];
      parent[i] = result;
      i = temp;
    }
    return result;
  }
}