package com.techelevator;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

import com.techelevator.view.ParkInfoMenu;
import com.techelevator.view.ParkMainMenu;
import com.techelevator.view.ReservationMenu;

import JDBCs.JDBCCampgroundDAO;
import JDBCs.JDBCParkDAO;
import JDBCs.JDBCReservationDAO;
import JDBCs.JDBCSiteDAO;


public class CampgroundCLI {
    private ParkMainMenu parkMenu;
    private ParkInfoMenu parkInfoMenu;
    private CampgroundMenu campgroundMenu;
    private ReservationMenu reservationMenu;
    private CampgroundManager campgroundManager;
    private ParkManager parkManager;
    
    private JDBCReservationDAO reservationDAO;
    private JDBCSiteDAO siteDAO;
    private JDBCParkDAO parkDAO;
    private JDBCCampgroundDAO campgroundDAO;

	public CampgroundCLI(DataSource datasource, ParkMainMenu parkMenu, ParkInfoMenu parkInfoMenu, CampgroundMenu campgroundMenu, ReservationMenu reservationMenu, CampgroundManager campgroundManager, ParkManager parkManager, JDBCReservationDAO reservationDAO, JDBCSiteDAO siteDAO, JDBCParkDAO parkDAO, JDBCCampgroundDAO campgroundDAO) {
		
		this.parkMenu = parkMenu;
		this.parkInfoMenu = parkInfoMenu;
		this.campgroundMenu = campgroundMenu;
		this.reservationMenu = reservationMenu;
		this.campgroundManager = campgroundManager;
		this.parkManager = parkManager;
		
		this.reservationDAO = reservationDAO;
		this.siteDAO = siteDAO;
		this.parkDAO = parkDAO;
		this.campgroundDAO = campgroundDAO;
	}
	
	public void run() {
		parkMenu.setParks(parkManager, parkDAO);
		boolean runninIt = true;
		while(runninIt) {
			int choice = parkMenu.getChoiceFromOptions(parkMenu.getParkNames(parkManager));
			if(choice == -1) {
				break;
			}
			boolean runParkMenu = true;
			
			while (runParkMenu) {
				parkInfoMenu.getSelectedParkInformation(choice, parkManager);
				String parkChoice = (String)parkInfoMenu.getChoiceFromOptionsSTR(parkInfoMenu.getCampgroundOptions());
				campgroundMenu.setCampgrounds(campgroundManager, choice, campgroundDAO);
				if(parkChoice.equals(parkInfoMenu.getViewCampgrounds())) {
					campgroundMenu.displayAllCampgroundsForPark(campgroundManager);
					boolean runningViewCampgrounds = true;
					while(runningViewCampgrounds) {
						String viewCampgroundChoice = (String)campgroundMenu.getChoiceFromOptionsSTR(campgroundMenu.getViewCampgroundMenuOptions());
						if(viewCampgroundChoice.equals(campgroundMenu.getSearchForAvailRes())) {
							int campgroundChoice = reservationMenu.getChoiceFromOptions(reservationMenu.getCampgroundStr(campgroundManager));
							if(campgroundChoice == -1) {
								break;
							}
							else {
								reservationMenu.setCampgroundSelected(campgroundChoice, campgroundManager);
								//Start working here again! Need to fix the date comparison! 
								//Don't forget to copy me all below!
								reservationMenu.askArrivalDate(campgroundManager);
								reservationMenu.askDepatureDate(campgroundManager);
								if(reservationMenu.tryDisplayAvailability(campgroundManager, siteDAO)==false) continue;
								reservationMenu.selectSiteToReserve(campgroundManager);
								reservationMenu.getReservationName(campgroundManager);
								//here
								reservationMenu.createReservation(campgroundManager, reservationDAO);
							}
						}
						else if (viewCampgroundChoice.equals(campgroundMenu.getReturnToPreviousScreen())) {
							break;
						}
					}
				}
				else if (parkChoice.equals(parkInfoMenu.getSearchForReservation())){
					int campgroundChoice = reservationMenu.getChoiceFromOptions(reservationMenu.getCampgroundStr(campgroundManager));
					if(campgroundChoice == -1) {
						break;
					}
					else {
						reservationMenu.setCampgroundSelected(campgroundChoice, campgroundManager);
						//Start working here again! Need to fix the date comparison! 
						//Don't forget to copy me all below!
						reservationMenu.askArrivalDate(campgroundManager);
						reservationMenu.askDepatureDate(campgroundManager);
						if(reservationMenu.tryDisplayAvailability(campgroundManager, siteDAO)==false) continue;
						reservationMenu.selectSiteToReserve(campgroundManager);
						reservationMenu.getReservationName(campgroundManager);
						//here
						reservationMenu.createReservation(campgroundManager, reservationDAO);
					}
				}
				else if (parkChoice.equals(parkInfoMenu.getReturnToPreviousScreen())){
					break;
				}
			}
				
		}
		
	}
		
	public static void main(String[] args) {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/campground");
		dataSource.setUsername("postgres");
		ParkMainMenu parkMenu = new ParkMainMenu(System.in, System.out);
		ParkInfoMenu parkInfoMenu = new ParkInfoMenu(System.in, System.out);
		CampgroundMenu campgroundMenu = new CampgroundMenu(System.in, System.out);
		ReservationMenu reservationMenu = new ReservationMenu(System.in, System.out);
		CampgroundManager campgroundManager = new CampgroundManager();
		ParkManager parkManager = new ParkManager();
		JDBCReservationDAO reservationDAO = new JDBCReservationDAO(dataSource);
		JDBCSiteDAO siteDAO = new JDBCSiteDAO(dataSource);
		JDBCParkDAO parkDAO = new JDBCParkDAO(dataSource);
		JDBCCampgroundDAO campgroundDAO = new JDBCCampgroundDAO(dataSource);
		
		CampgroundCLI application = new CampgroundCLI(dataSource, parkMenu, parkInfoMenu, campgroundMenu, reservationMenu, campgroundManager, parkManager, reservationDAO, siteDAO, parkDAO, campgroundDAO);
		
		
		application.run();
	}
}