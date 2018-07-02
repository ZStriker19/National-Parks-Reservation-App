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
	
	


}
