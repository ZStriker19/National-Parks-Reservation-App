package com.techelevator;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.sql.DataSource;

import DAOInterfacesAndJavaBeans.Campground;
import DAOInterfacesAndJavaBeans.Reservation;
import DAOInterfacesAndJavaBeans.Site;
import JDBCs.JDBCCampgroundDAO;
import JDBCs.JDBCReservationDAO;

public class CampgroundManager {
	
	private ArrayList <Campground> campgrounds = new ArrayList<Campground>();
	private String [] availabileSiteStr;
	private int campgroundSelected = 0;
	private Date arrivalDate;
	private Date departureDate;
	private int siteSelected = 0;
	String reservationName = "";
	

	public ArrayList<Campground> getCampgrounds () {
		return campgrounds;
	}
	
	public String[] getAvilCampgroundsStr() {
		String[] campgroundsArr = (String[]) campgrounds.stream().toArray();
		return campgroundsArr;
	}
	
	public String[] createCampgroundStr() {
		String[] campgroundStrToDisplay = new String[campgrounds.size()];
		for(int i=0; i<campgrounds.size(); i++) {
			String campgroundStr = String.format("%s\t%s\t%s\t%s\n", 
					campgrounds.get(i).getName(), campgrounds.get(i).getOpen_from_mm(), 
					campgrounds.get(i).getOpen_to_mm(), "$" + campgrounds.get(i).getDaily_fee().toString()); 
			campgroundStrToDisplay[i] = campgroundStr;
		}
		return campgroundStrToDisplay;
	}
	
	public void setAvailCampgrounds(JDBCCampgroundDAO campgroundDAO, int park_id) {
		campgrounds = (ArrayList<Campground>) campgroundDAO.getAllCampgrounds(park_id);
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
	
	public long createReservation(JDBCReservationDAO reservationDAO) {
		Reservation newReservation = new Reservation();
		newReservation.setFrom_date(arrivalDate);
		newReservation.setTo_date(departureDate);
		newReservation.setSite_id(siteSelected);
		reservationDAO.createReservation(newReservation);
		
		return newReservation.getReservation_id();
		
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

