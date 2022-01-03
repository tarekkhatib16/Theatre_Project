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
	 * Start the programme. Print out the welcome message, then get the input from the user. 
	 * If the input was to show all shows, then print details of all available performances. 
	 * If the input was about a specific show, then search the name in the SQL database. 
	 * If the name exists, print out the details of the specific performance, otherwise print
	 * an error message. 
	 */
	private void start() {

		boolean finished = false;

		printWelcome();

		while (!finished) {
			String input = reader.getInput().toLowerCase();

			if (input.contains("show all shows")) {
				sql.browseAllShows();
				
				System.out.println("if you would like to add tickets to this performance to your basket");
				System.out.println("type in: add to basket");
			} else if (input.contains("add to basket")) {
				this.addTickets();
			} else if (input.contains("see basket")) {
				this.getBasket();
			} else if (input.contains("checkout")) {
				this.finaliseOrder();
			} else if (input.contains("see order")) {
				this.getOrder();
			} else {
				if (sql.searchName(input)) {
					sql.getName(input);
				} else {
					System.out.println("There is no performance under that name, please ensure you typed");
					System.out.println("the name of the performance correctly, or search for another show, or");
					System.out.println("type in 'show all shows' to get a catalogue of all shows.");
				}
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
		System.out.println("Finally, if you would like to check an existing order, type in your");
		System.out.println("booking reference.");
	}

	/*
	 * Method to add tickets to the transaction basket.
	 */
	public void addTickets() {
		System.out.println("type in the date (dd/mm/yyyy) of the performance you would");
		System.out.println("like to attend");
		
		String date = reader.getInput();
		
		System.out.println("type in the time (hh:mm) of the performance you would like");
		System.out.println("to attend");
		
		String time = reader.getInput();
		
		System.out.println("How many tickets would you like to buy?");
		
		int numberOfTickets = Integer.parseInt(reader.getInput());
		
		System.out.println("How many of these tickets are concessionary?");
		
		int concessionary = Integer.parseInt(reader.getInput());
		
		int performanceID = sql.searchPerformance(date, time);
		
		for (int i = 0; i < (numberOfTickets-concessionary); i++) {
			if (concessionary > 0) {
				order.addTickets(i, performanceID, 1, true);
				concessionary--;
			} else {
				order.addTickets(i, performanceID, 1, false);
			}
		}
		
		System.out.println("Tickets were added to your basket. If you would like to see what");
		System.out.println("is in your basket type: 'see basket'. Alternatively if you would");
		System.out.println("like to checkout type: 'checkout'.");
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
