package DAOInterfacesAndJavaBeans;
import java.util.Date;
import java.util.List;

public interface ReservationDAO {

	public List<Reservation> getAllReservations();
	
	public long createReservation(Reservation newReservation);
}
