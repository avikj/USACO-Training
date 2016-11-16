/*
ID: avikjai1
LANG: JAVA
TASK: gift1
*/

import java.io.*;
import java.util.*;

public class gift1 {
  public static void main(String[] args) throws Exception {
    BufferedReader in = new BufferedReader(new FileReader("gift1.in"));
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("gift1.out")));
    
    HashMap<String, Integer> map = new HashMap<String, Integer>();
    int np = Integer.parseInt(in.readLine());
    String[] people = new String[np];
    for(int i = 0; i < np; i++){
      people[i] = in.readLine();
      map.put(people[i], 0);
    }

    for(int i = 0; i < np; i++){
      String person = in.readLine();
      StringTokenizer st = new StringTokenizer(in.readLine());
      int money = Integer.parseInt(st.nextToken());
      int nr = Integer.parseInt(st.nextToken());
      for(int j = 0; j < nr; j++){
        String r = in.readLine();
        map.put(r, map.get(r)+money/nr);
        map.put(person, map.get(person)-money/nr);
      }
    }

    for(int i = 0; i < np; i++){
      out.println(people[i]+" "+map.get(people[i]));
    }
    out.close();
  }
}
