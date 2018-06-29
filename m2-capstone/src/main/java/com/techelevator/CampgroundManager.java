package com.techelevator;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import DAOInterfacesAndJavaBeans.Campground;
import DAOInterfacesAndJavaBeans.Site;

public class CampgroundManager {
	
	private ArrayList <Campground> campgrounds = new ArrayList<Campground>();
	private String [] availabileSiteStr;
	private int campgroundSelected =0;
	private Date arrivalDate;
	private Date departureDate;
	private int siteSelected = 0;
	String reservationName = "";
	
	public ArrayList getCampgrounds () {
		return campgrounds;
	}
	
	public String[] getAvilCampgrounds() {
		String[] camgroundsArr = (String[]) campgrounds.stream().toArray();
		return camgroundsArr;
	}
	
	public String[] createCampgroundStr() {
		//waiting to create method until the DAO's are created.
		//Will return a string for each campground to reservationMenu
		//using a for loop
		return null;
		
	}
	
	//Get campground availability 
	public boolean checkAvailability() {
		//Call DAO based on information collected
		if(results.next()) {
			//Call create site if there are results to feed to the menu.
		}
		else {
			return false;
		}
	}
	public String[] createSite(Site[] sites) {
		//Creates strings to represent sites for presentation 
		//Add all sites converted to String to list and set to availabileSiteStr
		return null;
	}
	public void setSiteSelected(int i) {
		this.siteSelected = i;
	}
	
	public void setCampgroundSelected(int i) {
		this.campgroundSelected = i;
	}
	public void setArrivalDate(String date) {
		try {
			this.arrivalDate = new SimpleDateFormat("mm/dd/yyy").parse(date);
		}
		catch (ParseException e) {
			e.printStackTrace();
		}
	}
	public void setDepartureDate(String date) {
		try {
			this.departureDate = new SimpleDateFormat("mm/dd/yyy").parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	public Date getArrivalDate() {
		return arrivalDate;
	}
	
	public Date getDepartureDate() {
		return departureDate;
	}
	
	public String[] getSiteAvailabilityStr() {
		return availabileSiteStr;
	}
	
	public void setReservationName(String reservationName) {
		this.reservationName = reservationName;
	}
	public String getReservationName() {
		return reservationName;
	}
	
}

