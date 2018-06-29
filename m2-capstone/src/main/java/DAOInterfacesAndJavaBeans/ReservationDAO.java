package DAOInterfacesAndJavaBeans;
import java.util.List;

public interface ReservationDAO {

	public List<Reservation> getAllReservations();
	
	public List<Reservation> getReservationById();
	
	public List<Reservation> getReservationByDate();
}
