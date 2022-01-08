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
	 * The main method: this is what happens when the programme is first run.
	 * This creates an instance of the InputEngine and then triggers the start
	 * method. 
	 */
	public static void main(String[] args) {
    	InputEngine engine = new InputEngine();
    	engine.start();
	}

	/*
	 * Constructor where an instance of the InputReader class is created amongst
	 * other things.
	 */
	public InputEngine() {
		reader = new InputReader();
		order = new Transaction();
		sql = new SQLConverter();
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
				System.out.println(sql.browseAllShows());
				
				System.out.println("if you would like to add tickets to this performance to your basket");
				System.out.println("type in: add to basket");
				System.out.println();
				System.out.print("> ");
				
			} else if (input.contains("add to basket")) {
				this.selectEvent();
			} else if (input.contains("see basket")) {
				this.getBasket();
			} else if (input.contains("checkout")) {
				this.finaliseOrder();
			} else if (input.contains("see order")) {
				this.getOrder();
			} else if (input.contains("quit")) {
				finished = this.quit();
			} else {
				this.allShows(input);
			}
		}
	}
	
	/*
	 * Welcome message when the application opens. 
	 */
	private void printWelcome() {
		System.out.println("Welcome to the Theatre Royal website.");
		System.out.println("If you would like to see the full catalogue of shows available");
		System.out.println("type: 'show all shows'");
		System.out.println("Alternatively, you are able to search for a specific performance");
		System.out.println("by typing in the name of the show");
		System.out.println("If you would like to check an existing order, type in your");
		System.out.println("booking reference.");
		System.out.println("Finally, if you would like to quit the application type in 'quit'.");
		System.out.println();
		System.out.print("> ");
	}
	
	/*
	 * Method to show all available shows.
	 */
	public void allShows(String input) {
		String output = sql.getName(input);
		System.out.println(output);
	}
	
	/*
	 * Method to select specific event
	 */
	public void selectEvent() {
		System.out.println("type in the event ID of the performance you would");
		System.out.println("like to attend");
		System.out.println();
		System.out.print("> ");
		
		int eventID = Integer.parseInt(reader.getInput());
		
		String performances = sql.getPerformances(eventID);
		
		System.out.println(performances);
		
		addTickets();
	}

	/*
	 * Method to add tickets to the transaction basket.
	 */
	public void addTickets() {
		
		System.out.println("Please type in the year of the event ");
		System.out.println("you'd like to attent (yyyy).");
		System.out.println();
		System.out.print("> ");
		
		String year = reader.getInput();
		
		System.out.println("Please type in the month of the event ");
		System.out.println("you'd like to attent (mm).");
		System.out.println();
		System.out.print("> ");
		
		String month = reader.getInput();
		
		System.out.println("Please type in the date of the event ");
		System.out.println("you'd like to attent (dd).");
		System.out.println();
		System.out.print("> ");
		
		String day = reader.getInput();
		
		String date = year + "-" + month + "-" + day;
		
		System.out.println("Please type in the time of the event ");
		System.out.println("you'd like to attent (hh:mm).");
		System.out.println();
		System.out.print("> ");
		
		String time = reader.getInput();
		
		System.out.println("How many tickets would you like to buy?");
		System.out.println();
		System.out.print("> ");
		
		int numberOfTickets = Integer.parseInt(reader.getInput());
		
		System.out.println("How many of these tickets are concessionary?");
		System.out.println();
		System.out.print("> ");
		
		int concessionary = Integer.parseInt(reader.getInput());
		
		int performanceID = sql.searchPerformance(date, time);
		
		int seatNumber = sql.getNextSeat();
		
		for (int i = 0; i < (numberOfTickets-concessionary); i++) {
			if (concessionary > 0) {
				order.addTickets(i, performanceID, seatNumber, 1);
				concessionary--;
				seatNumber++;
			} else {
				order.addTickets(i, performanceID, seatNumber, 0);
				seatNumber++;
			}
		}
		
		System.out.println("Tickets were added to your basket. If you would like to see what");
		System.out.println("is in your basket type: 'see basket'. Alternatively if you would");
		System.out.println("like to checkout type: 'checkout'.");
		System.out.println();
		System.out.print("> ");
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
		System.out.println("Please type in your name.");
		System.out.println();
		System.out.print("> ");
		
		String purchaserName = reader.getInput();
		
		System.out.println("Please type in your birth year (yyyy).");
		System.out.println();
		System.out.print("> ");
		
		String year = reader.getInput();
		
		System.out.println("Please type in your birth month (mm).");
		System.out.println();
		System.out.print("> ");
		
		String month = reader.getInput();
		
		System.out.println("Please type in your birth date (dd).");
		System.out.println();
		System.out.print("> ");
		
		String day = reader.getInput();
		
		String purchaserDOB = year + "-" + month + "-" + day;
		
		System.out.println("Please type in your House Number.");
		System.out.println();
		System.out.print("> ");
		
		String purchaserAddressHouseNumber = reader.getInput();
		
		System.out.println("Please type in your Street Name.");
		System.out.println();
		System.out.print("> ");
		
		String purchaserAddressStreet = reader.getInput();
		
		System.out.println("Please type in your City Name.");
		System.out.println();
		System.out.print("> ");
		
		String purchaserAddressCity = reader.getInput();
		
		System.out.println("Please type in your County.");
		System.out.println();
		System.out.print("> ");
		
		String purchaserAddressCounty = reader.getInput();
		
		System.out.println("Please type in your Postcode.");
		System.out.println();
		System.out.print("> ");
		
		String purchaserAddressPostcode = reader.getInput();
		
		System.out.println("Please type in your credit card number.");
		System.out.println();
		System.out.print("> ");
		
		int purchaserCreditCard = Integer.parseInt(reader.getInput());
		
		System.out.println("Please type in 'complete order' to complete purchase.");
		System.out.println();
		System.out.print("> ");
		
		if (reader.getInput().contains("complete order")) {
			sql.finaliseOrder(
					purchaserName, 
					purchaserDOB,
					purchaserAddressHouseNumber,
					purchaserAddressStreet, 
					purchaserAddressCity, 
					purchaserAddressCounty, 
					purchaserAddressPostcode, 
					purchaserCreditCard);
			
			int bookingReference = sql.getBookingReference();
			
			System.out.println("Order has been completed, your booking reference is:" + bookingReference);
		} else {
			System.out.println("Instruction has not been understood.");
			System.out.println("Please type in 'complete order' to complete purchase.");
		}
	}

	/*
	 * Method to get contents of the basket.
	 */
	public void getBasket() {
		System.out.println("Your basket contains the following tickets:");
		System.out.println(sql.getPerformanceInformation(0));
	}
	
	/*
	 * Close the programme
	 */
	private boolean quit() {
        System.out.println("Are you sure? Type yes or no.");
        System.out.println();
		System.out.print("> ");
		
		if (reader.getInput().contains("yes")) {
			sql.changeFinished(true);
			return true;
		} else {
			return false;
		}
        
        
    }

}
