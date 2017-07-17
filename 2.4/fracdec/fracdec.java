/*
ID: avikjai1
LANG: JAVA
TASK: fracdec
*/

import java.io.*;
import java.util.*;

public class fracdec {
  public static void main(String[] args) throws Exception {
    Scanner in = new Scanner(new File("fracdec.in"));
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("fracdec.out")));
    int n = in.nextInt();
    int d = in.nextInt();
    String wholePart = String.valueOf(n/d);
    n = n % d;
    HashMap<Integer, Integer> previousNIndices = new HashMap<Integer, Integer>();
    StringBuilder decimalPartBuilder = new StringBuilder();
    // long division algorithm
    int index = 0;
    while(true) {
      n*=10;
      if(previousNIndices.containsKey(n)) // new repeated block starts here
        break;
      previousNIndices.put(n, index);
      int nextDigit = n/d;
      decimalPartBuilder.append(nextDigit);
      n -= d*nextDigit;
      index++;
    }
    int repeatStart = previousNIndices.get(n);
    String decimalPart = decimalPartBuilder.toString();
    decimalPart = decimalPart.substring(0, repeatStart)+"("+decimalPart.substring(repeatStart)+")";
    decimalPart = decimalPart.replace("(0)", "");
    if(decimalPart.length() == 0)
      decimalPart = "0";
    String result = wholePart+"."+decimalPart;
    for(int i = 0; i < result.length(); i+=76)
      out.println(result.substring(i, Math.min(i+76, result.length())));
    out.close();
  }
}