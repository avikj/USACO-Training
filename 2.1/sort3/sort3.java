/*
ID: avikjai1
LANG: JAVA
TASK: sort3
*/

import java.io.*;
import java.util.*;

public class sort3 {
  public static void main(String[] args) throws Exception {
    BufferedReader in = new BufferedReader(new FileReader("sort3.in"));
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("sort3.out")));
  	int n = Integer.parseInt(in.readLine());
  	int[] sequence = new int[n];
  	int[] counts = new int[3];
  	for(int i = 0; i < n; i++) {
  		sequence[i] = Integer.parseInt(in.readLine()) - 1;
  		counts[sequence[i]]++;
  	}
  	
  	// count # of each # in each section, 
  	// where a section is the set of locations a certain number 
  	// would be in if the sequence were sorted
  	int[][] countsPerSection = new int[3][3];
  	int index = 0;
  	for(int section = 0; section < 3; section++)
  		for(int i = 0; i < counts[section]; i++, index++)
  			countsPerSection[section][sequence[index]]++;

  	// perform single swaps that will move 2 numbers to the right place simultaneously
  	int totalSingleSwaps = 0;
  	for(int i = 0; i < 3; i++) {
  		for(int j = 0; j < 3; j++) {
  			if(i != j) {
  				int singleSwaps = Math.min(countsPerSection[i][j], countsPerSection[j][i]);
  				countsPerSection[i][j] -= singleSwaps;
  				countsPerSection[j][i] -= singleSwaps;
  				totalSingleSwaps += singleSwaps;
  			}
  		}
  	}

  	// everything else can be put in place by rotating 3 numbers
  	int rotations = 0;
  	for(int i = 0; i < 3; i++)
  		for(int j = 0; j < 3; j++)
  			if(i != j)
  				rotations += countsPerSection[i][j];
  	rotations /= 3;

  	// single swaps take 1 swap; rotations of 3 numbers take 2 swaps
  	int result = totalSingleSwaps + rotations * 2;
  	out.println(result);
  	out.close();
  }
}