package JDBCs;
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
	
	public JDBCReservationDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
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
		
		String ReservationSQL = "INSERT INTO reservation(reservation_id, from_date, to_date, site_id, name) VALUES (?,?,?,?,?) ";
		newReservation.setReservation_id(getNextReservationId());
		 jdbcTemplate.update(ReservationSQL, newReservation.getReservation_id(), newReservation.getFrom_date(), newReservation.getTo_date(), newReservation.getSite_id(), newReservation.getName());

		return newReservation.getReservation_id();
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
        SqlRowSet nextIdResult = jdbcTemplate.queryForRowSet("SELECT nextval('seq_reservation_id')");
        if(nextIdResult.next()) {
            return nextIdResult.getLong(1);
        } else {
            throw new RuntimeException("Something went wrong while getting an id for the new reservation");
        }
    }
}
