package main;

import java.util.ArrayList;

public class Route {
	private static int routeID = -1;
	private int parentID;
	public boolean isComplete;

	private ArrayList<Airport> stops;
	private ArrayList<Flight> flights;
	
	//For first Route
	public Route(Airport loc) {
		routeID++;
		this.parentID = 0;
		this.isComplete = false;
		this.stops = new ArrayList<Airport>();
		this.flights = new ArrayList<Flight>();
		
		this.stops.add(loc);
	}
	
	//For all subsequent Routes
	public Route(Airport loc, Route parent) {
		routeID++;
		this.parentID = parent.getRouteID();
		this.stops = new ArrayList<Airport>();
		this.flights = new ArrayList<Flight>();
		
		for(int i=0; i<parent.getStops().size(); i++) {
			this.stops.add(parent.getStops().get(i));
		}
		
		for(int i=0; i<parent.getFlights().size(); i++) {
			this.flights.add(parent.getFlights().get(i));
		}
		

	}
	
	//Setters
	public void setIsComplete(boolean val) {this.isComplete = val;}
	//
	
	
	//Getters
	public int getRouteID() {return routeID;}
	public int getParentID() {return this.parentID;}
	public ArrayList<Airport> getStops() {return this.stops;}
	public ArrayList<Flight> getFlights() {return this.flights;}
	//
	
	
	//Methods
	public void addStop(Airport stop) {this.stops.add(stop);}
	public void addFlight(Flight flight) {this.flights.add(flight);}
	
	public FlightPlan createFlightPlan() {
		Airport depLoc = this.stops.get(0);
		Airport arrLoc = this.stops.get(this.stops.size()-1);
		
		
		FlightPlan plan = new FlightPlan(depLoc, arrLoc);
		for(int i=0; i<this.stops.size(); i++) {
			plan.addFlight(this.flights.get(i));
		}
		
		return plan;
	}
}
