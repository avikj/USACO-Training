/*
ID: avikjai1
LANG: JAVA
TASK: test
*/
import java.io.*;
import java.util.*;

public class test {
  public static void main(String[] args) throws Exception {
    BufferedReader reader = new BufferedReader(new FileReader("test.in"));
    StringTokenizer st = new StringTokenizer(reader.readLine());
    int a = Integer.parseInt(st.nextToken());
    int b = Integer.parseInt(st.nextToken());
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("test.out")));
    out.println(a+b);
    out.close();
  }
}
