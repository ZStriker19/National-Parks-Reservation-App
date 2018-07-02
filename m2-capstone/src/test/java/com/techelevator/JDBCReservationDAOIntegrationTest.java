package com.techelevator;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Test;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;


import DAOInterfacesAndJavaBeans.Campground;
import DAOInterfacesAndJavaBeans.Park;
import DAOInterfacesAndJavaBeans.Reservation;
import DAOInterfacesAndJavaBeans.Site;
import JDBCs.JDBCCampgroundDAO;
import JDBCs.JDBCParkDAO;
import JDBCs.JDBCReservationDAO;
import JDBCs.JDBCSiteDAO;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;

public class JDBCReservationDAOIntegrationTest {

private static final long reservation_id = (long) 45;
	
	private static SingleConnectionDataSource dataSource;
	private JDBCReservationDAO dao;
	
	
	@BeforeClass
	public static void setupDataSource() {
		dataSource = new SingleConnectionDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/campground");
		dataSource.setUsername("postgres");
		dataSource.setAutoCommit(false);
	}
	
	@AfterClass
	public static void closeDataSource() throws SQLException {
		dataSource.destroy();
	}
	
	@Before
	public void setup() {
		String sqlInsertReservation = "INSERT INTO reservation (reservation_id, site_id, name, from_date, to_date, create_date) " +
								"VALUES ('45', '2', 'Reservation for Katie', '2018-06-01', '2018-06-10', '2018-05-01' )";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.update(sqlInsertReservation);
		dao = new JDBCReservationDAO(dataSource);
	}

	@After
	public void rollback() throws SQLException {
		dataSource.getConnection().rollback();
	}
	
	
	@Test
	public void testGetAllReservations() {
		List<Reservation> results = dao.getAllReservations();
		Assert.assertNotNull(results);
		Assert.assertEquals(45, results.size());
	}
	
	@Test
	public void testCreateReservation() {
		
		Reservation theReservation;
		
		DateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
//		String createDateStr = theReservation.setCreate_date(5-1-2018);
//		String create_date_str = sdf.format(date);
		Date d = null;
		try {
			d = sdf.parse(theReservation.getCreate_date());
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		//long site_id = 22;
		theReservation.setFrom_date("2018-06-05");
		theReservation.setTo_date("2018-06-10");
		theReservation.setName("Reservation for Katie");
		theReservation.setSite_id(22);
		
		//theReservation = getReservation((long)45, 2, "Reservation for Katie", theReservation.setFrom_date("2018-06-05"), theReservation.setTo_date("2018-06-10"));
		//Department theDepartment = getDepartment(7, "Human Resources");
		List<Reservation> results = dao.getAllReservations();
		Assert.assertNotNull(results);
		Assert.assertEquals(45, results.size());
		assertReservationAreEqual(theReservation, results.get(index));
		//assertDeparmentAreEqual(theDepartment, results.get(6))
		
		Reservation addedReservation = getReservation();
		//Department addedDepartment = getDepartment(7, "Human Resources");
		
		Assert.assertEquals(expected, actual);
		//Assert.assertEquals("Human Resources", addedDepartment.getName());
		
	}
	
//	private Reservation getReservation(Long reservation_id, int site_id, String name, Date from_date, Date to_date, Date create_date) {
//		
//		Reservation theReservation = new Reservation();
//		theReservation.getReservation_id();
//		theReservation.getSite_id();
//		theReservation.getName();
//		theReservation.getFrom_date();
//		theReservation.getTo_date();
//		theReservation.getCreate_date();
//		return theReservation;
//	}

	private void assertReservationAreEqual(Reservation expected, Reservation actual) {
		assertEquals(expected.getReservation_id(), actual.getReservation_id());
		assertEquals(expected.getSite_id(), actual.getSite_id());
		assertEquals(expected.getName(), actual.getName());
		assertEquals(expected.getFrom_date(), actual.getFrom_date());
		assertEquals(expected.getTo_date(), actual.getTo_date());
		assertEquals(expected.getCreate_date(), actual.getCreate_date());
	}

}