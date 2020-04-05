package main;

import java.util.ArrayList;
import java.lang.StringBuilder;

//Need to get time zone for airport in UTC + DST form 
public class Airport {
	private String code;
	private String name;
	private String country;
	private String city;
	
	private ArrayList<Flight> inFlights;
	private ArrayList<Flight> outFlights;
	
	//Constructors
	public Airport(String code) {
		this.code = code;
		this.name = null;
		this.country = null;
		this.city = null;
		
		this.inFlights = new ArrayList<Flight>();
		this.outFlights = new ArrayList<Flight>();
	}
	
	public Airport(String code, String name, String country, String city) {
		this.code = code;
		this.name = name;
		this.country = country;
		this.city = city;
		
		this.inFlights = new ArrayList<Flight>();
		this.outFlights = new ArrayList<Flight>();
	}
	//
	
	
	//Setters
	public void setCode(String code) {this.code = code;}
	public void setName(String name) {this.name = name;}
	public void setCountry(String country) {this.country = country;}
	public void setCity(String city) {this.city = city;}
	//
	
	
	//Getters
	public String getCode() {return this.code;}
	public String getName() {return this.name;}
	public String getCountr() {return this.country;}
	public String getCity() {return this.city;}
	
	public ArrayList<Flight> getInFlights() {return this.inFlights;}
	public ArrayList<Flight> getOutFLights() {return this.outFlights;}
	//
	
	
	//Methods
	public boolean addInFlight(Flight flight) {
		this.inFlights.add(flight);
		return true;
	}
	
	public boolean removeInFlight(Flight flight) {
		if(this.inFlights.contains(flight)) {
			this.inFlights.remove(flight);
			return true;
		} else {
			return false;
		}
	}
	
	public boolean addOutFlight(Flight flight) {
		this.outFlights.add(flight);
		return true;
	}
	
	public boolean removeOutFlight(Flight flight) {
		if(this.outFlights.contains(flight)) {
			this.outFlights.remove(flight);
			return true;
		} else {
			return false;
		}
	}
	
	public void loadFlightData(ArrayList<Flight> flights) {
		Flight tempFlight = null;
		
		for(int i=0; i<flights.size(); i++) {
			tempFlight = flights.get(i);
			
			if(tempFlight.getArrLocStr().equals(this.code)) {
				this.inFlights.add(tempFlight);
				System.out.printf("Flight \"%s\" added to %s inbound flight list.\n", tempFlight.getFlightNum(), this.name);
			}
			
			if(tempFlight.getDepLocStr().equals(this.code)) {
				this.outFlights.add(tempFlight);
				System.out.printf("Flight \"%s\" added to %s outbound flight list.\n", tempFlight.getFlightNum(), this.name);
			}
		}
		
	}
	
	public String toString() {
		StringBuilder str = new StringBuilder("[");
		str.append(this.code);
		str.append(", ");
		str.append(this.name);
		str.append(", ");
		str.append(this.city);
		str.append(", ");
		str.append(this.country);
		str.append("]");
		
		return str.toString();
	}
	//
}
