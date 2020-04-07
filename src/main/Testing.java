package main;

import java.util.ArrayList;

public class Testing {

	public static void main(String[] args) {
		long time;
		ArrayList<Airport> airports = CSVReader.loadAirports();
		ArrayList<Flight> flights = CSVReader.loadFlights();

		for(int i=0; i<10; i++) {
			System.out.println(airports.get(i).toString());
		}
		
		System.out.println();
		
		for(int i=0; i<flights.size(); i++) {
			System.out.println(flights.get(i).toString());
		}
		
		System.out.println();
		
		//Load all flights into respective airports
		time = System.currentTimeMillis();
		for(int i=0; i<airports.size(); i++) {
			airports.get(i).loadFlightData(flights);
		}
		time = System.currentTimeMillis() - time;
		
		System.out.printf("Load time = %.4f Seconds\n", ((double) time) / 1000);
		//Github Test
		
	}

}
