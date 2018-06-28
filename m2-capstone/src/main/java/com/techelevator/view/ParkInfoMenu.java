package com.techelevator.view;

import java.io.InputStream;
import java.io.OutputStream;

public class ParkInfoMenu extends Menu {

	private final String viewCampgrounds = "View Campgrounds";
	private final String searchForReservation = "Search for Reservation";
	private final String returnToPreviousScreen = "Return to Previous Screen";
	
	private final String[] campgroundOptions = {viewCampgrounds, searchForReservation, returnToPreviousScreen };
	
	public ParkInfoMenu(InputStream input, OutputStream output) {
		super(input, output);
		
	}
	public void getParkInfo() {
		System.out.printf("%s\n%s\n%s\n", viewCampgrounds, searchForReservation, returnToPreviousScreen);
		
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
	

}
