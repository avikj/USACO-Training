/*
ID: avikjai1
LANG: JAVA
TASK: transform
*/

import java.io.*;
import java.util.*;

public class transform {
  public static void main(String[] args) throws Exception {
    BufferedReader in = new BufferedReader(new FileReader("transform.in"));
    int n = Integer.parseInt(in.readLine());
    char[][] initialState = new char[n][n];
    for(int i = 0; i < n; i++) {
    	initialState[i] = in.readLine().toCharArray();
    }
    char[][] finalState = new char[n][n];
    for(int i = 0; i < n; i++) {
    	finalState[i] = in.readLine().toCharArray();
    }
    if(equal(rotate(initialState), finalState)) {
    	done(1);
    } else if(equal(rotate(rotate(initialState)), finalState)) {
    	done(2);
    } else if(equal(rotate(rotate(rotate(initialState))), finalState)) {
    	done(3);
    } else if(equal(reflect(initialState), finalState)) {
    	done(4);
    } else if(equal(rotate(reflect(initialState)), finalState) || equal(rotate(rotate(reflect(initialState))), finalState) || equal(rotate(rotate(rotate(reflect(initialState)))), finalState)) {
    	done(5);
    } else if(equal(initialState, finalState)) {
    	done(6);
    }
    done(7);
  }
  public static char[][] rotate(char[][] a) {
  	char[][] b = new char[a.length][a.length];
  	for(int i = 0; i < a.length; i++) {
  		for(int j = 0; j < a.length; j++) {
  			b[j][a.length-i-1] = a[i][j];
  		}
  	}
  	return b;
  }
  public static char[][] reflect(char[][] a) {
  	char[][] b = new char[a.length][a.length];
  	for(int i = 0; i < a.length; i++) {
  		for(int j = 0; j < a.length; j++) {
  			b[i][a.length-j-1] = a[i][j];
  		}
  	}
  	return b;
  }
  public static void done(int result) throws Exception {
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("transform.out")));
    out.println(result);
    out.close();
    System.exit(0);
  }
  public static boolean equal(char[][] a, char[][] b) {
  	for(int i = 0; i < a.length; i++) {
  		for(int j = 0; j < a[i].length; j++) {
  			if(a[i][j] != b[i][j]) {
  				return false;
  			}
  		}
  	}
  	return true;
  }
}