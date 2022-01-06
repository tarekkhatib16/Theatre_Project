package model;

import java.sql.ResultSet;
import java.util.HashMap;

import util.DBConnector;

public class SQLConverter {

	private Boolean finished = false;
	DBConnector db = new DBConnector();
	
	/*
	 * Constructor
	 */
	public SQLConverter() {
		
		while (!finished) {
			db.connect();
		}
		
		db.close();
	}
	
	/*
	 * Changes finished into true to end the programme.
	 */
	public void changeFinished(Boolean x) {
		finished = x;
		
		db.runQuery("");
		
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
		db.runQuery("");
		
		return true;
	}

	/*
	 * Return show information
	 */
	public String getName(String name) {
		db.runQuery("");
		
		return "Name";
	}

	/*
	 * Search for a specific performance
	 */
	public int searchPerformance(String date, String time) {
		db.runQuery("");
		
		return 5;
	}

	/*
	 * Browse all shows
	 */
	public void browseAllShows() {
		
		db.runQuery("");

	}

	/*
	 * Finalise order and update SQL database to create a new booking.
	 */
	public void finaliseOrder(String name, String address, int creditCard) {

		db.runQuery("");
		
	}

	/*
	 * Get finalised order details by retrieving the data from the SQL database.
	 */
	public void getOrderDetails() {

		db.runQuery("");
		
	}

	public String getPerformanceInformation(int perfID) {
		db.runQuery("");
		return "Name";
	}
	
	/*
	 * Method to get the next available seat number for a specific performance ID.
	 */
	public int getNextSeat() {
		db.runQuery("");
		return 1;
	}
	
	/*
	 * Method to get booking reference given all other information.
	 */
	public int getBookingReference() {
		db.runQuery("");
		return 5;
	}
	
}
