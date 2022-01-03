package util;

import java.util.Scanner;

public class InputReader {
	
	/*
	 * Create an instance of the Scanner class.
	 */
	private Scanner reader;
	
	/*
	 * Initialise the Scanner class in the constructor. 
	 */
	public InputReader() {
		reader = new Scanner(System.in);
	}
	
	/*
	 * Method to get the user's input as a string.
	 */
	public String getInput() {
		String inputLine = reader.nextLine();
		return inputLine;
	}
	
}
