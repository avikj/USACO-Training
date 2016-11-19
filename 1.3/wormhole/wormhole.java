/*
ID: avikjai1
LANG: JAVA
TASK: wormhole
*/

import java.io.*;
import java.util.*;

public class wormhole {
  public static void main(String[] args) throws Exception {
    Scanner in = new Scanner(new File("wormhole.in"));
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("wormhole.out")));

    int n = in.nextInt();
    int[][] coords = new int[n][2];
    for(int i = 0; i < n; i++) {
    	coords[i][0] = in.nextInt();
    	coords[i][1] = in.nextInt();
    }
    // for(int i = 0; i < coords.length; i++) { for(int j = 0; j < coords[i].length; j++) System.out.print(coords[i][j]+" "); System.out.println();}
    int[] pairs = new int[n];
    Arrays.fill(pairs, -1);
    // stores the index of the next wormhole directly to the right of this, -1 if none
  	int[] nextOnRight = new int[pairs.length];
  	Arrays.fill(nextOnRight, -1);
  	for(int i = 0; i < pairs.length; i++) {
  		// for each other wormhole, see if its to the right of this
  		for(int j = 0; j < coords.length; j++) {
  			if(j != i && coords[j][1] == coords[i][1] && coords[j][0] > coords[i][0] && (nextOnRight[i] == -1 || coords[nextOnRight[i]][0] > coords[j][0])) {
  				nextOnRight[i] = j;
  			}
  		}
  	}
    int result = solve(pairs, nextOnRight);
    out.println(result);
    out.close();
  }

  // recursively fills pairs will all possible pairings and calls cycleExists
  public static int solve(int[] pairs, int[] nextOnRight) {
  	// find first unpaired and pair it with every other
  	int firstUnpaired;
  	for(firstUnpaired = 0; firstUnpaired < pairs.length; firstUnpaired++) {
  		if(pairs[firstUnpaired] == -1) {
  			break;
  		}
  	}
  	// base case none are unpaired
  	if(firstUnpaired == pairs.length) {
  		return cycleExists(pairs, nextOnRight) ? 1 : 0; 
  	} else { // try to pair it with every other unpaired, recurse
  		int result = 0;
			for(int j = firstUnpaired+1; j < pairs.length; j++) {
				if(pairs[j] == -1) {
					pairs[firstUnpaired] = j;
					pairs[j] = firstUnpaired;
					result += solve(pairs, nextOnRight);
					pairs[firstUnpaired] = pairs[j] = -1;
				}
			}
			return result;
		}
  }
  // determines if a cycle is possible given pairs
  public static boolean cycleExists(int[] pairs, int[] nextOnRight) {
  	// for each possible starting position
  	for(int startPosition = 0; startPosition < pairs.length; startPosition++) {
  		// if within N steps, there is no next on right, there is no cycle
  		int currentWormhole = startPosition;
  		boolean cycleIfStartHere = true;
  		for(int i = 0; i < pairs.length; i++) {
  			// if there's nothing on the right, there isn't a cycle
  			if(nextOnRight[currentWormhole] == -1) {
  				i = pairs.length;
  				cycleIfStartHere = false;
  			} else {
  				// go to the next wormhole
  				currentWormhole = pairs[nextOnRight[currentWormhole]];
  			}
  		}
  		if(cycleIfStartHere) {
  			return true;
  		}
  	}
  	return false;
  }
}