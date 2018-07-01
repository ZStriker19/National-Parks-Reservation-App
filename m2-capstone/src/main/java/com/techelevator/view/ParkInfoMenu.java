package com.techelevator.view;

import java.io.InputStream;
import java.io.OutputStream;

import com.techelevator.ParkManager;

public class ParkInfoMenu extends Menu {

	private final String viewCampgrounds = "View Campgrounds";
	private final String searchForReservation = "Search for Reservation";
	private final String returnToPreviousScreen = "Return to Previous Screen";
	
	private final String[] campgroundOptions = {viewCampgrounds, searchForReservation, returnToPreviousScreen };
	
	public ParkInfoMenu(InputStream input, OutputStream output) {
		super(input, output);
		
	}
	public String[] getCampgroundOptions() {
		return campgroundOptions;
	}
	public String getViewCampgrounds() {
		return viewCampgrounds;
	}
	public String getSearchForReservation() {
		return searchForReservation;
	}
	public String getReturnToPreviousScreen() {
		return returnToPreviousScreen;
	}
	public void getSelectedParkInformation(int userInput, ParkManager parkManager) {
		parkManager.setId(userInput);
		System.out.print("\n" + parkManager.getParkString());
	}
	public void displayCampgrounds() {
		
	}
	

}
