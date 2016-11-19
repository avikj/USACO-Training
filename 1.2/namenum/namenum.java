/*
ID: avikjai1
LANG: JAVA
TASK: namenum
*/

import java.io.*;
import java.util.*;

public class namenum {
  public static void main(String[] args) throws Exception {
  	HashMap<Character, Character> map = new HashMap<Character, Character>();
  	map.put('A', '2');
  	map.put('B', '2');
  	map.put('C', '2');
  	map.put('D', '3');
  	map.put('E', '3');
  	map.put('F', '3');
  	map.put('G', '4');
  	map.put('H', '4');
  	map.put('I', '4');
  	map.put('J', '5');
  	map.put('K', '5');
  	map.put('L', '5');
  	map.put('M', '6');
  	map.put('N', '6');
  	map.put('O', '6');
  	map.put('P', '7');
  	map.put('R', '7');
  	map.put('S', '7');
  	map.put('T', '8');
  	map.put('U', '8');
  	map.put('V', '8');
  	map.put('W', '9');
  	map.put('X', '9');
  	map.put('Y', '9');
    BufferedReader in = new BufferedReader(new FileReader("namenum.in"));
    Scanner namesList = new Scanner(new File("dict.txt"));
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("namenum.out")));
    String num = in.readLine();
    boolean none = true;
    while(namesList.hasNextLine()) {
    	String currentName = namesList.nextLine();
    	String currentNum = "";
    	for(int i = 0; i < currentName.length(); i++) {
    		currentNum+=map.get(currentName.charAt(i));
    	}
    	if(currentNum.equals(num)) {
    		none = false;
    		out.println(currentName);
    	}
    }
    if(none) {
    	out.println("NONE");
    }
    out.close();
  }
}