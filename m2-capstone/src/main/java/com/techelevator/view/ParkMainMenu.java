package com.techelevator.view;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import com.techelevator.ParkManager;

import DAOInterfacesAndJavaBeans.Park;
import JDBCs.JDBCParkDAO;

public class ParkMainMenu extends Menu {
	
	private List<String> parkNames = new ArrayList<String>();
	

	public ParkMainMenu(InputStream input, OutputStream output) {
		super(input, output);
	}

	
	public void setParks(ParkManager parkManager, JDBCParkDAO parkDAO) {
		parkManager.setParks(parkDAO);
	}
	
	
	public String[] getParkNames(ParkManager parkManager) {
		return parkManager.getParkNames();
	}

}
