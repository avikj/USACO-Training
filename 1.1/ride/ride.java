/*
ID: avikjai1
LANG: JAVA
TASK: ride
*/
import java.util.*;
import java.io.*;

public class ride {
  public static void main(String[] args) throws Exception {
    BufferedReader in = new BufferedReader(new FileReader("ride.in"));
    String comet = in.readLine();
    String group = in.readLine();
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("ride.out")));
    out.println(getNumber(comet)%47 == getNumber(group)%47 ? "GO" : "STAY");
    out.close();
  }

  public static long getNumber(String str){
    long result = 1;
    for(int i = 0; i < str.length(); i++){
      result *= str.charAt(i) - 'A' + 1;
    }
    return result;
  }
}
