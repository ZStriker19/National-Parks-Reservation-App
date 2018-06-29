package com.techelevator;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import com.techelevator.view.Menu;

import DAOInterfacesAndJavaBeans.Campground;
import JDBCs.JDBCCampgroundDAO;

public class CampgroundMenu extends Menu{

	private final String searchForAvailRes = "Search for Available Reservation";
	private final String returnToPreviousScreen = "Return to Previous Screen";
	
	private final String[] viewCampgroundMenuOptions = { searchForAvailRes, returnToPreviousScreen};
	
	public CampgroundMenu(InputStream input, OutputStream output) {
		super(input, output);
		
	}
	
	public void displayAllCampgroundsForPark(CampgroundManager campgroundManager) {
		System.out.println("Name\t\t Open\t Close\t\t Daily Fee\n");
		super.displayMenuOptions(campgroundManager.createCampgroundStr());
	}
	
	public void setCampgrounds(CampgroundManager campgroundManager, int park_id, JDBCCampgroundDAO campgroundDAO) {
		campgroundManager.setAvailCampgrounds(campgroundDAO, park_id);
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
