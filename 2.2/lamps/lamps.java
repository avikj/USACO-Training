/*
ID: avikjai1
LANG: JAVA
TASK: lamps
*/

import java.io.*;
import java.util.*;

public class lamps {
  static ArrayList<Integer> on = new ArrayList<Integer>();
  static ArrayList<Integer> off = new ArrayList<Integer>();

  public static void main(String[] args) throws Exception {
    Scanner in = new Scanner(new File("lamps.in"));
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("lamps.out")));
    int n = in.nextInt();
    int c = in.nextInt();
    int nextInt = in.nextInt();
    while(nextInt != -1) {
      on.add(nextInt-1);
      nextInt = in.nextInt();
    }
    nextInt = in.nextInt();
    while(nextInt != -1) {
      off.add(nextInt-1);
      nextInt = in.nextInt();
    }
    List<Integer> possibleFlips = generatePossibleFlips(c);
    List<String> result = new ArrayList<String>();
    for(int flipCombo : possibleFlips) {
      boolean[] testSwitches = new boolean[n];
      Arrays.fill(testSwitches, true);
      if((flipCombo | 0b0001) == flipCombo) {
        for(int i = 0; i < n; i++)
          testSwitches[i] = !testSwitches[i];
      }
      if((flipCombo | 0b0010) == flipCombo) {
        for(int i = 0; i < n; i+=2)
          testSwitches[i] = !testSwitches[i];
      }
      if((flipCombo | 0b0100) == flipCombo) {
        for(int i = 1; i < n; i+=2)
          testSwitches[i] = !testSwitches[i];
      }
      if((flipCombo | 0b1000) == flipCombo) {
        for(int i = 0; i < n; i+=3)
          testSwitches[i] = !testSwitches[i];
      }
      boolean matchesConstraints = true;
      for(int i : on) 
        if(!testSwitches[i])
          matchesConstraints = false;
      for(int i : off) 
        if(testSwitches[i])
          matchesConstraints = false;
      if(matchesConstraints)
        result.add(toString(testSwitches));
    }
    Collections.sort(result);
    for(String line : result)
      out.println(line);
    if(result.size() == 0)
      out.println("IMPOSSIBLE");
    out.close(); 
  }

  static List<Integer> generatePossibleFlips(int c) {
    if(c > 4)
      c = c % 2 == 0 ? 4 : 3;
    List<Integer> result = new ArrayList<Integer>();
    if(c % 2 == 0) { // no switches are flipped
      result.add(0000);
    }
    if(c == 1 || c == 3) { // 1 switch is flipped
      for(int i = 0; i < 4; i++) {
        int a = 0000;
        a ^= 1 << i;
        result.add(a);
      }
    } else if(c == 2 || c == 4) { // 2 switches are flipped
      for(int i = 0; i < 4; i++) {
        for(int j = i+1; j < 4; j++) {
          int a = 0000;
          a ^= 1 << i; 
          a ^= 1 << j; 
          result.add(a);
        }
      }
    }
    if(c == 3) {
      for(int i = 0; i < 4; i++) {
        int a = 0b1111;
        a ^= 1 << i;
        result.add(a);
      }
    }
    if(c == 4) {
      result.add(0b1111);
    }
    return result;
  }

  static String toString(boolean[] a) {
    String result = "";
    for(boolean b : a)
      result += b ? 1 : 0;
    return result;
  }

  static String toString(int a) {
    return String.format("%4s", Integer.toBinaryString(a)).replace(' ', '0');
  }
}