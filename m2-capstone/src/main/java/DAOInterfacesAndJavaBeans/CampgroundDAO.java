package DAOInterfacesAndJavaBeans;

import java.util.List;

public interface CampgroundDAO {
	
	public List<Campground> getAllCampgrounds();
	
	
	public List<Campground> getCampgroundForDates();

}
