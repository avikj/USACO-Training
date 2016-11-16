/*
ID: avikjai1
LANG: JAVA
TASK: palsquare
*/

import java.io.*;
import java.util.*;

public class palsquare {
  public static void main(String[] args) throws Exception {
    BufferedReader in = new BufferedReader(new FileReader("palsquare.in"));
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("palsquare.out")));
    int b = Integer.parseInt(in.readLine());
    for(int i = 1; i < 300; i++) {
    	if(isPal(Integer.toString(i*i, b))) {
    		out.println(Integer.toString(i, b).toUpperCase()+" "+Integer.toString(i*i, b).toUpperCase());
    	}
    }
    out.close();
  }

  public static boolean isPal(String s) {
  	for(int i = 0; i < s.length(); i++) {
  		if(s.charAt(i) != s.charAt(s.length()-i-1)) {
  			return false;
  		}
  	}
  	return true;
  }
}