package com.techelevator;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import com.techelevator.view.Menu;

public class CampgroundMenu extends Menu{

	private final String searchForAvailRes = "Search for Available Reservation";
	private final String returnToPreviousScreen = "Return to Previous Screen";
	
	private final String[] viewCampgroundMenuOptions = { searchForAvailRes, returnToPreviousScreen};
	
	public CampgroundMenu(InputStream input, OutputStream output) {
		super(input, output);
		
	}
	public void displayAllCampgroundsForPark(CampgroundManager campgroundManager) {
		ArrayList <Campground> campgrounds = campgroundManager.getCampgrounds();
		//need actual campground info to present. Will be done after DAO creation.
	}
	public String getSearchForAvailRes() {
		return searchForAvailRes;
	}
	public String getReturnToPreviousScreen() {
		return returnToPreviousScreen;
	}
	public String[] getViewCampgroundMenuOptions() {
		return viewCampgroundMenuOptions;
	}
	
	
	

}
