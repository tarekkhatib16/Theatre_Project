package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.CallableStatement;
import java.util.HashMap;

import util.DBConnector;

public class SQLConverter {

	private Boolean finished = false;
	DBConnector db = new DBConnector();
	String mySQLquery; // string to hold query
	ResultSet rs; // results set
	CallableStatement cs;
	
	/*
	 * Constructor
	 */
	public SQLConverter() {
		db.connect();
		/*while (!finished) { //infinite loop
			db.connect();
		}
		
		db.close();*/
	}
	
	/*
	 * Changes finished into true to end the programme.
	 */
	public void changeFinished(Boolean x) {
		finished = x;
		
		if (finished) {
			db.close();
		}
		
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
	public boolean searchName(String name) { //BUG. after running doesn't update rs
		mySQLquery = "CALL GetShowsSearchBool('" + name + "', @exist)";
		db.runQuery(mySQLquery);
		rs = db.runQuery("SELECT @exist");
		boolean b = false;
		try {
			if(rs != null) {
				rs.last();
			if (rs.getBoolean("@exist") == true) {
				b = true;
			}}
		} catch (SQLException e) {
		}

		return b;
	}

	/*
	 * Return show information
	 */
	public String getName(String name) {
		mySQLquery = "CALL GetShowsSearch('"+name+"')";
		
		rs = db.runQuery(mySQLquery);
		printResults();
		
		return "Name";
	}

	/*
	 * Search for a specific performance
	 */
	public int searchPerformance(String date, String time) {
		mySQLquery = " CALL GetShowsDate('" + date + "')"; // Date must be in YYYY-MM-DD with hyphens
		
		rs = db.runQuery(mySQLquery);
		printResults();
		
		return 5;
	}

	/*
	 * Browse all shows
	 */
	public void browseAllShows() {
		mySQLquery = "CALL GetShows()";
		rs= db.runQuery(mySQLquery);
		db.printResults(rs);
	}

	/*
	 * Finalise order and update SQL database to create a new booking.
	 */
	public void finaliseOrder(String name, String address, int creditCard) {
		mySQLquery = "CALL";
		db.runQuery(mySQLquery);
		
	}

	/*
	 * Get finalised order details by retrieving the data from the SQL database.
	 */
	public void getOrderDetails() {

		rs = db.runQuery("");
		printResults();
		
	}

	public String getPerformanceInformation(int perfID) {
		String query = "Call GetPerformanceInfo("+ String.valueOf(perfID) + ")";
		
		rs = db.runQuery(query);
		printResults();
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
	
	private void printResults() {// print the results set
		db.printResults(rs);
	}
	
}
