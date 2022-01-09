package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.CallableStatement;
import java.util.List;

import util.DBConnector;

public class SQLConverter {

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
	 * Method to get performance information using performance ID
	 */
	public String getPerformance(int PerfID) {
		db.connect();
		
		mySQLquery = "CALL GetPerfInfo("+String.valueOf(PerfID)+")";
		rs = db.runQuery(mySQLquery);
		
		String retVal = resultToString(db.compileResults(rs));
		db.close();
		
		return retVal;
	}
	
	/*
	 * Method to get Event Name information using performance ID
	 */
	public String getEventFromPerformance(int PerfID) {
		db.connect();
		
		mySQLquery = "CALL GetEventIDFromPerf("+String.valueOf(PerfID)+")";
		rs = db.runQuery(mySQLquery);
		
		String retVal = resultToString(db.compileResults(rs));
		db.close();
		
		return retVal;
	}

	/*
	 * Search for a specific performance by UNIQUE identifier of Date/time
	 */
	public int searchPerformance(String date, String time) {
		
		db.connect();
		String dateTime = date + " " + time;
		
		mySQLquery = "CALL GetPerformanceID_DT('"+dateTime+"')";
		rs = db.runQuery(mySQLquery);
		
		int perfID = Integer.parseInt(resultToString(db.compileResults(rs)));
		db.close();
		
		return perfID;
	}
	
	/*
	 * Search performance by date
	 */
	public String searchPerformanceByDate(String date) {
		db.connect(); // open connection
		
		mySQLquery = "CALL GetShowsDate('" + date + "')"; // Date must be in YYYY-MM-DD with hyphens
	
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
	 * Finalise order and update SQL database to create a new purchaser.
	 */
	public String finaliseOrderPurchaser(
			String name, 
			String DOB,
			String addressHouseNumber,
			String addressStreet, 
			String addressCity, 
			String addressCounty, 
			String addressPostcode, 
			String creditCard) {
		db.connect(); // open connection
		
		mySQLquery = "CALL InsertPurchaser('" 
						+ name + "','" 
						+ DOB + "','"
						+ addressHouseNumber + "','"
						+ addressStreet + "','"
						+ addressCity + "','"
						+ addressCounty + "','"
						+ addressPostcode + "','"
						+ creditCard + "', @retBool)";
		rs = db.runQuery(mySQLquery);
		
		String retVal = resultToString(db.compileResults(rs));
		
		db.close(); //close connection
		
		return retVal;
	}
	
	/*
	 * Finalise order and update SQL database to create a new booking.
	 */
	public void finaliseOrderBooking(
			String perfID,
			String purID,
			String conc,
			String seatNumber,
			String stalls) {
		db.connect();
		
		mySQLquery = "CALL SetBooking("
						+ perfID + ","
						+ purID + ","
						+ conc + ","
						+ stalls + ","
						+ seatNumber + ")";
		
		db.connect();
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
	
	public String GetEventInfo(int evtID) {
		db.connect(); // open connection
		
		String query = "Call GetEventInfo("+ String.valueOf(evtID) + ")";
		
		rs = db.runQuery(query);
		String retVal = resultToString(db.compileResults(rs));
		db.close();		
		
		return retVal;
	}
	
	public String getPerformances(int eventID) {
		db.connect(); // open connection
		
		String query = "Call GetPerformances("+ String.valueOf(eventID) + ")";
		
		rs = db.runQuery(query);
		String retVal = resultToString(db.compileResults(rs));
		db.close();		
		
		return retVal;
	}
	
	/*
	 * Method to get the next available seat number for a specific performance ID.
	 */
	public int getNextSeat() {
		//for future development
		//db.connect(); // open connection
		//db.runQuery("");
		//db.close(); //close connection
		
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
				if (Data.get(i).get(j) != null) {
					retVal += Data.get(i).get(j) + " ";
				}
			}
			retVal+="\n"; //newline
		}
		
		if (retVal == "") {
			retVal = "nothing found, try again";
		}
		
		return retVal;
	}
	
}
