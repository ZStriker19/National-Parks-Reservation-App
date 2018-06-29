package DAOInterfacesAndJavaBeans;

import java.util.List;

public interface CampgroundDAO {
	
	public List<Campground> getAllCampgrounds();
	
	
	public List<Campground> getCampgroundForDates(int open_from_mm, int open_to_mm);

}
