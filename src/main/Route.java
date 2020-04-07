package main;

import java.util.ArrayList;

public class Route {
	private static int routeID = -1;
	private int parentID;

	private ArrayList<Airport> stops;
	
	//For first Route
	public Route(Airport loc) {
		routeID++;
		this.parentID = 0;
		this.stops = new ArrayList<Airport>();
		
		this.stops.add(loc);
	}
	
	//For all subsequent Routes
	public Route(Airport loc, Route parent) {
		routeID++;
		this.parentID = parent.getParentID();
		this.stops = new ArrayList<Airport>();
		
		for(int i=0; i<parent.getStops().size(); i++) {
			this.stops.add(parent.getStops().get(i));
		}
	}
	
	//Setters

	//
	
	
	//Getters
	public int getRouteID() {return routeID;}
	public int getParentID() {return this.parentID;}
	public ArrayList<Airport> getStops() {return this.stops;}
	//
	
	
	//Methods
	public void addStop(Airport stop) {this.stops.add(stop);}
}
