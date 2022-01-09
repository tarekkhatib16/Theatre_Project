/*
 * 20220108: SM: Added compileResults Function, Returns List<List<String> from results set;
 */

package util;
import java.sql.Connection;
import java.sql.CallableStatement;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBConnector {
	private String databaseUrl = "jdbc:mysql://localhost:3306/finalprojecttheatre";
	private String user = "customer";
	private String password = "password";
	private Connection conn;

	public DBConnector() {
		conn = null;
	}

	public void connect() {
		try {
			conn = DriverManager.getConnection(databaseUrl, user, password);
		} catch (SQLException e) {
			System.out.println("Connection failed.");
			e.printStackTrace();
			return;
		}

		if (conn != null) {
			//System.out.println("Connection established.");
		} else {
			System.out.println("Connection null still.");
		}
	}

	/*
	 * 4. Prepare a query statement to run 5. Execute query
	 */

	public ResultSet runQuery(String sql) {
		PreparedStatement pst = null;
		try {
			pst = conn.prepareStatement(sql,
					ResultSet.TYPE_SCROLL_SENSITIVE, // allows us to move forward and back in the ResultSet
                    ResultSet.CONCUR_UPDATABLE);
			pst.execute();
			ResultSet results = pst.getResultSet();
			if (results != null) {
				if (results.last()) {
					results.beforeFirst(); // not rs.first() because the rs.next() below will move on, missing the first
											// element
				}
			}
			return results;
		} catch (SQLException e) {
			System.out.println(sql + "\n failed to run.");
			e.printStackTrace();
			return null;
		}

	}
	
	// 6. Process Results

	public void printResults(ResultSet rs) {
		try {
			if (rs != null) {
				// while there is another row
				
				ResultSetMetaData rsmd = rs.getMetaData();
				int columnsNumber = rsmd.getColumnCount();
				
				// header
			    for(int i = 1; i <= columnsNumber; i++) {
			        System.out.print(rsmd.getColumnLabel(i) + " ");
			    }
			    System.out.println();

				// data
			    while (rs.next()) {
			        for(int i = 1; i <= columnsNumber; i++) {
			            System.out.print(rs.getString(i) + " ");
			        }
			        System.out.println();
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * Compile results into a 2d arraylist
	 */
	public List<List<String>> compileResults(ResultSet rs) {
		List<List<String>> Data = new ArrayList<List<String>>();
		
		try {
			if (rs != null) {
				// while there is another row
				
				ResultSetMetaData rsmd = rs.getMetaData();
				int columnsNumber = rsmd.getColumnCount();
				rs.last(); //move to last row to get number of rows
				int rowsNumber = rs.getRow()+1; // set row number
				rs.beforeFirst(); // move back to before first row to process again
				
				//instantiate  array
				for (int i = 0; i < rowsNumber; i++) {
					Data.add(new ArrayList<String>());
				}
				
				//fill array, first row[0] is header
				//header
				for(int j = 1; j <= columnsNumber; j++) {
				    Data.get(0).add(rsmd.getColumnLabel(j));
				}

				// data
			    rowsNumber = 0; // Reset row number to 0 (as incremented before first pass)
			    while (rs.next()) {
			    	rowsNumber++; //increment row number for next list element
			    	for(int i = 1; i <= columnsNumber; i++) {
			            Data.get(rowsNumber).add(rs.getString(i));
			        }
				}
			    //System.out.println(Data); // test
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Data;
	}

	public void close() {
		try {
			conn.close();
			//System.out.println("Connection closed.");
		} catch (SQLException e) {
			System.out.println("Connection not closed.");
			e.printStackTrace();
		}
	}


}

