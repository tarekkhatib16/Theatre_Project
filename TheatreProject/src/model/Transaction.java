package model;

import java.util.*;

public class Transaction {

	/*
	 * Instance variables including a 3D ArrayList for storing tickets.
	 */
	private ArrayList<ArrayList<Integer>> booking;

	/*
	 * Constructor that initialises each element of 3D ArrayList 'tickets'.
	 */
	public Transaction() {
		booking = new ArrayList<ArrayList<Integer>>();
		booking.add(new ArrayList<Integer>());
	}

	/*
	 * Method to return 3D ArrayList tickets.
	 */
	public ArrayList<ArrayList<Integer>> getTickets() {
		return booking;
	}

	/*
	 * Method to add ticket information to 3D ArrayList 'tickets'.
	 */
	public void addTickets(int x, int y, int z) {
		booking.add(new ArrayList<Integer>());
		booking.get(booking.size()-1).add(0, x);
		booking.get(booking.size()-1).add(1, y);
		booking.get(booking.size()-1).add(2, z);
	}

}
