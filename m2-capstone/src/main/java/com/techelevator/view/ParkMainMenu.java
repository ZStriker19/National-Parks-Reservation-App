package com.techelevator.view;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import DAOInterfacesAndJavaBeans.Park;

public class ParkMainMenu extends Menu {
	
	private List<String> parkNames = new ArrayList<String>();
	private List<Park> parkId;

	public ParkMainMenu(InputStream input, OutputStream output) {
		super(input, output);
		
	}

	
//	private setParks() {
//		this.parkNames = parkManager.getParkNames 
//	}
	
	
	public String[] getParks() {
		//return park names.
	}

}
