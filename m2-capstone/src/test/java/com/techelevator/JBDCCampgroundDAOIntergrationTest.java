package com.techelevator;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;


import DAOInterfacesAndJavaBeans.Campground;
import JDBCs.JDBCCampgroundDAO;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;


public class JBDCCampgroundDAOIntergrationTest {

	private static final long campground_id = (long) 8;
	
	private static SingleConnectionDataSource dataSource;
	private JDBCCampgroundDAO dao;
	
	
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
		String sqlInsertCampground = "INSERT INTO campground (campground_id, park_id, name, open_from_mm, open_to_mm, daily_fee) " +
								"VALUES ('8', '3', 'Katie Park', '05', '08', '$10')";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.update(sqlInsertCampground);//, departmentId);
		dao = new JDBCCampgroundDAO(dataSource);
	}

	@After
	public void rollback() throws SQLException {
		dataSource.getConnection().rollback();
	}
	
	@Test
	public void testGetAllCampgrounds() {
		List<Campground> results = dao.getAllCampgrounds(1);
		Assert.assertNotNull(results);
		Assert.assertEquals(3, results.size());
	}
	
	@Test //COME BACK TO THIS ONE!
	public void testGetAllCampgroundForDates() {
		
	}
	
	private Campground getCampground(Long campground_id, Long park_id, String name, int open_from_mm, int open_to_mm, BigDecimal daily_fee) {
		Campground theCampground = new Campground();
		theCampground.setCampground_id(campground_id);
		theCampground.setPark_id(park_id);
		theCampground.setName(name);
		theCampground.setOpen_from_mm(open_from_mm);
		theCampground.setOpen_to_mm(open_to_mm);
		theCampground.setDaily_fee(daily_fee);
		return theCampground;
	}

	private void assertCampgroundAreEqual(Campground expected, Campground actual) {
		assertEquals(expected.getCampground_id(), actual.getCampground_id());
		assertEquals(expected.getPark_id(), actual.getPark_id());
		assertEquals(expected.getName(), actual.getName());
		assertEquals(expected.getOpen_from_mm(), actual.getOpen_from_mm());
		assertEquals(expected.getOpen_to_mm(), actual.getOpen_to_mm());
		assertEquals(expected.getDaily_fee(), actual.getDaily_fee());
	}


}
