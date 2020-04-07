package main;

import java.util.ArrayList;

public class Route {
	private Airport depLoc;
	private ArrayList<Airport> stops;
	
	public Route(Airport depLoc) {
		this.depLoc = depLoc;
		this.stops = new ArrayList<Airport>();
		
		this.stops.add(this.depLoc);
	}
	
	//Setters
	public void setDepLoc(Airport depLoc) {this.depLoc = depLoc;}
	//
	
	
	//Getters
	public Airport getDepLoc() {return this.depLoc;}
	public ArrayList<Airport> getStops() {return this.stops;}
	//
	
	
	//Methods
	public void addStop(Airport stop) {this.stops.add(stop);}
}
