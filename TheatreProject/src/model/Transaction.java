package model;

import java.util.*;

public class Transaction {
	
	/*
	 * Instance variables including a 3D ArrayList for 
	 * storing tickets.
	 */
	private ArrayList<ArrayList<Integer>> booking;

	/*
	 * Constructor that initialises each element of 
	 * 3D ArrayList 'tickets'.
	 */
	public Transaction() {
		booking = new ArrayList< ArrayList<Integer> >();
	}
	
	/*
	 * Method to return 3D ArrayList tickets.
	 */
	public ArrayList<ArrayList<Integer>> getTickets() {
		return booking;
	}
	
	/*
	 * Method to add ticket information to 3D ArrayList
	 * 'tickets'.
	 */
	public void addTickets(int i, int x, int y) {
		booking.get(i).add(0, x);
		booking.get(i).add(1, y);
	}
	
}
