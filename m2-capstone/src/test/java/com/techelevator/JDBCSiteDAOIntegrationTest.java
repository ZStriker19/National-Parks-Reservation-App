package com.techelevator;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import org.junit.Test;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;


import DAOInterfacesAndJavaBeans.Campground;
import DAOInterfacesAndJavaBeans.Park;
import DAOInterfacesAndJavaBeans.Site;
import JDBCs.JDBCCampgroundDAO;
import JDBCs.JDBCParkDAO;
import JDBCs.JDBCSiteDAO;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;

public class JDBCSiteDAOIntegrationTest {

private static final long site_id = (long) 1;
	
	private static SingleConnectionDataSource dataSource;
	private JDBCSiteDAO dao;
	
	
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
		String sqlInsertSite = "INSERT INTO site (site_id, campground_id, site_number, max_occupancy, accessible, max_rv_length, utilities) " +
								"VALUES ('623', '5', '2', '100', 'false', '0', 'false' )";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.update(sqlInsertSite);
		dao = new JDBCSiteDAO(dataSource);
	}

	@After
	public void rollback() throws SQLException {
		dataSource.getConnection().rollback();
	}
	
	@Test
	public void testGetAllCampgroundSites() {
		List<Site> results = dao.getAllCampgroundSites(5); //campgroundID = 5
		Assert.assertNotNull(results);
		Assert.assertEquals(1, results.size());
	}
	
	@Test //COME BACK TO THIS ONE!!!
	public void testGetSitesById() {
		//Site theSite = null;
		//theSite.setCampground_id(5);
		List<Site> results = dao.getAllCampgroundSites(5);
		//theSite.getSite_id();
		Assert.assertNotNull(results);
		Assert.assertEquals(1, results.size());
		
//		
//		Long test_siteID = (long) 285;
//		List<Site> results = dao.getSitesByID();
		
		
		Assert.assertNotNull(results);
		Assert.assertEquals(285, results.size());	
//		Site theSite = getSite((long)1,1,1, 6, false, 0, false);
//		
//		dao.getSitesByID();
	}
	
	private Site getSite(Long site_id, int campground_id, int site_number, int max_occupancy, boolean accessible, int max_rv_length, boolean utilities) {
		Site theSite = new Site();
		theSite.setSite_id(site_id);
		theSite.setCampground_id(campground_id);
		theSite.setSite_number(site_number);
		theSite.setMax_occupancy(max_occupancy);
		theSite.setAccessible(accessible);
		theSite.setMax_rv_length(max_rv_length);
		theSite.setUtilities(utilities);
		return theSite;
	}

	private void assertSiteAreEqual(Site expected, Site actual) {
		assertEquals(expected.getSite_id(), actual.getSite_id());
		assertEquals(expected.getCampground_id(), actual.getCampground_id());
		assertEquals(expected.getSite_number(), actual.getSite_number());
		assertEquals(expected.getMax_occupancy(), actual.getMax_occupancy());
		assertEquals(expected.isAccessible(), actual.isAccessible());
		assertEquals(expected.getMax_rv_length(), actual.getMax_rv_length());
		assertEquals(expected.isUtilities(), actual.isUtilities());
	}

}
