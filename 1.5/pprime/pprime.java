/*
ID: avikjai1
LANG: JAVA
TASK: pprime
*/

import java.io.*;
import java.util.*;

public class pprime {
	static int a;
	static int b;
	static PrintWriter out;
  public static void main(String[] args) throws Exception {
    Scanner in = new Scanner(new File("pprime.in"));
    out = new PrintWriter(new BufferedWriter(new FileWriter("pprime.out")));
    a = in.nextInt();
    b = in.nextInt();
    for(int digits = String.valueOf(a).length(); digits <= String.valueOf(b).length(); digits++) 
    	generatePrimePalindromes("", digits);
    out.close();
  }

  public static void generatePrimePalindromes(String current, int digits) {
  	if(current.equals("0")) 
  		return;
  	if(current.length() == (digits + 1)/2) { // base case for generating palindromes
  		for(int i = digits/2 - 1; i >= 0; i--)
  			current += current.charAt(i);
  		int currentInt = Integer.parseInt(current);
  		if(isPrime(currentInt) && currentInt >= a && currentInt <= b)
  			out.println(current);
  		return;
  	}
  	for(int nextDigit = 0; nextDigit < 10; nextDigit++) 
  		if(!current.equals("") || nextDigit%2 == 1)
  			generatePrimePalindromes(current + nextDigit, digits);
  }

  public static boolean isPrime(int n) {
  	// try to speed up method by only testing odds and 0 since earlier it was just over time limit
  	if(n == 1 || n % 2 == 0) 
  		return false;
  	for(int i = 3; i <= Math.sqrt(n); i+=2)
  		if(n % i == 0)
  			return false;
  	return true;
  }
}