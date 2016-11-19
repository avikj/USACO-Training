/*
ID: avikjai1
LANG: JAVA
TASK: dualpal
*/

import java.io.*;
import java.util.*;

public class dualpal {
  public static void main(String[] args) throws Exception {
    Scanner in = new Scanner(new File("dualpal.in"));
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("dualpal.out")));
    int n = in.nextInt();
    int s = in.nextInt();
    for(int i = s+1; n > 0; i++) {
    	int count = 0;
    	for(int b = 2; b <= 10; b++) {
    		if(isPal(Integer.toString(i, b))) {
    			count++;
    		}
    	}
    	if(count >= 2) {
    		out.println(i);
    		n--;
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