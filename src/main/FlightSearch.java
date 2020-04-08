package main;

import java.util.*;

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
	
	//Gets the min distance between current node and all adjacent nodes
	public static int minDistance(Flight prevFlight, Airport airport, ArrayList<Airport> unvisited) {
		double tempCost = 1;
		double tempFlightDur = 1;
		double tempLayover = 1;
		Airport tempAirport = null;
		Flight tempFlight = null;
		
		double tempWeight = 0;
		
		double min = (double) Integer.MAX_VALUE;
		int minIdx = -1;
		
		ArrayList<Flight> adjacent = airport.getOutFlights();
		
		for(int i=0; i<adjacent.size(); i++) {
			tempFlight = adjacent.get(i);
			tempAirport = tempFlight.getArrLoc();
			
			//If input airport is not depLoc, get layover time
			if(!airport.getCode().equals(depLoc.getCode())) {
				tempLayover = getLayoverTime(prevFlight, tempFlight);
			}
			
			tempCost = tempFlight.getFlightCost();
			tempFlightDur = tempFlight.getFlightDur();
			
			tempWeight = tempCost * tempFlightDur * tempLayover;
			
			if(!unvisited.contains(tempAirport) && tempWeight < min) {
				min = tempWeight;
				minIdx = i;
			}
		}
		
		return minIdx;
	}
	
	public static void dijkstras() {	
		ArrayList<Airport> unvisited = new ArrayList<Airport>(airports.size());
		
		//Copy all airports into unvisited list
		//If route isn't international, only adds airports in same country
		if(isIntl == false) {
			for(int i=0; i<airports.size(); i++) {
				if(airports.get(i).getCountry().equalsIgnoreCase(arrLoc.getCountry()))
					unvisited.add(airports.get(i));
			}
		} else {
			for(int i=0; i<airports.size(); i++) {
				unvisited.add(airports.get(i));
			}
		}
		
		
		
		
	}
	
	//Returns layover time in minutes
	public static double getLayoverTime(Flight arr, Flight dep) {
		int layover = 0;
		final int MILLIS_TO_MINUTE = 60000;
		
		/*
		int tempYear = 0;
		int tempMonth = 0;
		int tempDay = 0;
		int tempHour = 0;
		int tempMinute = 0;
		*/
		
		Calendar arrTime = arr.getDepDate();
		Calendar depTime = dep.getDepDate();
		
		arrTime.add(Calendar.MINUTE, arr.getFlightDur());
		
		long arrTimeMillis = arrTime.getTimeInMillis();
		long depTimeMillis = depTime.getTimeInMillis();
		
		if(arrTime.before(depTime)) {
			/*
			tempYear = arrTime.get(Calendar.YEAR);
			tempMonth = arrTime.get(Calendar.MONTH);
			tempDay = arrTime.get(Calendar.DATE);
			tempHour = arrTime.get(Calendar.HOUR);
			tempMinute = arrTime.get(Calendar.MINUTE);
			
			depTime.add(Calendar.YEAR, tempYear*-1);
			depTime.add(Calendar.MONTH, tempMonth*-1);
			depTime.add(Calendar.DATE, tempDay*-1);
			depTime.add(Calendar.HOUR, tempHour*-1);
			depTime.add(Calendar.MINUTE, tempMinute*-1);
			*/
			
			layover = ((int)(depTimeMillis - arrTimeMillis)) / MILLIS_TO_MINUTE;
			return (double) layover;
			
		} else
			return -1;
		
		
	}
	

}
