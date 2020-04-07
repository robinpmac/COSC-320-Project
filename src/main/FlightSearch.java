package main;

import java.util.*;
import java.lang.*;
import java.io.*;

public class FlightSearch {
	
	private static ArrayList<Airport> airports;
	private static ArrayList<Flight> flights;
	
	
	private static int numAirports;

	//Pre-processing for the algorithm
	public static void loadAll() {
		airports = CSVReader.loadAirports();
		flights = CSVReader.loadFlights();
		
		numAirports = airports.size();
		
		//Load flights into airports
		for(int i=0; i<airports.size(); i++) {
			airports.get(i).loadFlightData(flights);
		}
	}
	
	public static void main(String[] args) {
		loadAll();
		
		ArrayList<Airport> unvisited = new ArrayList<Airport>(numAirports);
		
		for(int i=0; i<airports.size(); i++) {
			unvisited.add(airports.get(i));
		}
		

	}
	
	int minDistance() {
		return -1;
	}

}
