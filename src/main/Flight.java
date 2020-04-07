package main;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

public class Flight {
	private String flightNum;
	private String departureLocStr;
	private String arrivalLocStr;
	private Airport departureLoc;
	private Airport arrivalLoc;
	private double flightCost;
	private int flightDur;		//In minutes
	private boolean isIntl;
	
	private Calendar departureDate;
	
	//Constructors
	public Flight(String flightNum, String depLoc, String arrLoc, double flightCost, int flightDur) {
		this.flightNum = flightNum;
		this.departureLocStr = depLoc;
		this.arrivalLocStr = arrLoc;
		this.flightCost = flightCost;
		this.flightDur = flightDur;
		
		this.departureLoc = null;
		this.arrivalLoc = null;
		
		this.isIntl = checkIsIntl();
		
		this.departureDate = Calendar.getInstance();
	}
	//
	
	//Setters
	public void setFlightNum(String flightNum) {this.flightNum = flightNum;}
	public void setDepLocStr(String code) {this.departureLocStr = code;}
	public void setArrLocStr(String code) {this.arrivalLocStr = code;}
	public void setDepLoc(Airport airport) {this.departureLoc = airport;}
	public void setArrLoc(Airport airport) {this.arrivalLoc = airport;}
	public void setFlightCost(double cost) {this.flightCost = cost;}
	public void setFlightDur(int dur) {this.flightDur = dur;}
	public void setIsIntl(boolean isIntl) {this.isIntl = isIntl;}
	
	public void setDepDate(int month, int day, int hour, int minute) {
		this.departureDate.set(Calendar.YEAR, 2020);
		this.departureDate.set(Calendar.MONTH, month);
		this.departureDate.set(Calendar.DATE, day);
		this.departureDate.set(Calendar.HOUR, hour);
		this.departureDate.set(Calendar.MINUTE, minute);
		this.departureDate.set(Calendar.SECOND, 0);
		this.departureDate.set(Calendar.MILLISECOND, 0);
	}
	//
	
	
	//Getters
	public String getFlightNum() {return this.flightNum;}
	public String getDepLocStr() {return this.departureLocStr;}
	public String getArrLocStr() {return this.arrivalLocStr;}
	public Airport getDepLoc() {return this.departureLoc;}
	public Airport getArrLoc() {return this.arrivalLoc;}
	public double getFlightCost() {return this.flightCost;}
	public int getFlightDur() {return this.flightDur;}
	public boolean getIsIntl() {return this.isIntl;}
	public Calendar getDepDate() {return this.departureDate;}
	//
	
	
	//Methods
	public void setDepArrLoc(ArrayList<Airport> airports) {
		Iterator<Airport> itr = airports.iterator();
		Airport temp = null;
		
		while(itr.hasNext() && (this.departureLoc != null && this.arrivalLoc != null)) {
			temp = itr.next();
			
			if(temp.getCode().equals(this.departureLocStr))
				this.departureLoc = temp;
			else if(temp.getCode().equals(this.arrivalLocStr))
				this.arrivalLoc = temp;
		}
		
	}
	
	public String toString() {
		StringBuilder str = new StringBuilder("[");
		str.append(this.flightNum);
		str.append(", ");
		str.append(this.departureLocStr);
		str.append(", ");
		str.append(this.arrivalLocStr);
		str.append(", ");
		str.append(this.flightCost);
		str.append(", ");
		str.append(this.flightDur);
		str.append("]");
		
		return str.toString();
	}
	
	public boolean checkIsIntl() {
		if(this.departureLoc.getCountry().equalsIgnoreCase(this.arrivalLoc.getCountry()))
			return false;
		else
			return true;
	}
	
	//Ignore
	public void delayFlight(double delayTime) {
		
	}
	//
}
