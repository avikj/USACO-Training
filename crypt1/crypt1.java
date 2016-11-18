/*
ID: avikjai1
LANG: JAVA
TASK: crypt1
*/

import java.io.*;
import java.util.*;

public class crypt1 {
  public static void main(String[] args) throws Exception {
    Scanner in = new Scanner(new File("crypt1.in"));
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("crypt1.out")));
    int n = in.nextInt();
    int[] digits = new int[n];
    for(int i = 0; i < n; i++) {
    	digits[i] = in.nextInt();
    }
		
		int result = countCryptarithms(new int[5], 0, digits);
		out.println(result);
		out.close();
  }
  // recursively iterates through all possible abcde and counts valid cryptarithms
  // digits is digits so far; first 3 are a b c, last 2 are d e
  // n is # of digits so far
  // possibleDigits is list of possibleDigits
  public static int countCryptarithms(int[] digits, int n, int[] possibleDigits) {
  	// base case, digits full
  	if(n == digits.length) {
  		return isValidCryptarithm(digits, possibleDigits) ? 1 : 0;
  	}
  	int result = 0;
  	for(int i = 0; i < possibleDigits.length; i++) {
  		digits[n] = possibleDigits[i];
  		result += countCryptarithms(digits, n+1, possibleDigits);
  	}
  	return result;
  }
  public static boolean isValidCryptarithm(int[] digits, int[] possibleDigits) {
  	int abc = 100*digits[0] + 10*digits[1] + digits[2];
  	int d = digits[3];
  	int e = digits[4];
  	int de = 10 * d + e;
  	return digitCount(abc*d) == 3 && digitCount(abc*e) == 3 && digitCount(abc*de) == 4 &&
  		madeFromDigits(abc * d, possibleDigits) && madeFromDigits(abc * e, possibleDigits) && 
  		madeFromDigits(abc * de, possibleDigits);
  }
  public static int digitCount(int num) {
  	int result = 0;
  	while(num > 0) {
  		num /= 10;
  		result++;
  	}
  	return result;
  }
  public static boolean madeFromDigits(int num, int[] digits) {
  	while(num > 0) {
  		if(!contains(num % 10, digits)) {
  			return false;
  		}
  		num /= 10;
  	}
  	return true;
  }
  public static boolean contains(int digit, int[] digits) {
  	for(int containedDigit : digits) 
  		if(digit == containedDigit)
  			return true;
  	return false;
  }
}