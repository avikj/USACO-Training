/*
ID: avikjai1
LANG: JAVA
TASK: preface
*/

import java.io.*;
import java.util.*;

public class preface {
  static final char[] digits = { 'I', 'V', 'X', 'L', 'C', 'D', 'M' };

  // first element in each array is a value; subsequent elements refer to indices for digits which are used in the representation of this value
  // 0: I, 1: V, 2: X, 3: L, 4: C, 5: D, 6: M
  static final int[][] digitCounts = {
    { 1, 0 },
    { 4, 0, 1 },
    { 5, 1 },
    { 9, 0, 2 },
    { 10, 2},
    { 40, 2, 3 },
    { 50, 3 },
    { 90, 2, 4 },
    { 100, 4 },
    { 400, 4, 5 },
    { 500, 5 },
    { 900, 4, 6 },
    { 1000, 6 }
  };

  public static void main(String[] args) throws Exception {
    BufferedReader in = new BufferedReader(new FileReader("preface.in"));
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("preface.out")));
    int n = Integer.parseInt(in.readLine());
    int[] result = new int[7];
    for(int page = 1; page <= n; page++) {
      int[] pageDigits = countRomanDigits(page);
      for(int i = 0; i < digits.length; i++) 
        result[i] += pageDigits[i];
    }
    int maxDig = -1;
    for(int i = 0; i < digits.length; i++) 
      if(result[i] > 0)
        maxDig = i;
    for(int i = 0; i <= maxDig; i++)
      out.println(digits[i]+" "+result[i]);
    out.close();
  }

  static int[] countRomanDigits(int n) {
    int[] result = new int[7];
    for(int i = digitCounts.length - 1; i >= 0; i--)
      while(n >= digitCounts[i][0]) {
        n -= digitCounts[i][0];
        for(int j = 1; j < digitCounts[i].length; j++)
          result[digitCounts[i][j]]++;
      }
    return result;
  }
}