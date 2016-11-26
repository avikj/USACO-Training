/*
ID: avikjai1
LANG: JAVA
TASK: runround
*/

import java.io.*;
import java.util.*;

public class runround {
  static long m;
  public static void main(String[] args) throws Exception {
    Scanner in = new Scanner(new File("runround.in"));
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("runround.out")));
    m = in.nextLong();
    
    for(int len = 1; len <= 9; len++) {
      int[] result = solve(new int[len], 0, new boolean[10], len);
      if(result != null) {
        for(int digit : result)
          out.print(digit);
        out.println();
        break;
      }
    }
    out.close();
  }

  // generates all possible permutations of n unique digits and finds smallest runaround greater than m
  static int[] solve(int[] digits, int k, boolean[] selectedDigits, int n) {
    if(k == n) {
      if(isValidRunaround(digits))
        return digits;
      return null;
    }
    for(int i = 1; i < 10; i++) {
      if(!selectedDigits[i]) {
        selectedDigits[i] = true;
        digits[k] = i;
        int[] r = solve(digits, k+1, selectedDigits, n);
        if(r != null)
          return r;
        selectedDigits[i] = false;
      }
    }
    return null;
  }
  static boolean isValidRunaround(int[] n) {
    String str = "";    
    for(int a : n)
      str += a;
    if(Long.parseLong(str) <= m) // needs to be larger than m
      return false;
    for(int i = 0; i < str.length(); i++) // ensure digits are unique
      if(str.replace(str.charAt(i)+"", "").length() != str.length() - 1)
        return false;
    boolean[] visited = new boolean[str.length()];
    int index = 0;
    for(int i = 0; i < str.length(); i++) {
      visited[index] = true;
      index += str.charAt(index) - '0';
      index %= str.length();
    }
    if(index != 0) // should end up at start again
      return false;
    for(int i = 0; i < visited.length; i++) // should have visited each digit
      if(!visited[i])
        return false;
    return true;
  }
}