package main;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;

public class FlightPlan {
	private double totalCost;
	private int totalFlightTime;
	private int totalWaitTime;
	private int depMonth;
	private int depDay;
	
	private Airport depLoc;
	private Airport arrLoc;
	
	private ArrayList<Flight> flights; 
	
	//Constructors
	public FlightPlan(Airport depLoc, Airport arrLoc) {
		this.totalCost = 0;
		this.totalFlightTime = 0;
		this.totalWaitTime = 0;
		
		this.depLoc = depLoc;
		this.arrLoc = arrLoc;
		
		this.depMonth = 0;
		this.depDay = 0;
		
		this.flights = new ArrayList<Flight>();
	}
	
	public FlightPlan(Airport depLoc, Airport arrLoc, int depMonth) {
		this.totalCost = 0;
		this.totalFlightTime = 0;
		this.totalWaitTime = 0;
		
		this.depLoc = depLoc;
		this.arrLoc = arrLoc;
		
		this.depMonth = depMonth;
		this.depDay = 0;
		
		this.flights = new ArrayList<Flight>();
	}
	
	public FlightPlan(Airport depLoc, Airport arrLoc, int depMonth, int depDay) {
		this.totalCost = 0;
		this.totalFlightTime = 0;
		this.totalWaitTime = 0;
		
		this.depLoc = depLoc;
		this.arrLoc = arrLoc;
		
		this.depMonth = depMonth;
		this.depDay = depDay;
		
		this.flights = new ArrayList<Flight>();
	}
	//
	
	
	//Setters
	public void setTotalCost(double cost) {this.totalCost = cost;}
	public void setTotalFlightTime(int time) {this.totalFlightTime = time;}
	public void setTotalWaitTime(int time) {this.totalWaitTime = time;}
	public void setDepMonth(int month) {this.depMonth = month;}
	public void setDepDay(int day) {this.depDay = day;}
	public void setDepLoc(Airport airport) {this.depLoc = airport;}
	public void setArrLoc(Airport airport) {this.arrLoc = airport;}
	//
	
	
	//Getters
	public double getTotalCost() {return this.totalCost;}
	public int getTotalFlightTime() {return this.totalFlightTime;}
	public int getTotalWaitTime() {return this.totalWaitTime;}
	public int getDepMonth() {return this.depMonth;}
	public int getDepDay() {return this.depDay;}
	public Airport getDepLoc() {return this.depLoc;}
	public Airport getArrLoc() {return this.arrLoc;}
	public ArrayList<Flight> getFlights() {return this.flights;}
	//
	

	//Methods
	public void calculateFlightPlan() {
		double cost = 0;
		int flightTime = 0;
		int waitTime = 0;
		
		Calendar prevCal = null;
		Calendar currCal = null;
		
		int tempYear = 0;
		int tempMonth = 0;
		int tempDay = 0;
		int tempHour = 0;
		int tempMinute = 0;
		
		Flight temp = null;
		Iterator<Flight> itr = this.flights.iterator();
		
		while(itr.hasNext()) {
			temp  = itr.next();
			
			cost += temp.getFlightCost();
			flightTime += temp.getFlightDur();
			
			if(prevCal == null) {
				prevCal = temp.getDepDate();
				prevCal.add(Calendar.MINUTE, temp.getFlightDur());
			} else {
				currCal = temp.getDepDate();
				
				tempYear = prevCal.get(Calendar.YEAR);
				tempMonth = prevCal.get(Calendar.MONTH);
				tempDay = prevCal.get(Calendar.DATE);
				tempHour = prevCal.get(Calendar.HOUR);
				tempMinute = prevCal.get(Calendar.MINUTE);
				
				currCal.add(Calendar.YEAR, tempYear * -1);
				currCal.add(Calendar.MONTH, tempMonth * -1);
				currCal.add(Calendar.DATE, tempDay * -1);
				currCal.add(Calendar.HOUR, tempHour * -1);
				currCal.add(Calendar.MINUTE, tempMinute * -1);
				
				
			}
			
			
		}
	}
	//
	
}
