/*
ID: avikjai1
LANG: JAVA
TASK: milk3
*/

import java.io.*;
import java.util.*;

public class milk3 {
	static boolean[][][] visited;
	static boolean[] result;
	static int[] capacities;
  public static void main(String[] args) throws Exception {
    Scanner in = new Scanner(new File("milk3.in"));
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("milk3.out")));

    capacities = new int[]{ in.nextInt(), in.nextInt(), in.nextInt() };
    int[] values = { 0, 0, capacities[2] };
    visited = new boolean[capacities[0]+1][capacities[1]+1][capacities[2]+1];
    result = new boolean[capacities[2]+1];
    solve(values);
    String outString = "";
    for(int i = 0; i < result.length; i++) {
    	if(result[i])
    		outString += i+" ";
    }
    outString = outString.substring(0, outString.length() - 1);
    out.println(outString);
    out.close();
  }
  // recursively iterate through every possible state
  public static void solve(int[] values) {
  	if(visited[values[0]][values[1]][values[2]])
  		return;

  	visited[values[0]][values[1]][values[2]] = true;
  	if(values[0] == 0)
  		result[values[2]] = true;
  	// try each possible pour
  	for(int i = 0; i < 3; i++) {
  		for(int j = 0; j < 3; j++) {
  			if(i != j) {
  				int[] newValues = pour(values, i, j);
  				solve(newValues);
  			}
  		}
  	}
  }

  // returns array with contents from i poured into j
  public static int[] pour(int[] values, int i, int j) {
  	int[] result = (int[]) values.clone();
  	if(result[j] + result[i] <= capacities[j]) { // if all of i can be poured into j, transfer all of i to j
  		result[j] += result[i];
  		result[i] = 0;
  		return result;
  	} else { // else, fill i to its max capacity
  		result[i] -= capacities[j] - result[j];
  		result[j] = capacities[j];
  		return result;
  	}
  }
}