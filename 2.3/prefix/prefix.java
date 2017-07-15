/*
ID: avikjai1
LANG: JAVA
TASK: prefix
*/

import java.io.*;
import java.util.*;

public class prefix {
  public static void main(String[] args) throws Exception {
    long startTime = System.nanoTime();
    BufferedReader in = new BufferedReader(new FileReader("prefix.in"));
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("prefix.out")));
    List<String> primitives = new ArrayList<String>();
    String line = in.readLine();
    while(line.charAt(0) != '.') {
      for(String primitive : line.split(" "))
        primitives.add(primitive);
      line = in.readLine();
    }
    // use StringBuilder because adding big strings is really slow
    StringBuilder sb = new StringBuilder();
    while((line = in.readLine()) != null)
      sb.append(line);
    String s = sb.toString();
    Trie trie = new Trie();
    // Built tree in O(M*N) where M is max primitive length and N is number of primitives (10 and 200 respectively)
    for(String p : primitives)
      trie.add(p);
    Integer[][] primitiveSubstringLengthsAt = new Integer[s.length()][];
    for(int i = 0; i < primitiveSubstringLengthsAt.length; i++)
      primitiveSubstringLengthsAt[i] = trie.getPrefixLengths(s, i);
    // Compute longest prefix in O(M*N) where M is sequence length and N is the max length of a primitive
    int[] longestSubstringFrom = new int[s.length()+1];
    for(int i = s.length() - 1; i >= 0; i--)
      for(int prefixLength : primitiveSubstringLengthsAt[i])
        longestSubstringFrom[i] = Math.max(longestSubstringFrom[i], prefixLength + longestSubstringFrom[i+prefixLength]);
    out.println(longestSubstringFrom[0]);
    out.close();
  }
  public static int[] lengths(String[] arr) {
    int[] result = new int[arr.length];
    int i = 0;
    for(String s : arr)
      result[i++] = s.length();
    return result;
  }
  static class Trie {
    public Trie[] children;
    public boolean isPrimitive;
    public Trie parent;
    public int count;
    public Trie() {
      this(null);
    }
    public Trie(Trie parent) {
      children = new Trie[26];
      isPrimitive = false;
      this.parent = parent;
      count = 0;
    }
    public void add(String s) {
      count++;
      if(s.length() == 0)
        isPrimitive = true;
      else {
        if(children[s.charAt(0)-'A'] == null)
          children[s.charAt(0)-'A'] = new Trie(this);
        children[s.charAt(0)-'A'].add(s.substring(1));
      }
    }
    public String[] getStrings() {
      String[] result = new String[count];
      int j = 0;
      if(isPrimitive)
        result[j++] = "";
      for(int i = 0; i < 26; i++)
        if(children[i] != null)
          for(String s : children[i].getStrings())
            result[j++] = (char)(i+'A')+s;
      return result;
    }
    public Integer[] getPrefixLengths(String s, int startIndex) {
      ArrayList<Integer> result = new ArrayList<Integer>();
      if(isPrimitive)
        result.add(0);
      if(s.length() == startIndex)
        return result.toArray(new Integer[result.size()]);
      if(children[s.charAt(startIndex)-'A'] != null)
        for(int prefixEndingLengths : children[s.charAt(startIndex)-'A'].getPrefixLengths(s, startIndex+1))
          result.add(1+prefixEndingLengths);
      return result.toArray(new Integer[result.size()]);
    }
  }
}

