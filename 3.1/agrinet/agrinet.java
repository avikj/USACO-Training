/*
ID: avikjai1
LANG: JAVA
TASK: agrinet
*/

import java.io.*;
import java.util.*;

public class agrinet {
  public static void main(String[] args) throws Exception {
    Scanner in = new Scanner(new File("agrinet.in"));
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("agrinet.out")));
    int n = in.nextInt();
    int[][] dist = new int[n][n];
    for(int i = 0; i < n; i++)
      for(int j = 0; j < n; j++)
        dist[i][j] = in.nextInt();
    boolean[] inTree = new boolean[n];
    inTree[0] = true;
    int totalEdgeLength = 0;
    // O(N^3) implementation of Prim's that is good enough for input size
    for(int edgeCount = 0; edgeCount < n-1; edgeCount++) {
      int closest = -1;
      int closestDist = -1;
      // for each point, try the shortest edge that connects it to the tree if it exists
      for(int i = 0; i < n; i++)
        if(!inTree[i])
          for(int j = 0; j < n; j++)
            if(inTree[j] && (closest == -1 || dist[i][j] < closestDist)) {
              closest = i;
              closestDist = dist[i][j];
            }
      inTree[closest] = true;
      totalEdgeLength += closestDist;
    }
    out.println(totalEdgeLength);
    out.close();
  }
}