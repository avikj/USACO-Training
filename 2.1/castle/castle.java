/*
ID: avikjai1
LANG: JAVA
TASK: castle
*/

import java.io.*;
import java.util.*;

public class castle {
	static int componentNum;
	static int[] component;
	static Module[] modules;
	static int m;
	static int n;
	static int maxRoomSize = 0;
	static int[] componentSizes;
  public static void main(String[] args) throws Exception {
    Scanner in = new Scanner(new File("castle.in"));
    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("castle.out")));

    m = in.nextInt(); n = in.nextInt();
    modules = new Module[n*m];
    for(int i = 0; i < n; i++)
    	for(int j = 0; j < m; j++)
    		modules[i*m+j] = new Module(new Coord(i, j), in.nextInt());
    System.out.println(Arrays.toString(modules));

    // assign each module to a component with breadth first scanning floodfill
    component = new int[n*m];
    componentSizes = new int[n*m+1];
    Arrays.fill(component, -1);
    for(int i = 0; i < n; i++) {
    	for(int j = 0; j < m; j++) {
	    	if(component[i*m+j] == -1) { // unassigned
	    		componentNum++;
	    		component[i*m+j] = -2;
	    		floodFill(componentNum);
    		}
    	}
    }
    out.println(componentNum); // number of rooms
    out.println(maxRoomSize);
    
    // determine optimal wall to remove
    int maxWhenRemove = -1;
    Coord bestCoords = null;
    String direction = "";
    for(int i = 0; i < n; i++) { // remove vertical wall between (i, j) and (i, j+1)
    	for(int j = 0; j < m - 1; j++) {
    		if(component[i*m+j] != component[i*m+j+1]) { // if breaking this wall would merge 2 different rooms
    			int potentialNewMax = componentSizes[component[i*m+j]] + componentSizes[component[i*m+j+1]];
    			if(bestCoords == null || 
    					potentialNewMax > maxWhenRemove || 
    					(potentialNewMax == maxWhenRemove && (j < bestCoords.j || (j == bestCoords.j && i > bestCoords.i)))) {
    				maxWhenRemove = potentialNewMax;
    				bestCoords = new Coord(i, j);
    				direction = "E";
    			}
    		}
    	}
    }
    for(int i = 0; i < n - 1; i++) {	// remove horizontal wall between (i, j) and (i+1, j)
    	for(int j = 0; j < m; j++) {
    		if(component[i*m+j] != component[(i+1)*m+j]) { // if breaking this wall would merge 2 different rooms
    			int potentialNewMax = componentSizes[component[i*m+j]] + componentSizes[component[(i+1)*m+j]];
    			if(bestCoords == null || 
    					potentialNewMax > maxWhenRemove || 
    					(potentialNewMax == maxWhenRemove && (j < bestCoords.j || (j == bestCoords.j && i + 1 >= bestCoords.i)))) {
    				maxWhenRemove = potentialNewMax;
    				bestCoords = new Coord(i+1, j);
    				direction = "N";
    			}
    		}
    	}
    }
    out.println(maxWhenRemove);
    out.println((bestCoords.i+1)+" "+(bestCoords.j+1)+" "+direction);
    out.close();
  }
  public static void floodFill(int componentNum) {
  	int numVisited;
  	int roomSize = 0;
  	do {
  		numVisited = 0;
  		for(int i = 0; i < n; i++) {
	    	for(int j = 0; j < m; j++) {
	    		if(component[i*m+j] == -2) {
	    			numVisited++;
	    			component[i*m+j] = componentNum;
	    			roomSize++;
	    			for(Coord adj : modules[i*m+j].adjacencyList) {
	    				if(component[adj.i*m+adj.j] == -1) {
	    					component[adj.i*m+adj.j] = -2;
	    				}
	    			}
	    		}
	   		}
	  	}	
  	} while(numVisited != 0);
  	componentSizes[componentNum] = roomSize;
  	maxRoomSize = Math.max(roomSize, maxRoomSize);
  }
  static class Module {
  	public ArrayList<Coord> adjacencyList;
  	public Coord location;
  	public Module(Coord location, int sum) {
  		this.location = location;
  		this.adjacencyList = new ArrayList<Coord>(4);
  		if(sum < 8) 
  			adjacencyList.add(location.nextSouth());
  		if(sum % 8 < 4) 
  			adjacencyList.add(location.nextEast());
  		if(sum % 4 < 2) 
  			adjacencyList.add(location.nextNorth());
  		if(sum % 2 < 1) 
  			adjacencyList.add(location.nextWest());
  	}
  	public String toString() {
  		String result =  String.format("(%d, %d) -> [", location.i, location.j);
  		for(Coord coord : adjacencyList)
  			result += "("+coord.i+", "+coord.j+"), ";
  		result = result.substring(0, result.length()-2)+"]";
  		return result;
  	}
  }
  static class Coord {
  	public int i;
  	public int j;

  	public Coord(int i, int j) {
  		this.i = i;
  		this.j = j;
  	}
  	public Coord nextSouth() {
  		return new Coord(i+1, j);
  	}
  	public Coord nextEast() {
  		return new Coord(i, j+1);
  	}
  	public Coord nextNorth() {
  		return new Coord(i-1, j);
  	}
  	public Coord nextWest() {
  		return new Coord(i, j-1);
  	}

  	public String toString() {
  		return "("+i+", "+j+")";
  	}
  	public boolean equals(Object other) {
  		return other instanceof Coord && ((Coord)other).i == this.i && ((Coord)other).j == this.j;
  	}
  }
}