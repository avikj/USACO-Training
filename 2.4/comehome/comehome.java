/*
ID: avikjai1
LANG: JAVA
TASK: comehome
*/

import java.io.*;
import java.util.*;

public class comehome {
  public static void main(String[] args) throws Exception {
    Scanner in = new Scanner(new File("comehome.in"));
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("comehome.out")));
    List<List<Integer>> adjList = new ArrayList<List<Integer>>();
    List<List<Integer>> edgeWeights = new ArrayList<List<Integer>>();
    for(int i = 0; i < 52; i++) {
      adjList.add(new ArrayList<Integer>());
      edgeWeights.add(new ArrayList<Integer>());
    }
    int p = in.nextInt();
    for(int i = 0; i < p; i++) {
      int a = toInt(in.next().charAt(0));
      int b = toInt(in.next().charAt(0));
      int d = in.nextInt();
      adjList.get(a).add(b);
      adjList.get(b).add(a);
      edgeWeights.get(a).add(d);
      edgeWeights.get(b).add(d);
    }

    // Dijkstra's from Z
    boolean[] inQ = new boolean[52];
    int[] dist = new int[52];
    Arrays.fill(dist, Integer.MAX_VALUE);
    Arrays.fill(inQ, true);
    dist[toInt('Z')] = 0;
    while(true) {
      int u = -1;
      for(int i = 0; i < 52; i++)
        if(inQ[i] && (u == -1 || dist[i] < dist[u]))
          u = i;
      inQ[u] = false;
      if(toChar(u) <= 'Z' && toChar(u) != 'Z') { // exit when first cow is found
        out.println(toChar(u)+" "+dist[u]);
        break;
      }
      for(int i = 0; i < adjList.get(u).size(); i++)
        dist[adjList.get(u).get(i)] = Math.min(dist[adjList.get(u).get(i)], dist[u] + edgeWeights.get(u).get(i));
    }
    out.close();
  }

  // map lowercase and uppercase characters to unique indices on [0, 52)
  static int toInt(char c) {
    if(c >= 'a')
      return c-'a';
    return c-'A'+'z'-'a'+1;
  }
  static char toChar(int i) {
    if(i < 26)
      return (char)(i+'a');
    return (char)(i-26+'A');
  }
}