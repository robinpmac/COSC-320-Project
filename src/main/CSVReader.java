package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public abstract class CSVReader {
	private static ArrayList<Airport> airports;
	private static ArrayList<Flight> flights;
	
	//Load Airports
	public static ArrayList<Airport> loadAirports() {
		final int fileSize = 7700;
		
		String csvFile = "CanadaAirportList.csv";
		BufferedReader br = null;
		String line = "";
		String csvSplitBy = ",";
		
		airports = new ArrayList<Airport>(fileSize);
		
		try {
			
			br = new BufferedReader(new FileReader(new File(csvFile)));
			br.readLine();	//Skips the headers
			
			while((line = br.readLine()) != null) {
				
				String[] info = line.split(csvSplitBy);
				/*
				 * info[0] = Airport IATA Code
				 * info[1] = Airport Name
				 * info[2] = Airport Country
				 * info[3] = Airport City
				 * info[4] = Local TimeZone
				 */
				
				airports.add(new Airport(info[0], info[1], info[2], info[3]));
			}
			
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			
			if(br != null) {
				try {
					br.close();
					
				} catch(IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return airports;
	}
	
	
	//Load Flights
	public static ArrayList<Flight> loadFlights(){
		//final int fileSize = 7700;
		
		String csvFile = "DummyFlightList.csv";
		BufferedReader br = null;
		String line = "";
		String csvSplitBy = ",";
		
		flights = new ArrayList<Flight>();
		
		try {
			
			br = new BufferedReader(new FileReader(new File(csvFile)));
			br.readLine();	//Skips the headers
			
			while((line = br.readLine()) != null) {
				
				String[] info = line.split(csvSplitBy);
				/*
				 * info[0] = Flight Number
				 * info[1] = Departure Location IATA Code
				 * info[2] = Arrival Location IATA Code
				 * info[3] = Flight Cost
				 * info[4] = Flight Duration
				 * info[5] = Layover Duration
				 */
				
				double cost = Double.valueOf(info[3]);
				int flightDur = Integer.valueOf(info[4]);
				int Layover = Integer.valueOf(info[5]);
				
				flights.add(new Flight(info[0], info[1], info[2], cost, flightDur,Layover));
			}
			
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			
			if(br != null) {
				try {
					br.close();
					
				} catch(IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		return flights;
	}
	
	/*
    public static void main(String[] args) {
        String csvFile = "MasterAirportList.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ",";
        ArrayList<Airport> airports = new ArrayList<Airport>(7700);

        try {

            br = new BufferedReader(new FileReader(new File(csvFile)));
            while ((line = br.readLine()) != null) {

                // use comma as separator
                String[] info = line.split(cvsSplitBy);

                airports.add(new Airport(info[0], info[1], info[2], info[3]));

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        
        System.out.println(airports.get(6012).toString());

    }
    */

}
