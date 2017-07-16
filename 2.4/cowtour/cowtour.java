/*
ID: avikjai1
LANG: JAVA
TASK: cowtour
*/

import java.io.*;
import java.util.*;

public class cowtour {
  public static void main(String[] args) throws Exception {
    Scanner in = new Scanner(new File("cowtour.in"));
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cowtour.out")));
    int n = in.nextInt();
    int[][] pastures = new int[n][2];
    for(int i = 0; i < n; i++) {
      pastures[i][0] = in.nextInt();
      pastures[i][1] = in.nextInt();
    }
    double[][] dist = new double[n][n];
    for(int i = 0; i < n; i++) {
      String line = in.next();
      for(int j = 0; j < n; j++)
        if(line.charAt(j) == '1')
          dist[i][j] = dist(pastures[i], pastures[j]);
        else if(i == j)
          dist[i][j] = 0;
        else
          dist[i][j] = -1;
    }
    for(int k = 0; k < n; k++) // Floyd-Warshall
      for(int i = 0; i < n; i++)
        for(int j = 0; j < n; j++)
          if(dist[k][j] != -1 && dist[i][k] != -1 && (dist[i][j] == -1 || dist[i][j] > dist[i][k]+dist[k][j])) 
            dist[i][j] = dist[i][k] + dist[k][j];
    double[] maxDistWithinField = new double[n];
    for(int i = 0; i < n; i++)
      for(int j = 0; j < n; j++)
        maxDistWithinField[i] = Math.max(maxDistWithinField[i], dist[i][j]);
    double result = Double.MAX_VALUE;
    for(int i = 0; i < n; i++) {
      for(int j = 0; j < n; j++) {
        double maxDiameterSeparate = 0;
        for(int k = 0; k < n; k++)
          if(dist[k][j] != -1 || dist[k][i] != -1)
            maxDiameterSeparate = Math.max(maxDiameterSeparate, maxDistWithinField[k]);
        // diameter of resulting field is maximum of diameters of separate fields and max distance through new connection
        double newDiameter = Math.max(maxDistWithinField[i]+maxDistWithinField[j]+dist(pastures[i], pastures[j]), maxDiameterSeparate);
        if(dist[i][j] == -1 && newDiameter < result)
          result = newDiameter;
      }
    }
    out.printf("%.6f\n", Math.round(result*1e6)/1e6);
    out.close();
  }
  static void print(double[][] a) {
    for(double[] row : a)
      System.out.println(Arrays.toString(row));
  }
  static double dist(int[] a, int[] b) {
    return Math.sqrt(Math.pow(a[0]-b[0], 2)+Math.pow(a[1]-b[1], 2));
  }
}