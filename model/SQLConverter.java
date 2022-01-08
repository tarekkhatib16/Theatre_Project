package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.CallableStatement;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

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
		//db.connect(); // Commented out, unreliable results returned if connection kept open
		//will open at beginning of function then close
		/*while (!finished) { //infinite loop
			db.connect();
		}
		
		db.close();*/
	}
	
	/*
	 * Changes finished into true to end the programme, close connection.
	 */
	public void changeFinished(Boolean x) {
		
		if (x) {
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
	public boolean searchName(String name) {//still needed?
		db.connect(); // open connection
		
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
		db.close();
		
		return b;
	}

	/*
	 * Return show information based on search mask
	 */
	public String getName(String name) {
		db.connect(); // open connection
		
		mySQLquery = "CALL GetShowsSearch('"+name+"')";
		rs = db.runQuery(mySQLquery);
		
		String retVal = resultToString(db.compileResults(rs));
		db.close();		
		
		return retVal;
	}

	/*
	 * Search for a specific performance by UNIQUE identifier of Date/time
	 */
	public int searchPerformance(String date, String time) {
		db.connect(); // open connection
		
		rs = db.runQuery("CALL");
		db.close(); //close connection
		
		return 5;
	}
	
	/*
	 * Search performance by date
	 */
	public String searchPerformanceByDate(String date) {
		db.connect(); // open connection
		
		mySQLquery = " CALL GetShowsDate('" + date + "')"; // Date must be in YYYY-MM-DD with hyphens
	
		rs = db.runQuery(mySQLquery);
		String retVal = resultToString(db.compileResults(rs));
		db.close();		
		
		return retVal;
	}

	/*
	 * Browse all shows
	 */
	public String browseAllShows() {
		db.connect(); // open connection
		
		mySQLquery = "CALL GetShows()";
		rs= db.runQuery(mySQLquery);
		String retVal = resultToString(db.compileResults(rs));
		db.close();		
		
		return retVal;
	}

	/*
	 * Finalise order and update SQL database to create a new booking.
	 */
	public void finaliseOrder(String name, String address, int creditCard) {
		db.connect(); // open connection
		
		mySQLquery = "CALL";
		db.runQuery(mySQLquery);
		db.close(); //close connection
		
	}

	/*
	 * Get finalised order details by retrieving the data from the SQL database.
	 */
	public void getOrderDetails() {
		db.connect(); // open connection
		
		rs = db.runQuery("");
		db.close(); //close connection
		
		printResults();
		
	}

	public String getPerformanceInformation(int perfID) {
		db.connect(); // open connection
		
		String query = "Call GetPerformanceInfo("+ String.valueOf(perfID) + ")";
		
		rs = db.runQuery(query);
		String retVal = resultToString(db.compileResults(rs));
		db.close();		
		
		return retVal;
	}
	
	/*
	 * Method to get the next available seat number for a specific performance ID.
	 */
	public int getNextSeat() {
		db.connect(); // open connection
		
		db.runQuery("");
		
		db.close(); //close connection
		
		return 1;
	}
	
	/*
	 * Method to get booking reference given all other information.
	 */
	public int getBookingReference() {
		db.connect(); // open connection
		
		db.runQuery("");
		
		db.close(); //close connection
		return 5;
	}
	
	private void printResults() {// print the results set
		db.printResults(rs);
	}
	
	private String resultToString(List<List<String>> Data) {//convert the ListList to string to display
		String retVal = "";
		
		for (int i = 1; i < Data.size(); i++) { // ignore headers, start at index 1
			for (int j = 0; j < Data.get(i).size(); j++) {
				retVal += Data.get(i).get(j) + " ";
			}
			retVal+="\n"; //newline
		}
		
		if (retVal == "") {
			retVal = "nothing found, try again";
		}
		
		return retVal;
	}
	
}
