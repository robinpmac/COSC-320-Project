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
	
	//Find all paths from depLoc to arrLoc
	public static ArrayList<Route> getAllRoutes() {

		ArrayList<Route> routes = new ArrayList<Route>();
		Stack<Airport> s = new Stack<Airport>();
		
		Airport currNode = null;
		ArrayList<Flight> tempOutFlights = null;
		//Route currRoute = null;
		//int tempID = 0;
		//boolean isPath = false;
		
		s.push(depLoc);
		//routes.add(new Route(depLoc));
		
		//While there are items in the stack
		while(s.isEmpty() == false) {
			currNode = s.pop();
			//currRoute = getRouteByID(routes, tempID);
				
			if(currNode.isDiscovered() == false) {
				currNode.setDiscovered(true);
				tempOutFlights = currNode.getOutFlights();
				
				//Checks to make sure outbound flight list for node isn't empty
				if(tempOutFlights.isEmpty() == false) {
					for(int i=0; i<tempOutFlights.size(); i++) {
						s.push(tempOutFlights.get(i).getArrLoc());
						//routes.add(new Route(tempOutFlights.get(i).getArrLoc(), currRoute));
					}
				}
			}
		}
			
		return routes;
	}
	
	public static Route getRouteByID(ArrayList<Route> routes, int ID) {
		for(int i=0; i<routes.size(); i++) {
			if(routes.get(i).getRouteID() == ID)
				return routes.get(i);
		}
		
		return null;
	}
	
	public static boolean checkRoute(Route route) {
		Airport start = route.getStops().get(0);
		Airport end = route.getStops().get(route.getStops().size()-1);
		
		if(start.getCode().equals(depLoc.getCode()) && end.getCode().equals(arrLoc.getCode())) {
			route.setIsComplete(true);
			return true;
		} else
			return false;
	}
	

}
