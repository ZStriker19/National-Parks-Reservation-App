package com.techelevator.view;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class Menu {

	protected PrintWriter out;
	protected Scanner in;

	public Menu(InputStream input, OutputStream output) {
		this.out = new PrintWriter(output);
		this.in = new Scanner(input);
	}
//Same method as below, but returns int
	public int getChoiceFromOptions(String[] options) {
		int choice = -2;
		while(choice < -1) {
			displayMenuOptions(options);
			choice = getChoiceFromUserInput(options);
		}
		return choice;
	}
	
	protected int getChoiceFromUserInput(String[] options) {
		int choice = -2;
		String userInput = in.nextLine();
		if(userInput.equals("q") || userInput.equals("Q") ) {
			choice = -1;
		}
		else {
			try {
				int selectedOption = Integer.valueOf(userInput);
				if(selectedOption <= options.length && selectedOption > 0) {
					choice = selectedOption -1;
				}
			} catch(NumberFormatException e) {
				// eat the exception, an error message will be displayed below since choice will be null
			}
			if(choice == -2) {
				out.println("\n*** "+userInput+" is not a valid option ***\n");
			}
		}
		return choice;
	}
	protected void displayMenuOptions(Object[] options) {
		out.println();
		for(int i = 0; i < options.length; i++) {
			int optionNum = i+1;
			out.println(optionNum+") "+options[i]);
		}
		out.print("\nPlease choose an option >>> ");
		out.flush();
	}
	//Same methods as above, but returns string
	public String getChoiceFromOptionsSTR(String[] options) {
		String choice = null;
		while(choice == null) {
			displayMenuOptions(options);
			choice = getChoiceFromUserInputSTR(options);
		}
		return choice;
	}

	private String getChoiceFromUserInputSTR(String[] options) {
		String choice = null;
		String userInput = in.nextLine();
		try {
			int selectedOption = Integer.valueOf(userInput);
			if(selectedOption > 0 && selectedOption <= options.length) {
				choice = options[selectedOption - 1];
			}
		} catch(NumberFormatException e) {
			// eat the exception, an error message will be displayed below since choice will be null
		}
		if(choice == null) {
			out.println("\n*** "+userInput+" is not a valid option ***\n");
		}
		return choice;
	}
}
