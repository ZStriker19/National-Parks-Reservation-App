package JDBCs;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import DAOInterfacesAndJavaBeans.Site;
import DAOInterfacesAndJavaBeans.SiteDAO;


public class JDBCSiteDAO implements SiteDAO {

	private JdbcTemplate jdbcTemplate;
	
	public JDBCSiteDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	@Override
	public List <Site> getAllCampgroundSites() {
		String SiteSQL = "SELECT * FROM Site";
		ArrayList<Site> siteList = new ArrayList<>();
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(SiteSQL);
		while(results.next()) {
			Site site = mapRowToSite(results);
			siteList.add(site);
		}
		return siteList;
	}

	@Override
	public List<Site> getSitesByID() {
		String SiteSQL = "SELECT site_id FROM Site";
		ArrayList<Site> siteList = new ArrayList<>();
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(SiteSQL);
		while(results.next()) {
			Site site = mapRowToSite(results);
			siteList.add(site);
		}
		
		return siteList;
	}
	
	
	private Site mapRowToSite(SqlRowSet results) {
		Site theSite = new Site();
		
		theSite.setSite_id(results.getLong("site_id"));
		theSite.setCampground_id(results.getInt("campground_id"));
		theSite.setSite_number(results.getInt("site_number"));
		theSite.setMax_occupancy(results.getInt("max_occupancy"));
		theSite.setAccessible(results.getBoolean("accessible"));
		theSite.setMax_rv_length(results.getInt("max_rv_length"));
		theSite.setUtilities(results.getBoolean("utilities"));
		
		return theSite;
	}
}
