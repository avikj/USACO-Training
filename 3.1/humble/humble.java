/*
ID: avikjai1
LANG: JAVA
TASK: humble
*/

import java.io.*;
import java.util.*;

public class humble {
  static int[] p;
  public static void main(String[] args) throws Exception {
    Scanner in = new Scanner(new File("humble.in"));
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("humble.out")));
    int k = in.nextInt();
    int n = in.nextInt();
    p = new int[k];
    for(int i = 0; i < k; i++)
      p[i] = in.nextInt();
    long[] h = new long[n];
    int r = 0;
    HashSet<Pair> set = new HashSet<Pair>();
    HashSet<Long> valueSet = new HashSet<Long>();
    PriorityQueue<Pair> q = new PriorityQueue<Pair>();
    q.add(new Pair(1, 0));
    int i = 0;
    while(i < n) {
      Pair next = q.poll();
      if(i == 0 || next.value != h[i-1])
        h[i++] = next.value;
      Pair a = new Pair(next.multiplier, next.primeIndex+1);
      while(a.primeIndex < k) {
        if(!valueSet.contains(a.value)) {
          q.add(a);
          set.add(a);
          valueSet.add(a.value);
          break;
        }
        a.incrementPrimeIndex();
      }
      Pair b = new Pair(next.value, 0);
      if(!set.contains(b)) {
        set.add(b);
        q.add(b);
        valueSet.add(b.value);
      }
    }
    out.println(h[n-1]);
    out.close();
  }
  static class Pair implements Comparable {
    public int primeIndex;
    public long multiplier;
    public long value;
    public Pair(long multiplier, int primeIndex) {
      this.primeIndex = primeIndex;
      this.multiplier = multiplier;
      value = primeIndex < p.length ? p[primeIndex]*multiplier : -1;
    }
    public void incrementPrimeIndex() {
      primeIndex++;
      value = primeIndex < p.length ? p[primeIndex]*multiplier : -1;
    }
    public int compareTo(Object o) {
      if(p[primeIndex]*multiplier > p[((Pair)o).primeIndex]*((Pair)o).multiplier)
        return 1;
      return -1;
    }
    public int hashCode() {
      return Long.valueOf(multiplier).hashCode()*p.length+primeIndex;
    }
    public boolean equals(Object o) {
      return this.hashCode() == o.hashCode();
    }
  }
}