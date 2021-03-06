package controller;

import util.InputReader;
import model.Transaction;

import java.util.ArrayList;

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
		
		
		while (!finished) {
			printWelcome();
			
			String input = reader.getInput().toLowerCase();

			if (input.contains("show all shows")) {
				System.out.println(sql.browseAllShows());
				selectEventOrBack();
			}
			else if (input.contains("search name")){
				System.out.println("Enter Name or part of name of show");
				System.out.println();
				System.out.print("> ");
				
				searchShowsName();
			}
			else if (input.contains("search date")){
				System.out.println("Enter Date or part of Date of show, in format YYYY-MM-DD");
				System.out.println();
				System.out.print("> ");
				
				searchShowsDate();
			}
			else if (input.contains("see order")) {
				this.getOrder();
			}
			else if (input.equals("quit")){
				finished = this.quit();
			}
			else {
				System.out.println("command not valid, enter again");
			}
		}
	}
	
	/*
	 * Welcome message when the application opens. 
	 */
	private void printWelcome() {
		System.out.println("Welcome to the Theatre Royal website.");
		this.mainMenu();
	}
	
	private void mainMenu() {
		System.out.println("If you would like to see the full catalogue of shows available");
		System.out.println("type:");
		System.out.println();
		System.out.println("'show all shows' to browse all available shows");
		System.out.println();
		System.out.println("Alternatively, you are able to search for a specific performance");
		System.out.println("by typing:"); 
		System.out.println();
		System.out.println("'search name' to search by name");
		System.out.println("'search date' to search by date");
		System.out.println();
		System.out.println("If you would like to check an existing order, type in");
		System.out.println("'see order'");
		System.out.println();
		System.out.println("Finally, if you would like to quit the application type in 'quit'.");
		System.out.println();
		System.out.print("> ");
	}
	
	/*
	 * Method to show all available shows based on search term.
	 */
	//public void allShows(String input) {
	public void searchShowsName() {
		String input = reader.getInput();
		String output = sql.getName(input);
		System.out.println(output);
		selectEventOrBack();
	}
	
	/*
	 * Method to show all shows based on search term Date
	 */
	public void searchShowsDate() {
		String input = reader.getInput();
		String output = sql.searchPerformanceByDate(input);
		System.out.println(output);
		selectEventOrBack();
	}
	
	/*
	 * method to either select event from list or return to main
	 */
	private void selectEventOrBack() {
		boolean retToMain = false;
		System.out.println("type 'more info' to see more info about an event");
		System.out.println("or 'back' to return to main menu");
		System.out.println();
		System.out.print("> ");
		
		while(!retToMain) {
			String input = reader.getInput().toLowerCase();
			
			if (input.contains("more info")) {
				retToMain = true; // reset to break out of loop on return from selectEvent
				selectEvent();
			}
			else if (input.contains("back")) {
				retToMain = true;
			}
			else{
				System.out.println("try again");
			}
		}
	}
	
	/*
	 * Method to select specific event
	 */
	public void selectEvent() {
		System.out.println("type in the event ID of the performance you would");
		System.out.println("like to attend");
		System.out.println();
		System.out.print("> ");
		
		String eventID = reader.getInput();

		System.out.println("Event Selected");
		System.out.println();
		System.out.println(sql.GetEventInfo(eventID)); // print event info
		System.out.println("Available Performances");
		System.out.println();
		System.out.println(sql.getPerformances(eventID)); // print event performances
		
		boolean retToMain = false;
		
		while (!retToMain) {
			System.out.println("if you would like to purchase tickets for a performance listed");
			System.out.println("type 'add tickets'");
			System.out.println("otherwise type 'back' to return to main menu");
			System.out.println();
			System.out.print("> ");
			
			String input = reader.getInput().toLowerCase();
			
			if (input.contains("add tickets")) {
				retToMain = true; //reset to break out of loop on return from addTickets
				addTickets(eventID);
			}
			else if (input.contains("back")) {
				retToMain = true;
			}
			else{
				System.out.println("try again");
			}
			
			
		}
		
	}

	/*
	 * Method to add tickets to the transaction basket.
	 */
	public void addTickets(String eventID) {
		
		System.out.println("Enter performanceID of performance you want to book");
		System.out.println();
		System.out.print("> ");
		
		String performanceID = reader.getInput();
		
		System.out.println("How many tickets would you like to buy?");
		System.out.println();
		System.out.print("> ");
		
		int numberOfTickets = Integer.parseInt(reader.getInput());
		
		System.out.println("How many of these tickets are concessionary?");
		System.out.println();
		System.out.print("> ");
		
		int concessionary = Integer.parseInt(reader.getInput());
		
		System.out.println("Type in stalls for stalls seats and circle for circle seats.");
		System.out.println();
		System.out.print("> ");
		
		String input = reader.getInput();
		Boolean stalls = true;
		
		if (input.contains("stalls")) {
			stalls = true;
		} else if (input.contains("circle")) {
			stalls = false;
		}
		
		int price = Integer.parseInt(sql.getPerformancePrice(eventID, stalls).replaceAll("\\D", ""));
		
		double totalPrice = ((price/100) * numberOfTickets) - (0.25 * (price/100) * concessionary);
		
		String seatNumber = sql.getNextSeat(performanceID, stalls);
		
		int seatNumberInt = Integer.parseInt(seatNumber.replaceAll("\\D", ""));
		
		if (!stalls) {
			seatNumberInt += 120;
			seatNumber = Integer.toString(seatNumberInt);
		}
		
			for (int i = 0; i < numberOfTickets; i++) {
				if (concessionary > 0) {
					order.addTickets(performanceID, seatNumber, "TRUE");
					concessionary--;
					seatNumberInt--;
					seatNumber = Integer.toString(seatNumberInt);
				} else {
					order.addTickets(performanceID, seatNumber, "FALSE");
					seatNumberInt--;
					seatNumber = Integer.toString(seatNumberInt);
				}
			}
			
			this.loopPurchase(totalPrice);
		}

	/*
	 * method to loop, buy more tickets, check basket or checkout
	 */
	private void loopPurchase(double totalPrice) {
		boolean retToMain = false;
		while (!retToMain) {
			System.out.println("Tickets were added to your basket. If you would like to see what");
			System.out.println("is in your basket type: 'see basket'. Alternatively if you would");
			System.out.println("like to checkout type: 'checkout'.");
			System.out.println("Or if you would like to search for more shows, type 'main menu'");
			System.out.println();
			System.out.print("> ");
			
			String input = reader.getInput().toLowerCase();
			
			if (input.contains("see basket")) {
				retToMain = true; //reset to break out of loop on return
				this.getBasket(totalPrice);
			} else if (input.contains("checkout")) {
				retToMain = true; //reset to break out of loop on return
				this.finaliseOrder();
			} else if (input.contains("main menu")) {
				retToMain = true;
			}
		}
	}


	/*
	 * Method to get finalised order.
	 */
	public void getOrder() {
		System.out.println("Please type in your Booking Reference Number");
		System.out.println();
		System.out.print(">");
		
		String bookingReference = reader.getInput();
		
		System.out.println(sql.getOrderDetails(bookingReference));
	}

	/*
	 * Method to finalise order in basket (i.e. check-out).
	 */
	public void finaliseOrder() {
		System.out.println("Please type in your full name.");
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
		
		String purchaserCreditCard = reader.getInput();
		
		System.out.println("Please type in 'complete order' to complete purchase.");
		System.out.println();
		System.out.print("> ");
		
		if (reader.getInput().contains("complete order")) {
			String purchaserID = sql.finaliseOrderPurchaser(
					purchaserName, 
					purchaserDOB,
					purchaserAddressHouseNumber,
					purchaserAddressStreet, 
					purchaserAddressCity, 
					purchaserAddressCounty, 
					purchaserAddressPostcode, 
					purchaserCreditCard);
			
			for (int i = 0; i < order.getTickets().size(); i++) {
				String perf = order.getTickets().get(i).get(0);			
				int seatInt = Integer.parseInt(order.getTickets().get(i).get(1).replaceAll("\\D", "")); 
				String seatString = order.getTickets().get(i).get(1).toString();
				String conc = order.getTickets().get(i).get(2).toString();
				String stalls;
				
				if (seatInt <= 120 && seatInt >= 1) {
					stalls = "TRUE";
				} else { 
					stalls = "FALSE";
				}
				
				sql.finaliseOrderBooking(
						perf,
						purchaserID,
						conc,
						stalls,
						seatString);
			}
			
			System.out.println("Order has been completed, your booking reference is: " + purchaserID);
			System.out.println("If you would like to search for more shows, type 'main menu'.");
			System.out.println("Alternatively if you would like to quit the application type 'quit'.");
			System.out.println();
			System.out.print("> ");
			
			String input = reader.getInput();
			
			Boolean finished = false;
			
			while (!finished) {
			
				if (input.contains("main menu")) {
					this.mainMenu();
					finished = true;
				} else if (input.contains("quit")) {
					this.quit();
					finished = true;
				} else {
					System.out.println("Instruction has not been understood.");
					System.out.println("If you would like to search for more shows, type 'main menu'.");
					System.out.println("Alternatively if you would like to quit the application type 'quit'.");
					System.out.println();
					System.out.print("> ");
					
					input = reader.getInput();
				}
			}

		} else {
			System.out.println("Instruction has not been understood.");
			System.out.println("Please type in 'complete order' to complete purchase.");
			System.out.println();
			System.out.print("> ");
		}
	}

	/*
	 * Method to get contents of the basket.
	 */
	public void getBasket(double Price) {
		System.out.println("Your basket contains the following tickets:");
		//System.out.println(sql.getPerformanceInformation(0));
		System.out.println();
		
		// order.addTickets(performanceID, seatNumber, 1);
		System.out.println("-----------------");
		for (int i = 0; i < order.getTickets().size(); i++) {
			System.out.print("Performance: ");
			String even = sql.getEventFromPerformance(order.getTickets().get(i).get(0));
			String perf = sql.getPerformance(order.getTickets().get(i).get(0));
			System.out.print(even);
			System.out.print(perf);
			System.out.println();
			
			System.out.print("Seat Number: ");			
			String seat = order.getTickets().get(i).get(1);
			System.out.print(seat);
			System.out.println();
			
			System.out.print("Concessionary? ");
			String conc = order.getTickets().get(i).get(2);
			
			if (conc == "TRUE") {
				System.out.print("Yes");
			} else if (conc == "FALSE") {
				System.out.print("No");
			}
			System.out.println();
			System.out.println("-----------------");
		}
		
		String totalPrice = Double.toString(Price);
		
		System.out.println("Your total price is: " + totalPrice);
		System.out.println("-----------------");
		System.out.println();
		
		this.loopPurchase(Price);
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