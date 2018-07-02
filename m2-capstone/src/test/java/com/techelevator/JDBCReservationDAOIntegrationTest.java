package com.techelevator;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

private static final long reservation_id = (long) 623;
    
    private static SingleConnectionDataSource dataSource;
    private JDBCReservationDAO dao;
    private JDBCCampgroundDAO dao_campground;
    
    
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
        String sqlInsertReservation = "INSERT INTO reservation (site_id, name, from_date, to_date, create_date) " +
                                "VALUES ('2', 'Reservation for Katie', '2018-06-01', '2018-06-10', '2018-05-01' )";
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update(sqlInsertReservation);
        dao = new JDBCReservationDAO(dataSource);
        dao_campground = new JDBCCampgroundDAO(dataSource);
    }

    @After
    public void rollback() throws SQLException {
        dataSource.getConnection().rollback();
    }
    
    
    @Test
    public void testGetAllReservations() {
        List<Reservation> results = dao.getAllReservations();
        Assert.assertNotNull(results);
        Assert.assertEquals(89, results.size());
    }
    

    @Test
    public void testCreateReservation() {
        
        Reservation theReservation = new Reservation();
  
        theReservation.setFrom_date("11/06/2018");
        theReservation.setTo_date("11/19/2018");
        theReservation.setName("Reservation for Katie");
        theReservation.setSite_id(47);

        Statement stmt;
        Connection con;
        long reservation_id = 0;
        String sqlGetGeneratedId = "SELECT currval('reservation_reservation_id_seq')";
    try {
        con = dataSource.getConnection();
       stmt = con.createStatement();
       ResultSet rs = stmt.executeQuery(sqlGetGeneratedId);
       if (rs.next()) {
           reservation_id = rs.getLong(1);
       }
   } catch (SQLException e) {
       e.printStackTrace();
   }
    Assert.assertEquals(reservation_id+1, dao.createReservation(theReservation));
}
private Reservation getReservation(Long reservation_id, int site_id, String name, Date from_date, Date to_date, Date create_date) {
    
    Reservation theReservation = new Reservation();
    theReservation.getReservation_id();
    theReservation.getSite_id();
    theReservation.getName();
    theReservation.getFrom_date();
    theReservation.getTo_date();
    theReservation.getCreate_date();
    return theReservation;
}

private void assertReservationAreEqual(Reservation expected, Reservation actual) {
    assertEquals(expected.getReservation_id(), actual.getReservation_id());
    assertEquals(expected.getSite_id(), actual.getSite_id());
    assertEquals(expected.getName(), actual.getName());
    assertEquals(expected.getFrom_date(), actual.getFrom_date());
    assertEquals(expected.getTo_date(), actual.getTo_date());
    assertEquals(expected.getCreate_date(), actual.getCreate_date());
}
}