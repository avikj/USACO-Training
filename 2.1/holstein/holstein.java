/*
ID: avikjai1
LANG: JAVA
TASK: holstein
*/

import java.io.*;
import java.util.*;

public class holstein {
	static int[][] feeds;
	static int[] vReqs;
	static int g;
	static int v;
  public static void main(String[] args) throws Exception {
    Scanner in = new Scanner(new File("holstein.in"));
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("holstein.out")));

    v = in.nextInt();
    vReqs = new int[v];
    for(int i = 0; i < v; i++)
    	vReqs[i] = in.nextInt();
    g = in.nextInt();
    feeds = new int[g][v];
    for(int i = 0; i < g; i++)
    	for(int j = 0; j < v; j++)
    		feeds[i][j] = in.nextInt();
    Result result = solve(new boolean[g], 0);
    out.println(result);
    out.close();
  }

  /**
	 * @param selectedFeeds - boolean array indicating whether or not each feed was selected in this set
	 * @param n - how many feeds have been determined so far
   */
  public static Result solve(boolean[] selectedFeeds, int n) {
  	if(n < g) { // haven't finished determining selectedFeeds
  		selectedFeeds[n] = false; // don't include nth feed
  		Result whenExcluded = solve(selectedFeeds, n+1);
  		selectedFeeds[n] = true; // include nth feed
  		Result whenIncluded = solve(selectedFeeds, n+1); 
  		return whenIncluded.compareTo(whenExcluded) < 0 ? whenIncluded : whenExcluded;
  	} else { // the selected feeds are fully determined, now we need to see if this set works and the cost
  		int[] vitamins = new int[v];
  		int numFeeds = 0;
  		for(int i = 0; i < g; i++)
  			if(selectedFeeds[i]){
  				numFeeds++;
  				for(int j = 0; j < v; j++)
  					vitamins[j] += feeds[i][j];
  			}
  		for(int i = 0; i < v; i++)
  			if(vitamins[i] < vReqs[i])
  				return new Result(Integer.MAX_VALUE, null); // infinte cost (doesn't even work)
  		return new Result(numFeeds, selectedFeeds);
  	}
  }

  static class Result implements Comparable<Result> {
  	public int numFeeds;
  	public boolean[] selectedFeeds;
  	public Result(int numFeeds, boolean[] selectedFeeds) {
  		this.numFeeds = numFeeds;
  		this.selectedFeeds = new boolean[g];
  		if(selectedFeeds != null)
  			for(int i = 0 ; i < g; i++)
  				this.selectedFeeds[i] = selectedFeeds[i];
  	}
  	public int compareTo(Result other) {
  		if(this.numFeeds == other.numFeeds)
  			for(int i = 0; i < g; i++)
  				if(this.selectedFeeds[i] != other.selectedFeeds[i])
  					if(this.selectedFeeds[i])
  						return -1;
  					else
  						return 1;
  		return this.numFeeds - other.numFeeds;
  	}
  	public String toString() {
  		String result = String.valueOf(numFeeds);
  		for(int i = 0; i < g; i++)
  			if(selectedFeeds[i])
  				result += " "+(i+1);
  		return result;
  	}
  }
}