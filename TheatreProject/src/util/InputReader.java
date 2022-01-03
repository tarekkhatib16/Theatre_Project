package util;

import java.util.Scanner;

public class InputReader {
	
	private Scanner reader;

	public InputReader() {
		reader = new Scanner(System.in);
	}
	
	public String getInput() {
		String inputLine = reader.nextLine();
		return inputLine;
	}
	
}
