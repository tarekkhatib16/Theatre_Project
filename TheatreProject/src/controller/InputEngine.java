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
	 * Constructor where an instance of the InputReader class is created amongst
	 * other things.
	 */
	public InputEngine() {
		reader = new InputReader();
		order = new Transaction();
		sql = new SQLConverter();

		this.start();
	}
	
	/*
	 * Start the programme.
	 */
	private void start() {

		boolean finished = false;

		printWelcome();

		while (!finished) {
			String input = reader.getInput();

			if (input.contains("show all shows".toLowerCase())) {
				sql.browseAllShows();
			} else {
				sql.searchName(input);
			}
		}

	}
	
	/*
	 * Welcome message when the application opens. 
	 */
	private void printWelcome() {
		System.out.println("Welcome to the Theatre Royal website.");
		System.out.println("If you would like to see the full catalogue of shows available");
		System.out.println("type: show all shows");
		System.out.println("Alternatively, you are able to search for a specific performance");
		System.out.println("by typing in the name of the show");
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
		sql.getOrderDetails();
	}

	/*
	 * Method to finalise order in basket (i.e. check-out).
	 */
	public void finaliseOrder() {
		sql.finaliseOrder();
	}

	/*
	 * Method to get contents of the basket.
	 */
	public void getBasket() {
		System.out.println("Your basket contains the following tickets:");
		System.out.println(sql.getPerformanceInformation(0));
	}

}
