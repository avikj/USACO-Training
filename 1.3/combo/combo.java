/*
ID: avikjai1
LANG: JAVA
TASK: combo
*/

import java.io.*;
import java.util.*;

public class combo {
  public static void main(String[] args) throws Exception {
    Scanner in = new Scanner(new File("combo.in"));
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("combo.out")));
    int n = in.nextInt();
    int[] farmerCombo = new int[3];
    int[] masterCombo = new int[3];
    for(int i = 0; i < 3; i++) {
    	farmerCombo[i] = in.nextInt();
    }
    for(int i = 0; i < 3; i++) {
    	masterCombo[i] = in.nextInt();
    }
    int result = 2*(int)Math.pow(Math.min(5, n), 3);
    int[] distances = new int[3];
    for(int i = 0; i < 3; i++) {
    	distances[i] = Math.min(Math.abs(farmerCombo[i] - masterCombo[i]), n - Math.abs(farmerCombo[i] - masterCombo[i]));
    }
    int product = 1;
    for(int i = 0; i < 3; i++) {
    	product *= Math.min(n, Math.max(0, 5 - distances[i]));
    }
    result -= product;
    out.println(result);
    out.close();
  }
}