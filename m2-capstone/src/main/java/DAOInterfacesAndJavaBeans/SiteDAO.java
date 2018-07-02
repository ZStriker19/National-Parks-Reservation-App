package DAOInterfacesAndJavaBeans;
import java.util.Date;
import java.util.List;

public interface SiteDAO {

	public List<Site> getAllCampgroundSites(int campgroundId);
	
	public List<Site>getSitesByID();
	
	public List<Site> getAvailSites(String from_date, String to_date, int campground_id);
}
