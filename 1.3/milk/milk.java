/*
ID: avikjai1
PROG: milk
LANG: JAVA
*/

import java.util.*;
import java.io.*;

public class milk {
	public static void main(String[] args) throws Exception {
		Scanner in = new Scanner(new File("milk.in"));
		PrintWriter out = new PrintWriter("milk.out");
		int n = in.nextInt();
		int m = in.nextInt();
		Farmer[] farmers = new Farmer[m];
		for(int i = 0; i < m; i++) {
			farmers[i] = new Farmer(in.nextInt(), in.nextInt());
		}
		Arrays.sort(farmers);
		int result = 0;
		for(int i = 0; i < m; i++) {
			if(n >= farmers[i].units) {
				result += farmers[i].units * farmers[i].cents;
				n -= farmers[i].units;
				System.out.printf("Bought %d units for %d cents.\n", farmers[i].units, farmers[i].cents);
			} else {
				result += farmers[i].cents * n;
				System.out.printf("Bought %d units for %d cents.\n", n, farmers[i].cents);
				break;
			}
		}
		out.println(result);
		out.close();
	}
	static class Farmer implements Comparable<Farmer> {
		public int units;
		public int cents;
		public Farmer(int c, int u) {
			units = u;
			cents = c;
		}
		public int compareTo(Farmer other) {
			return cents - other.cents;
		}
		public String toString() {
			return "Farmer{ units: "+units+", cents: "+cents+" }";
		}
	}
}