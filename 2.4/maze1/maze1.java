/*
ID: avikjai1
LANG: JAVA
TASK: maze1
*/

import java.io.*;
import java.util.*;

public class maze1 {
  public static void main(String[] args) throws Exception {
    Scanner in = new Scanner(new File("maze1.in"));
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("maze1.out")));
    int w = in.nextInt();
    int h = in.nextInt();
    char[][] mazeInput = new char[2*h+1][2*w+1];
    in.nextLine();
    for(int i = 0; i < 2*h+1; i++)
      mazeInput[i] = in.nextLine().toCharArray();
    Location[][] locations = new Location[h][w];
    for(int i = 0; i < h; i++)
      for(int j = 0; j < w; j++)
        locations[i][j] = new Location(i, j);
    for(int i = 0; i < h; i++) {
      for(int j = 0; j < w; j++) {
        if(i < h - 1 && mazeInput[i*2+2][j*2+1] == ' ') {
          locations[i][j].neighbors.add(locations[i+1][j]);
          locations[i+1][j].neighbors.add(locations[i][j]);
        }
        if(j < w - 1 && mazeInput[i*2+1][j*2+2] == ' ') {
          locations[i][j].neighbors.add(locations[i][j+1]);
          locations[i][j+1].neighbors.add(locations[i][j]);
        }
      }
    }
    ArrayList<Location> exits = new ArrayList<Location>();
    for(int j = 0; j < w; j++) // search top for exits
      if(mazeInput[0][j*2+1] == ' ')
        exits.add(locations[0][j]);
    for(int j = 0; j < w; j++) // search bottom for exits
      if(mazeInput[h*2][j*2+1] == ' ')
        exits.add(locations[h-1][j]);
    for(int i = 0; i < h; i++) // search left for exits
      if(mazeInput[i*2+1][0] == ' ')
        exits.add(locations[i][0]);
    for(int i = 0; i < h; i++) {// search right for exits
      if(mazeInput[i*2+1][w*2] == ' ')
        exits.add(locations[i][w-1]);
    }
    int[][] dist = new int[h][w];
    for(int i = 0; i < h; i++)
      for(int j = 0; j < w; j++)
        dist[i][j] = Integer.MAX_VALUE;
    for(Location exit : exits) // run bfs from each exit to populate dist
      bfs(exit, dist);
    int maxDist = 0;
    for(int i = 0; i < h; i++)
      for(int j = 0; j < w; j++)
        maxDist = Math.max(dist[i][j], maxDist);
    out.println(maxDist+1);
    out.close();
  }
  static void bfs(Location start, int[][] resultDists) {
    boolean[][] visited = new boolean[resultDists.length][resultDists[0].length];
    Queue<Location> locations = new LinkedList<Location>();
    Queue<Integer> dists = new LinkedList<Integer>();
    locations.add(start);
    dists.add(0);
    visited[start.i][start.j] = true;
    while(!locations.isEmpty()) {
      Location next = locations.poll();
      int nextDist = dists.poll();
      resultDists[next.i][next.j] = Math.min(nextDist, resultDists[next.i][next.j]);
      for(Location neighbor : next.neighbors) {
        if(!visited[neighbor.i][neighbor.j]) {
          locations.add(neighbor);
          dists.add(nextDist+1);
          visited[neighbor.i][neighbor.j] = true;
        }
      }
    }
  }
}
class Location {
  public final int i;
  public final int j;
  public List<Location> neighbors;
  public Location(int i, int j) {
    this.i = i;
    this.j = j;
    neighbors = new ArrayList<Location>();
  }
}