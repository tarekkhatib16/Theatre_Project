package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

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
			System.out.println("Connection established.");
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

	public void close() {
		try {
			conn.close();
			System.out.println("Connection closed.");
		} catch (SQLException e) {
			System.out.println("Connection not closed.");
			e.printStackTrace();
		}
	}


}

