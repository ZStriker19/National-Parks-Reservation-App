package com.techelevator;

import java.util.ArrayList;

import DAOInterfacesAndJavaBeans.Park;
import JDBCs.JDBCParkDAO;

public class ParkManager {
	private int id;
	ArrayList<Park> allParks;
	
	
public String[] getParkNames() {
	String[] parkNamesArr= new String[allParks.size()];
	for (int i = 0; i < allParks.size(); i++) {
		String name = allParks.get(i).getName();
		parkNamesArr[i] = name;
		
	}
	
//		String[] parkNamesArr = (String[]) allParks.stream().map(park -> park.getName()).toArray();
	return parkNamesArr;
	}



	public void setParks(JDBCParkDAO parkDAO) {
	this.allParks = (ArrayList<Park>) parkDAO.getAllParks();
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getParkString() {
		Park park = allParks.get(id);
		//write method to create better formatted description (with line breaks)
		String parkInfo = String.format("%s National Park \nLocation: \t%s \nEstablished: %s \nArea: \t\t%s sq km \nAnnual Visitors: %s \n\n%s\n",
				park.getName(), park.getLocation(), park.getEstablish_date(), park.getArea(), park.getVisitors(), park.getDescription());
		return parkInfo;
	}
	 

}
