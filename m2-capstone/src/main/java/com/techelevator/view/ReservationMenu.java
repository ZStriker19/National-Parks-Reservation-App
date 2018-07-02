package com.techelevator.view;

import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.techelevator.CampgroundManager;
import com.techelevator.CampgroundMenu;

import DAOInterfacesAndJavaBeans.Campground;
import JDBCs.JDBCReservationDAO;
import JDBCs.JDBCSiteDAO;

public class ReservationMenu extends Menu {
	
	private String[] campgroundStr;
	final static String DATE_FORMAT = "dd-MM-yyyy";
	
	public ReservationMenu(InputStream input, OutputStream output) {
		super(input, output);
		
	}
	
	public String[] getCampgroundStr(CampgroundManager campgroundManager) {
		System.out.println("Name \t Open \t Close \t Daily Fee");
		String[] campgroundStrs =  campgroundManager.createCampgroundStr();
		return campgroundStrs;
	}
	
	public void printCampgroundToSelect() {
		System.out.print("Which campground (enter Q to cancel)? ");
	}
	
	public void askArrivalDate(CampgroundManager campgroundManager) {
		boolean isNotValidDate = true;
		while(isNotValidDate) {
		System.out.print("What is the arrival date? (MM/DD/YYYY) ");
		String arrivalUserInput = in.nextLine();
		if(isDateValid(arrivalUserInput)) {
			setArrivalDate(arrivalUserInput, campgroundManager);
			isNotValidDate = false;
		}
		else 
			System.out.print("You have entered an invalid date. Please try again (MM/DD/YYYY): ");
		}
			
	}
	
	public void askDepatureDate(CampgroundManager campgroundManager) {
		boolean isNotValidDate = true;
		while(isNotValidDate) {
		System.out.print("What is the departure date? (MM/DD/YYYY) ");	
		String departureUserInput = in.nextLine();
		if(isDateValid(departureUserInput)) {
			setDepartureDate(departureUserInput, campgroundManager);
			isNotValidDate = false;
		}
		else
			System.out.print("You have entered an invalid date. Please try again (MM/DD/YYYY): ");
		}
	}
	
	public int selectSiteToReserve(CampgroundManager campgroundManager) {
		out.println("Results matching your criteria:\n");
		out.println("Campground\t SiteNumber\t Max Occup.\t Accessible?\t  RvLen\t Utility\t Cost\n");
		return super.getChoiceFromOptions(campgroundManager.getSiteAvailabilityStr());
	}
	
	public void setSiteSelected(int siteSelected, CampgroundManager campgroundManager) {
		campgroundManager.setSiteSelected(siteSelected);
	}
	
	
	//seeing if this overides so we can put custom message for site choice.
	protected void displayMenuOptions(Object[] options) {
		out.println();
		for(int i = 0; i < options.length; i++) {
			int optionNum = i+1;
			out.println(optionNum+") "+options[i]);
		}
		out.print("Please select a location to reserve (use the left most column to select) (enter Q to cancel)? ");
		out.flush();
	}
	
	public void getReservationName(CampgroundManager campgroundManager) {
		boolean invalidName = true;
		while(invalidName) {
		System.out.print("What name should the reservation be made under? ");
		String userInput = in.nextLine();
			if(userInput.equals("")) {
				continue;
			}
			else{
				campgroundManager.setReservationName(userInput);
				invalidName = false;
			}
		}
	}
	
	public void createReservation(CampgroundManager campgroundManager, JDBCReservationDAO reservationDAO) {
		long reservationId = campgroundManager.createReservation(reservationDAO);
		System.out.print("The reservation has been made and the confirmation id is: " + reservationId);
	}
	
	
	public String[] getCampgroundStr() {
		return campgroundStr;
	}
	public void setCampgroundSelected(int i, CampgroundManager campgroundManager ) {
		campgroundManager.setCampgroundSelected(i);
	}
	public void setArrivalDate(String date, CampgroundManager campgroundManager) {
		campgroundManager.setArrivalDate(date);
	}
	public void setDepartureDate(String date, CampgroundManager campgroundManager) {
		campgroundManager.setDepartureDate(date);
	}
	
	//Results code
	public boolean tryDisplayAvailability(CampgroundManager campgroundManager, JDBCSiteDAO siteDAO) {
		if(campgroundManager.checkAvailability(siteDAO)) {
			return true;
		}
		System.out.println("The dates you selected were unavailable :( ");
		return false;
	}
	
	
	public static boolean isDateValid(String date) {
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
        Date d = null;
        df.setLenient(false);
	        try {
	            d = df.parse(date);
	            return true;
	        } catch (Exception e) {
	            return false;
	        }
	}

}
