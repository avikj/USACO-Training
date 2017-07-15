/*
ID: avikjai1
LANG: JAVA
TASK: zerosum
*/

import java.io.*;
import java.util.*;

public class zerosum {
  public static void main(String[] args) throws Exception {
    Scanner in = new Scanner(new File("zerosum.in"));
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("zerosum.out")));
    int n = in.nextInt();
    ArrayList<int[]> result = new ArrayList<int[]>();
    solve(new int[n-1], n, 0, result);
    String[] resultStrs = new String[result.size()];
    int i = 0;
    for(int[] signs : result)
      resultStrs[i++] = toString(n, signs);
    Arrays.sort(resultStrs);
    for(String s : resultStrs)
      out.println(s);
    out.close();
  }
  static void solve(int[] signs, int n, int i, ArrayList<int[]> result) {
    if(i == n-1) {// signs has been completely populated, now check if sum is 0
      if(sum(n, signs) == 0)
        result.add((int[])(signs.clone()));
    }
    else
      for(signs[i] = 0; signs[i] < 3; signs[i]++)
        solve(signs, n, i+1, result);
  }
  static int sum(int n, int[] signs) {
    int i = 0;
    ArrayList<Integer> terms = new ArrayList<Integer>();
    terms.add(1);
    for(int v = 2; v <= n; v++)
      switch(signs[i++]) {
        case 0: // +
          terms.add(v);
          break;
        case 1: // -
          terms.add(-v);
          break;
        case 2: // blank
          int lastTerm = terms.get(terms.size()-1);
          terms.set(terms.size()-1, lastTerm/Math.abs(lastTerm)*(Math.abs(lastTerm*10)+v));
      }
    int sum = 0;
    for(int term : terms)
      sum += term;
    return sum;
  }
  static String toString(int n, int[] signs) {
    int i = 0;
    String result = "1";
    for(int v = 2; v <= n; v++)
      switch(signs[i++]) {
        case 0: // +
          result += "+"+v;
          break;
        case 1: // -
          result += "-"+v;
          break;
        case 2: // blank
          result += " "+v;
      }
    return result;
  }
}