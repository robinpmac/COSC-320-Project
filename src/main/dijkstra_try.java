package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class dijkstra_try {
	
	//airport would be the arraylist with nodes
	//flightlist would be the arraylist with edges
	public static ArrayList<Airport> airport = CSVReader.loadAirports();
	public static ArrayList<Flight> flightlist = CSVReader.loadFlights();
			
	public static final int numVertex = airport.size();
	public List<Airport> visited = new ArrayList<>();
	public List<Airport> unvisited = new ArrayList<>(airport);
	public static int[][] adMatrix;
	public static List<String> shortest = new ArrayList<>();
	
	public static void main(String[] args) {
		long t1, t2;
		int totalTime = 0;
		
		t1 = System.currentTimeMillis();
		//matrix of shortest distance
		adMatrix = new int[numVertex][numVertex];
			//Initialize the Shortest Distance matrix
			for(int i = 0; i < numVertex;i++) {
				adMatrix[i][i] = 0;
			}
			
			//Input Data from csv file
			  MapData(airport, flightlist); 
			  InputWeight(airport);
			 //
			  
			  
			  
			  
			  //Print out the Shortest Distance in adjacency matrix
			  System.out.println("Adjacency matrix: ");
			 System.out.print("" + " \t");
		for(int s = 0; s < numVertex;s++) {
			System.out.print(airport.get(s).getCode() + " \t");
		}System.out.println("");
		for(int i = 0; i < numVertex;i++) {
			System.out.print(airport.get(i).getCode() + " \t");
			for(int j = 0; j < numVertex;j++) {
				System.out.print(adMatrix[i][j] + " \t");
			}
			System.out.println("");
		}//
		t1 = System.currentTimeMillis() - t1;
		totalTime += Math.toIntExact(t1);
		
		Scanner in = new Scanner(System.in);
		System.out.println("Please choose a starting point:");
		for(int m = 0; m < numVertex;m++) {
			System.out.println(m + "->" + airport.get(m).getCode());
		}
		System.out.println();
		int src = Integer.parseInt(in.next());
		System.out.println("Please choose a destination:");
		int des = Integer.parseInt(in.next());
		
		//Algorithm and Timer
		t2 = System.currentTimeMillis();
		dijkstra(adMatrix,src);
		t2 = System.currentTimeMillis() - t2;
		totalTime += Math.toIntExact(t2);

		System.out.println("The whole is :");
		for(String s : shortest) {
			System.out.print(s+ "->");
		}
		
		System.out.println();
		
		String destination = airport.get(des).getCode();
		int counter = 0;
		System.out.println("The shortestPath is :");
		while(!destination.equals(shortest.get(counter))) {
			System.out.print(shortest.get(counter)+ "->");
			counter++;
		}
		
		in.close();
		System.out.println(shortest.get(counter));
		System.out.printf("Total Algorithm Running Time: %d ms%n", totalTime);
		}

	
	//Credit: The following code snippet is used and modified from the source code provided on: https://www.geeksforgeeks.org/dijkstras-shortest-path-algorithm-greedy-algo-7/
	
	static int minDistance(int dist[], Boolean sptSet[]) 
    { 
        // Initialize min value 
        int min = Integer.MAX_VALUE, min_index = -1; 
  
        for (int v = 0; v < numVertex; v++) 
            if (sptSet[v] == false && dist[v] <= min) { 
                min = dist[v]; 
                min_index = v; 
            } 
  
        return min_index; 
    } 
  
    // A utility function to print the constructed distance array 
    static void printSolution(int dist[]) 
    { 
        System.out.println("Vertex \t\t Distance from Source"); 
        for (int i = 0; i < numVertex; i++) 
            System.out.println(airport.get(i).getCode() + " \t\t " + dist[i]); 
    } 
  
    // Function that implements Dijkstra's single source shortest path 
    // algorithm for a graph represented using adjacency matrix 
    // representation 
    public static void dijkstra(int graph[][], int src) 
    { 
        int dist[] = new int[numVertex]; // The output array dist[i] will hold 
        // the shortest distance from src to i 
  
        // sptSet[i] will true if vertex i is included in shortest 
        // path tree or shortest distance from src to i is finalized 
        Boolean sptSet[] = new Boolean[numVertex]; 
  
        // Initialize all distances as INFINITE and stpSet[] as false 
        for (int i = 0; i < numVertex; i++) { 
            dist[i] = Integer.MAX_VALUE; 
            sptSet[i] = false; 
        } 
  
        // Distance of source vertex from itself is always 0 
        dist[src] = 0; 
  
        // Find shortest path for all vertices 
        for (int count = 0; count < numVertex - 1; count++) { 
            // Pick the minimum distance vertex from the set of vertices 
            // not yet processed. u is always equal to src in first 
            // iteration. 
            int u = minDistance(dist, sptSet); 
  
            // Mark the picked vertex as processed 
            sptSet[u] = true; 
  
            // Update dist value of the adjacent vertices of the 
            // picked vertex. 
            for (int v = 0; v < numVertex; v++) 
  
                // Update dist[v] only if is not in sptSet, there is an 
                // edge from u to v, and total weight of path from src to 
                // v through u is smaller than current value of dist[v] 
                if (!sptSet[v] && graph[u][v] != 0 && dist[u] != Integer.MAX_VALUE && dist[u] + graph[u][v] < dist[v]) {
                    dist[v] = dist[u] + graph[u][v];
                    
                }
            shortest.add(airport.get(u).getCode());
            } 
        printSolution(dist);
        
       
        } //
  
       
    
    
	public static void InputWeight(ArrayList<Airport> nodes) {
		ArrayList<String> airportName = new ArrayList<>();
		for(Airport a : nodes) {
			airportName.add(a.getCode());
		}
		for(int i = 0; i < numVertex; i ++) {
			Airport CurrentAirport = nodes.get(i);
			ArrayList<Flight> outboundflights = CurrentAirport.getOutFLights();
			for(Flight f : outboundflights) {
				String arrival = f.getArrLocStr();
				int k = airportName.indexOf(arrival);
				adMatrix[i][k] = adMatrix[k][i] = ComputeWeight(f);
				}
			}
		}
		
	
	public static void MapData(ArrayList<Airport> nodes, ArrayList<Flight> flights) {
		for(Airport i : nodes) {
			for(Flight f : flights) {
				if(f.getDepLocStr().equals(i.getCode())) {
					i.addOutFlight(f);
				}
			}
			
		}
		
	}
	
	
	public static int ComputeWeight(Flight f) {
		return (int)Math.round(f.getFlightCost() * f.getFlightDur() * f.getLayover())/1000000;
	}
	
	
}
