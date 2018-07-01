package JDBCs;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import DAOInterfacesAndJavaBeans.Reservation;
import DAOInterfacesAndJavaBeans.ReservationDAO;
import DAOInterfacesAndJavaBeans.Site;



public class JDBCReservationDAO implements ReservationDAO{

	private JdbcTemplate jdbcTemplate;
	private DataSource dataSource;
	public JDBCReservationDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.dataSource = dataSource;
	}
	
	@Override
	public List<Reservation> getAllReservations() {
		String ReservationSQL = "SELECT * FROM Reservation";
		ArrayList<Reservation> reservationList = new ArrayList<>();
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(ReservationSQL);
		while(results.next()) {
			Reservation reservation = mapRowToReservation(results);
			reservationList.add(reservation);
		}
		return reservationList;
	}


	@Override
	public long createReservation(Reservation newReservation) {
		Connection con = null;
		Statement stmt;
		long reservation_id = 0;
		String ReservationSQL = "INSERT INTO reservation(from_date, to_date, site_id, name) VALUES (?,?,?,?)";
	    jdbcTemplate.update(ReservationSQL, newReservation.getFrom_date(), newReservation.getTo_date(), newReservation.getSite_id() +1, newReservation.getName());
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

		return reservation_id;
	}

	
	private Reservation mapRowToReservation(SqlRowSet results) {
		Reservation theReservation = new Reservation();
		
		theReservation.setReservation_id(results.getLong("reservation_id"));
		theReservation.setSite_id(results.getInt("site_id"));
		theReservation.setName(results.getString("name"));
		theReservation.setFrom_date(results.getDate("from_date"));
		theReservation.setTo_date(results.getDate("to_date"));
		theReservation.setCreate_date(results.getDate("create_date"));
		
		return theReservation;
	}
	
	private long getNextReservationId() {
        SqlRowSet nextIdResult = jdbcTemplate.queryForRowSet("SELECT nextval(pg_get_serial_sequence('reservation', 'reservation_id'))");
        if(nextIdResult.next()) {
            return nextIdResult.getLong(1);
        } else {
            throw new RuntimeException("Something went wrong while getting an id for the new reservation");
        }
    }
}
