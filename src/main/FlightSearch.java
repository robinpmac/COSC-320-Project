package main;

import java.util.*;
import java.lang.*;
import java.io.*;

public class FlightSearch {
	
	private static ArrayList<Airport> airports;
	private static ArrayList<Flight> flights;
	
	private static String depLocCode = "YVR";
	private static String arrLocCode = "YYZ";
	
	private static Airport depLoc = null;
	private static Airport arrLoc = null;
	
	private static int numAirports;
	
	private static boolean isIntl;

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
	
	//Set the departure and arrival locations based on given airport codes
	public static boolean setStartFinish() {
		boolean loadSuccess = false;
		boolean depLocLoad = false;
		boolean arrLocLoad = false;
		Airport temp = null;
		
		Iterator<Airport> itr = airports.iterator();
		
		while(itr.hasNext() && loadSuccess == false) {
			temp = itr.next();
			
			if(temp.getCode().equals(depLocCode)) {
				depLoc = temp;
				depLocLoad = true;
			}
			
			if(temp.getCode().equals(arrLocCode)) {
				arrLoc = temp;
				arrLocLoad = true;
			}
			
			if(depLocLoad && arrLocLoad)
				loadSuccess = true;	
		}
		
		if(loadSuccess == false) {
			depLoc = null;
			arrLoc = null;
		} else {
			isIntl = checkIsIntl();
		}
		
		
		
		return loadSuccess;
	}
	
	public static void main(String[] args) {
		loadAll();
		
		ArrayList<Airport> unvisited = new ArrayList<Airport>(numAirports);
		
		for(int i=0; i<airports.size(); i++) {
			unvisited.add(airports.get(i));
		}
		
		boolean loadSuccess = setStartFinish();
		if(loadSuccess == false) {
			System.out.println("Error Loading Departure and Arrival Locations.");
			System.exit(0);
		}
		

	}
	
	public static boolean checkIsIntl() {
		if(depLoc.getCountry().equalsIgnoreCase(arrLoc.getCountry()))
			return false;
		else
			return true;
	}
	
	public static 
	
	//Ignore for now
	int minDistance(Airport currNode) {
		int min = Integer.MAX_VALUE;
		int minIdx = -1;
		
		
		
		
		return -1;
	}

}
