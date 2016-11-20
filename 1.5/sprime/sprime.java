/*
ID: avikjai1
LANG: JAVA
TASK: sprime
*/

import java.io.*;
import java.util.*;

public class sprime {
	static PrintWriter out;
	static final int[] possibleDigits = {1, 2, 3, 5, 7, 9};
  public static void main(String[] args) throws Exception {
    Scanner in = new Scanner(new File("sprime.in"));
    out = new PrintWriter(new BufferedWriter(new FileWriter("sprime.out")));
  	int n = in.nextInt();
  	solve(0, n);
  	out.close();
  }

  public static void solve(int current, int n) {
  	if(!isPrime(current)) { // if it isn't prime, discard
  		return;
  	}
  	int currentLength = current == 0 ? 0 : String.valueOf(current).length();
  	if(currentLength == n) {
  		out.println(current);
  	} else {
  		for(int i: possibleDigits) {
  			solve(10*current + i, n);
  		}
  	}
  }

  public static boolean isPrime(int n) {
  	if(n == 1) return false;
  	for(int i = 2; i <= Math.sqrt(n); i++) {
  		if(n % i == 0) {
  			return false;
  		}
  	}
  	return true;
  }
}