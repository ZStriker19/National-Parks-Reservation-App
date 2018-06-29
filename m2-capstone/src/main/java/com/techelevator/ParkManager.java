package com.techelevator;

import java.util.ArrayList;

import DAOInterfacesAndJavaBeans.Park;

public class ParkManager {
	private int id;
	ArrayList<Park> allParks;
	
	
public String[] getParkNames() {
		String[] parkNamesArr = (String[]) parkNames.stream().toArray();
		return parkNamesArr;
	}



	//private setParks() {
	//this = //call ParksDAO getParkNames 
	//}

public void setId(int id) {
	this.id = id;
}

}
