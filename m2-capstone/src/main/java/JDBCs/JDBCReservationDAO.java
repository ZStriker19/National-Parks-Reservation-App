package JDBCs;
import java.util.ArrayList;
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
	public List<Reservation> getReservationById() {
		
		return null;
	}

	@Override
	public List<Reservation> getReservationByDate() {
		// TODO Auto-generated method stub
		return null;
	}
	
	private Reservation mapRowToReservation(SqlRowSet results) {
		Reservation theReservation = new Reservation();
		
		theReservation.setReservation_id(results.getLong("reservation_id"));
		theReservation.setSite_it(results.getInt("site_id"));
		theReservation.setName(results.getString("name"));
		theReservation.setFrom_date(results.getDate("from_date"));
		theReservation.setTo_date(results.getDate("to_date"));
		theReservation.setCreate_date(results.getDate("create_date"));
		
		return theReservation;
	}

}
