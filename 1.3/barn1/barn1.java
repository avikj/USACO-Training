/*
ID: avikjai1
LANG: JAVA
TASK: barn1
*/

import java.io.*;
import java.util.*;

public class barn1 {
  public static void main(String[] args) throws Exception {
    Scanner in = new Scanner(new File("barn1.in"));
    PrintWriter out = new PrintWriter("barn1.out");
		int m = in.nextInt(), // max number of boards
				s = in.nextInt(), // number of stalls
				c = in.nextInt(); // number of cows

		int result = s; // subtract stuff from this to get answer
		
		boolean[] cowInStall = new boolean[s]; // whether or not stall i has a cow
		for(int i = 0; i < c; i++) {
			cowInStall[in.nextInt()-1] = true;
		}
		boolean[] boarded = new boolean[s]; // whether or not stall i is boarded
		for(int i = 0; i < s; i++) {
			boarded[i] = true;
		}
		ArrayList<Range> emptyRanges = new ArrayList<Range>();
		boolean currentlyEmpty = !cowInStall[0];
		int emptyRangeStart = 0;
		for(int stallIndex = 0; stallIndex < s; stallIndex++) {
			if(currentlyEmpty && cowInStall[stallIndex]) {
				currentlyEmpty = false;
				if(emptyRangeStart == 0) {
					result -= stallIndex;
					System.out.println(stallIndex+" open at the start.");
				} else {
					emptyRanges.add(new Range(emptyRangeStart, stallIndex));
				}
			} else if(!currentlyEmpty && !cowInStall[stallIndex]) {
				currentlyEmpty = true;
				emptyRangeStart = stallIndex;
			}
		}
		if(currentlyEmpty) {
			result -= s - emptyRangeStart;
			System.out.print(s - emptyRangeStart +" open at the end.");
		}
		System.out.println(emptyRanges);
		Collections.sort(emptyRanges);
		// subtract m-1 largest empty ranges to find min number of boarded stalls
		for(int i = 1; i < m; i++) {
			if(emptyRanges.size() == 0) {
				break;
			}
			Range current = emptyRanges.remove(emptyRanges.size() - 1);
			System.out.println(current);
			result -= current.end - current.start;
		}
		out.println(result);
		out.close();
  }
  static class Range implements Comparable<Range>{
  	public int start;
  	public int end;
  	public Range(int start, int end) {
  		this.start = start;
  		this.end = end;
  	}
  	public String toString() {
  		return "["+(start+1)+", "+(end+1)+")";
  	}
  	public int compareTo(Range other) {
  		return (end - start) - (other.end - other.start);
  	}
  }
}