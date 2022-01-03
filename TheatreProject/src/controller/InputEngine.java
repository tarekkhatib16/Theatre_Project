package controller;

import util.InputReader;
import model.Transaction;
import model.SQLConverter;

public class InputEngine {
	
	/*
	 * Instance variables.
	 */
	private InputReader reader;
	private Transaction order;
	private SQLConverter sql;
	
	/*
	 * Constructor where an instance of the InputReader class is
	 * created amongst other things. 
	 */
	public InputEngine() {
		reader = new InputReader();
		order = new Transaction();
		sql = new SQLConverter();
	}
	
	/*
	 * Method to add tickets to the transaction basket. 
	 */
	public void addTickets() {
		
	}
	
	/*
	 * Method to get finalised order. 
	 */
	public void getOrder() {
		
	}
	
	/*
	 * Method to finalise order in basket (i.e. check-out).
	 */
	public void finaliseOrder() {
		
	}
	
	/*
	 * Method to get contents of the basket.
	 */
	public void getBasket() {
		
	}
	
}
