package com.techelevator.view;

import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.techelevator.CampgroundManager;
import com.techelevator.CampgroundMenu;

import DAOInterfacesAndJavaBeans.Campground;
import JDBCs.JDBCReservationDAO;

public class ReservationMenu extends Menu {
	
	private String[] campgroundStr;
	final static String DATE_FORMAT = "dd-MM-yyyy";
	
	public ReservationMenu(InputStream input, OutputStream output) {
		super(input, output);
		
	}
	
	public String[] getCampgroundStr(CampgroundManager campgroundManager) {
		System.out.println("Name \t Open \t Close \t Daily Fee");
		return campgroundManager.createCampgroundStr;
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
	
	public void selectSiteToReserve(CampgroundManager campgroundManager) {
		System.out.print("Which site should be reserved (use the left most column to select) (enter Q to cancel)? ");
		campgroundManager.setSiteSelected(super.getChoiceFromOptions(campgroundManager.getSiteAvailabilityStr()));
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
				invalidName = true;
			}
		}
	}
	
	public void createReservation(CampgroundManager campgroundManager, JDBCReservationDAO reservationDAO) {
		long reservationId = campgroundManager.createReservation(reservationDAO);
		System.out.print("The reservation has been made and the confirmation id is: " + reservationId);
	}
	
	@Override
	public int getChoiceFromOptions(String[] options) {
		int choice = 0;
		while(choice == 0) {
			displayMenuOptions(options);
			printCampgroundToSelect();
			choice = getChoiceFromUserInput(options);
		}
		return choice;
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
	public boolean tryDisplayAvailability(CampgroundManager campgroundManager) {
		if(campgroundManager.checkAvailability()) {
			String [] availabileSiteStr = campgroundManager.getSiteAvailabilityStr();
			//Will display string array
		}
		System.out.println("The dates you selected were unavailable :( ");
		return false;
	}
	
	
	public static boolean isDateValid(String date) {
	        try {
	            DateFormat df = new SimpleDateFormat(DATE_FORMAT);
	            df.setLenient(false);
	            df.parse(date);
	            return true;
	        } catch (ParseException e) {
	            return false;
	        }
	}

}
