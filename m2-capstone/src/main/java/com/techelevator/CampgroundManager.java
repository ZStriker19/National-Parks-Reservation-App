package com.techelevator;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import DAOInterfacesAndJavaBeans.Campground;
import DAOInterfacesAndJavaBeans.Reservation;
import DAOInterfacesAndJavaBeans.Site;
import JDBCs.JDBCCampgroundDAO;
import JDBCs.JDBCReservationDAO;
import JDBCs.JDBCSiteDAO;

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
			//NEED TO FORMAT STRING TO BE PRETTIER!!!!! Individual human take care of this.
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
	public boolean checkAvailability(JDBCSiteDAO siteDAO) {
		System.out.println(campgroundSelected);
//		List<Site> sites = siteDAO.getAllCampgroundSites(campgroundSelected);
		List<Site> sites = siteDAO.getAllCampgroundSites(campgroundSelected);
		//Call DAO based on information collected
		if(sites.size() > 0) {
			Site[] sitesArr = (Site[]) sites.stream().toArray(Site[]::new);
			createSiteSTR(sitesArr);
			return true;
		}
		else {
			return false;
		}
	}
	
	private void createSiteSTR(Site[] sites) {
		String[] availableSiteStr = new String[sites.length];
		String campgroundName = "";
		String cost = "";
		String accessible = "";
		String utilities = "";
		String rvLength = "";
		for (int i = 0; i < sites.length; i++) {
			for (int j = 0; j < campgrounds.size(); j++) {
				if (sites[i].getCampground_id() == (int) campgrounds.get(j).getCampground_id()) {
					campgroundName = campgrounds.get(j).getName();
					
					long numDays =  (arrivalDate.getTime()-departureDate.getTime())/86400000;
			        long absNumDays =  Math.abs(numDays);
					cost = campgrounds.get(j).getDaily_fee().multiply(new BigDecimal(absNumDays)).setScale(2, RoundingMode.DOWN).toString(); 
				}
				if (sites[i].isAccessible()) {
					accessible =  "Yes";
				} else {
					accessible = "No";
				}
				
				if (sites[i].getMax_rv_length() == 0) {
					rvLength = "N/A";
				} else {
					rvLength = Integer.toString(sites[i].getMax_rv_length());
				}
				
				if (sites[i].isUtilities()) {
					utilities = "Yes";
				} else {
					utilities = "N/A";
				}
				
			} 
			
			String siteStr = String.format(("%s\t\t %s\t\t %s\t\t %s\t %s\t %s\t\t %s\n"), campgroundName, sites[i].getSite_number(), sites[i].getMax_occupancy(),
					accessible, rvLength, utilities, cost);
			availableSiteStr[i] = siteStr;
			
		}
		this.availabileSiteStr = availableSiteStr;
	}
	
	public long createReservation(JDBCReservationDAO reservationDAO) {
		Reservation newReservation = new Reservation();
		newReservation.setFrom_date(arrivalDate);
		newReservation.setTo_date(departureDate);
		newReservation.setSite_id(siteSelected);
		newReservation.setName(reservationName);
		return reservationDAO.createReservation(newReservation);
		
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

