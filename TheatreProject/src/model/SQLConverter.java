package model;

import java.sql.ResultSet;
import java.util.HashMap;

import util.DBConnector;

public class SQLConverter {

	/*
	 * Constructor
	 */
	public SQLConverter() {
		
	}

	/*
	 * Search whether the name exists in the database.
	 * 
	 * Creates a db connector, then runs the SQL query that 
	 * we want to run, then gets the result and sees whether 
	 * the programme we searched for exists. If it does then
	 * it returns true, if it does not exist in the results 
	 * returned then it returns false. 
	 */
	public boolean searchName(String name) {
		return true;
		
		String query = "INSERT SQL CODE HERE";

		DBConnector db = new DBConnector(); 
		db.connect();
		
		for (int i = 0; i < query.size(); i++) {
			String line = query.get(i);
			ResultSet results = db.runQuery(line);
			if (results.contains(name)) {
				return true;
			} else { 
				return false;
			}
			
		}

		db.close();
	}

	/*
	 * Return show information
	 */
	public String getName(String name) {
		return "Name";
	}

	/*
	 * Search for a specific performance
	 */
	public int searchPerformance(String date, String time) {
		return 5;
	}

	/*
	 * Browse all shows
	 */
	public void browseAllShows() {

	}

	/*
	 * Finalise order and update SQL database to create a new booking.
	 */
	public void finaliseOrder(String name, String address, int creditCard) {

	}

	/*
	 * Get finalised order details by retrieving the data from the SQL database.
	 */
	public void getOrderDetails() {

	}

	public String getPerformanceInformation(int perfID) {
		return "Name";
	}
	
	/*
	 * Method to get the next available seat number for a specific performance ID.
	 */
	public int getNextSeat() {
		return 1;
	}
	
	/*
	 * Method to get booking reference given all other information.
	 */
	public int getBookingReference() {
		return 5;
	}
	
}
