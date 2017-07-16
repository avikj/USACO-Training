/*
ID: avikjai1
LANG: JAVA
TASK: ttwo
*/

import java.io.*;
import java.util.*;

public class ttwo {
  static int[][] directions = {
    {-1, 0},
    {0, 1},
    {1, 0},
    {0, -1}
  };
  public static void main(String[] args) throws Exception {
    Scanner in = new Scanner(new File("ttwo.in"));
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("ttwo.out")));
    
    char[][] map = new char[10][10];
    for(int i = 0; i < 10; i++)
      map[i] = in.next().toCharArray();
    map = addBoundaries(map);
    // total states for farmer is 10*10*4 = 400, total states for cows is the same
    // total states together is 400^2 = 160,000
    // if simulation runs for more than 160,000 steps without them meeting, they will never meet
    Player farmer = null;
    Player cows = null;
    for(int i = 0; i < 12; i++)
      for(int j = 0; j < 12; j++)
        if(map[i][j] == 'F')
          farmer = new Player(i, j, 0);
        else if(map[i][j] == 'C')
          cows = new Player(i, j, 0);
    for(int i = 0; i < 160000; i++) {
      if(cows.i == farmer.i && cows.j == farmer.j) {
        out.println(i);
        out.close();
        System.exit(0);
      }
      int nextFarmerI = farmer.i+directions[farmer.dir][0];
      int nextFarmerJ = farmer.j+directions[farmer.dir][1];
      if(map[nextFarmerI][nextFarmerJ] == '*') {
        nextFarmerI = farmer.i;
        nextFarmerJ = farmer.j;
        farmer.dir = (farmer.dir+1)%4;
      }
      farmer.i = nextFarmerI;
      farmer.j = nextFarmerJ;
      int nextCowsI = cows.i+directions[cows.dir][0];
      int nextCowsJ = cows.j+directions[cows.dir][1];
      if(map[nextCowsI][nextCowsJ] == '*') {
        nextCowsI = cows.i;
        nextCowsJ = cows.j;
        cows.dir = (cows.dir+1)%4;
      }
      cows.i = nextCowsI;
      cows.j = nextCowsJ;
    }
    out.println(0);
    out.close();
  }

  static char[][] addBoundaries(char[][] map) {
    char[][] result = new char[12][12];
    for(int i = 0; i < 12; i++)
      for(int j = 0; j < 12; j += 11)
        result[i][j] = '*';
    for(int i = 0; i < 12; i += 11)
      for(int j = 0; j < 12; j++)
        result[i][j] = '*';
    for(int i = 0; i < 10; i++)
      for(int j = 0; j < 10; j++)
        result[i+1][j+1] = map[i][j];
    return result;
  }
  static class Player {
    public int i;
    public int j;
    public int dir;
    public Player(int i, int j, int dir) {
      this.i = i;
      this.j = j;
      this.dir = dir;
    }
  }
}