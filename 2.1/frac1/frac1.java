/*
ID: avikjai1
LANG: JAVA
TASK: frac1
*/

import java.io.*;
import java.util.*;

public class frac1 {
  public static void main(String[] args) throws Exception {
    BufferedReader in = new BufferedReader(new FileReader("frac1.in"));
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("frac1.out")));
  	int n = Integer.parseInt(in.readLine());
  	ArrayList<Frac> fracs = new ArrayList<Frac>();
  	for(int denominator = 1; denominator <= n; denominator++)
  		for(int numerator = 0; numerator <= denominator; numerator++)
  			fracs.add(new Frac(numerator, denominator));
  	Collections.sort(fracs);
  	out.println("0/1");
  	for(int i = 1; i < fracs.size(); i++)
  		if(!fracs.get(i).equivalentTo(fracs.get(i-1)))
  			out.println(fracs.get(i));
  	out.close();
  }
  static class Frac implements Comparable<Frac> {
  	public int numerator;
  	public int denominator;
  	public Frac(int n, int d) {
  		numerator = n;
  		denominator = d;
  	}
  	public String toString() {
  		return numerator+"/"+denominator;
  	}
  	public int compareTo(Frac other) {
  		if(numerator * other.denominator == other.numerator * denominator)
  			return denominator - other.denominator;
  		return numerator * other.denominator - other.numerator * denominator;
  	}
  	public boolean equivalentTo(Frac other) {
  		return numerator * other.denominator == other.numerator * denominator;
  	}
  }
}