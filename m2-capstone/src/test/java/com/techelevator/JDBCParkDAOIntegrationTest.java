package com.techelevator;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import org.junit.Test;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;


import DAOInterfacesAndJavaBeans.Campground;
import DAOInterfacesAndJavaBeans.Park;
import JDBCs.JDBCCampgroundDAO;
import JDBCs.JDBCParkDAO;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;


public class JDBCParkDAOIntegrationTest {

private static final long park_id = (long) 4;
	
	private static SingleConnectionDataSource dataSource;
	private JDBCParkDAO dao;
	
	
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
		String sqlInsertPark = "INSERT INTO park (name, location, establish_date, area, visitors, description) " +
								"VALUES ('New Park', 'Ohio', '2018-06-01', '32000', '1000', 'This is a brand new part created in 2018.')";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.update(sqlInsertPark);//, departmentId);
		dao = new JDBCParkDAO(dataSource);
	}

	@After
	public void rollback() throws SQLException {
		dataSource.getConnection().rollback();
	}
	
	
	@Test
	public void testGetAllParks() {
		List<Park> results = dao.getAllParks();
		Assert.assertNotNull(results);
		Assert.assertEquals(4, results.size());
	}

}
