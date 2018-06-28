package com.techelevator;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

import com.techelevator.view.ParkInfoMenu;
import com.techelevator.view.ParkMainMenu;
import com.techelevator.view.ReservationMenu;


public class CampgroundCLI {
    private ParkMainMenu parkMenu;
    private ParkInfoMenu parkInfoMenu;
    private CampgroundMenu campgroundMenu;
    private ReservationMenu reservationMenu;
    private CampgroundManager campgroundManager;

	public CampgroundCLI(DataSource datasource, ParkMainMenu parkMenu, ParkInfoMenu parkInfoMenu, CampgroundMenu campgroundMenu, ReservationMenu reservationMenu, CampgroundManager campgroundManager) {
		// create your DAOs here
		this.parkMenu = parkMenu;
		this.parkInfoMenu = parkInfoMenu;
		this.campgroundMenu = campgroundMenu;
		this.reservationMenu = reservationMenu;
		this.campgroundManager = campgroundManager;
	}
	
	public void run() {
		parkMenu.setParks();
		boolean runninIt = true;
		while(runninIt) {
			int choice = parkMenu.getChoiceFromOptions(parkMenu.getParks());
			if(choice == -1) {
				break;
			}
			boolean runParkMenu = true;
			
			while (runParkMenu) {
				String parkChoice = (String)parkInfoMenu.getChoiceFromOptionsSTR(parkInfoMenu.getCampgroundOptions());
				
				if(parkChoice.equals(parkInfoMenu.getViewCampgrounds())) {
					
					boolean runningViewCampgrounds = true;
					while(runningViewCampgrounds) {
						String viewCampgroundChoice = (String)campgroundMenu.getChoiceFromOptionsSTR(campgroundMenu.getViewCampgroundMenuOptions());
						if(viewCampgroundChoice.equals(campgroundMenu.getSearchForAvailRes())) {
							int campgroundChoice = reservationMenu.getChoiceFromOptions(reservationMenu.getCampgroundStr());
							if(campgroundChoice == -1) {
								break;
							}
							else {
								reservationMenu.setCampgroundSelected(campgroundChoice, campgroundManager);
								reservationMenu.askArrivalDate(campgroundManager);
								reservationMenu.askDepatureDate(campgroundManager);
								if(reservationMenu.tryDisplayAvailability(campgroundManager)==false) continue;
								reservationMenu.selectSiteToReserve(campgroundManager);
								reservationMenu.getReservationName(campgroundManager);
							}
						}
						else if (viewCampgroundChoice.equals(campgroundMenu.getReturnToPreviousScreen())) {
							break;
						}
					}
				}
				else if (parkChoice.equals(parkInfoMenu.getSearchForReservation())){
					int campgroundChoice = reservationMenu.getChoiceFromOptions(reservationMenu.getCampgroundStr());
					if(campgroundChoice == -1) {
						break;
					}
					else {
						reservationMenu.setCampgroundSelected(campgroundChoice, campgroundManager);
						reservationMenu.askArrivalDate(campgroundManager);
						reservationMenu.askDepatureDate(campgroundManager);
						if(reservationMenu.tryDisplayAvailability(campgroundManager)==false) continue;
						reservationMenu.selectSiteToReserve(campgroundManager);
						reservationMenu.getReservationName(campgroundManager);
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
		
		CampgroundCLI application = new CampgroundCLI(dataSource, parkMenu, parkInfoMenu, campgroundMenu, reservationMenu, campgroundManager);
		
		
		application.run();
	}
}


//public class ProjectsCLI {
//	
//	private static final String MAIN_MENU_OPTION_EMPLOYEES = "Employees";
//	private static final String MAIN_MENU_OPTION_DEPARTMENTS = "Departments";
//	private static final String MAIN_MENU_OPTION_PROJECTS = "Projects";
//	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
//	private static final String[] MAIN_MENU_OPTIONS = new String[] { MAIN_MENU_OPTION_DEPARTMENTS, 
//																	 MAIN_MENU_OPTION_EMPLOYEES, 
//																	 MAIN_MENU_OPTION_PROJECTS, 
//																	 MAIN_MENU_OPTION_EXIT };
//
//	private static final String MENU_OPTION_RETURN_TO_MAIN = "Return to main menu";
//
//	private static final String DEPT_MENU_OPTION_ALL_DEPARTMENTS = "Show all departments";
//	private static final String DEPT_MENU_OPTION_SEARCH_BY_NAME = "Department search by name";
//	private static final String DEPT_MENU_OPTION_DEPARTMENT_EMPLOYEES = "Show department employees";
//	private static final String DEPT_MENU_OPTION_ADD_DEPARTMENT = "Add a new department";
//	private static final String DEPT_MENU_OPTION_UPDATE_NAME = "Update department name";
//	private static final String[] DEPARTMENT_MENU_OPTIONS = new String[] { DEPT_MENU_OPTION_ALL_DEPARTMENTS,
//																		   DEPT_MENU_OPTION_SEARCH_BY_NAME,
//																		   DEPT_MENU_OPTION_DEPARTMENT_EMPLOYEES,
//																		   DEPT_MENU_OPTION_ADD_DEPARTMENT,
//																		   DEPT_MENU_OPTION_UPDATE_NAME,
//																		   MENU_OPTION_RETURN_TO_MAIN};
//	
//	private static final String EMPL_MENU_OPTION_ALL_EMPLOYEES = "Show all employees";
//	private static final String EMPL_MENU_OPTION_SEARCH_BY_NAME = "Employee search by name";
//	private static final String EMPL_MENU_OPTION_EMPLOYEES_NO_PROJECTS = "Show employees without projects";
//	private static final String EMPL_MENU_OPTION_CHANGE_DEPARTMENT = "Change employee's department";
//	private static final String[] EMPL_MENU_OPTIONS = new String[] { EMPL_MENU_OPTION_ALL_EMPLOYEES,
//																	 EMPL_MENU_OPTION_SEARCH_BY_NAME,
//																	 EMPL_MENU_OPTION_EMPLOYEES_NO_PROJECTS,
//																	 EMPL_MENU_OPTION_CHANGE_DEPARTMENT,
//																	 MENU_OPTION_RETURN_TO_MAIN};
//	
//	private static final String PROJ_MENU_OPTION_ACTIVE_PROJECTS = "Show active projects";
//	private static final String PROJ_MENU_OPTION_PROJECT_EMPLOYEES = "Show project employees";
//	private static final String PROJ_MENU_OPTION_ASSIGN_EMPLOYEE_TO_PROJECT = "Assign an employee to a project";
//	private static final String PROJ_MENU_OPTION_REMOVE_EMPLOYEE_FROM_PROJECT = "Remove employee from project";
//	private static final String[] PROJ_MENU_OPTIONS = new String[] { PROJ_MENU_OPTION_ACTIVE_PROJECTS,
//																	 PROJ_MENU_OPTION_PROJECT_EMPLOYEES,
//																	 PROJ_MENU_OPTION_ASSIGN_EMPLOYEE_TO_PROJECT,
//																	 PROJ_MENU_OPTION_REMOVE_EMPLOYEE_FROM_PROJECT,
//																	 MENU_OPTION_RETURN_TO_MAIN };
//	
//	private Menu menu;
//	private DepartmentDAO departmentDAO;
//	private EmployeeDAO employeeDAO;
//	private ProjectDAO projectDAO;
//	
//	public static void main(String[] args) {
//		ProjectsCLI application = new ProjectsCLI();
//		application.run();
//	}
//	
//	public ProjectsCLI() {
//		this.menu = new Menu(System.in, System.out);
//		
//		BasicDataSource dataSource = new BasicDataSource();
//		dataSource.setUrl("jdbc:postgresql://localhost:5432/projectInfo");
//		dataSource.setUsername("postgres");
//		dataSource.setPassword("postgres1");
//		
//		departmentDAO = new JDBCDepartmentDAO(dataSource);
//		employeeDAO = new JDBCEmployeeDAO(dataSource);
//		projectDAO = new JDBCProjectDAO(dataSource);
//	}