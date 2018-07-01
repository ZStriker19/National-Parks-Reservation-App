package DAOInterfacesAndJavaBeans;
import java.util.List;

public interface SiteDAO {

	public List<Site> getAllCampgroundSites(int campgroundId);
	
	public List<Site>getSitesByID();
	
	
}
