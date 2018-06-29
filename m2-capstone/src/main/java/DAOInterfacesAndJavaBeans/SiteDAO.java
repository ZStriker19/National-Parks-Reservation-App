package DAOInterfacesAndJavaBeans;
import java.util.List;

public interface SiteDAO {

	public List<Site> getAllCampgroundSites();
	
	public List<Site>getSitesByID();
	
	
}
