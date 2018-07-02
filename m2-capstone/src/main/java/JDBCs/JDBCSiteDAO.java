package JDBCs;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import DAOInterfacesAndJavaBeans.Reservation;
import DAOInterfacesAndJavaBeans.Site;
import DAOInterfacesAndJavaBeans.SiteDAO;


public class JDBCSiteDAO implements SiteDAO {

	private JdbcTemplate jdbcTemplate;
	
	public JDBCSiteDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	//Need to only Select sites at the with the campground ID entered.
	@Override
	public List <Site> getAllCampgroundSites(int campgroundId) {
		String SiteSQL = "SELECT * FROM Site Where campground_id = ? LIMIT 5";
		ArrayList<Site> siteList = new ArrayList<>();
		
		SqlRowSet results = jdbcTemplate.queryForRowSet(SiteSQL, campgroundId + 1);
		while(results.next()) {
			Site site = mapRowToSite(results);
			siteList.add(site);
		}
		return siteList;
	}
	
	
	
	
	public List<Site> getAvailSites(String arrivalDate, String departureDate, long campground_id) {
		int openFromMonth = 0;
		int openToMonth = 0;
		
		ArrayList<Site> siteList = new ArrayList<>();
		String sqlCheckCampMonths = "SELECT open_from_mm, open_to_mm FROM campground WHERE campground_id = ?";
		SqlRowSet resultMonths = jdbcTemplate.queryForRowSet(sqlCheckCampMonths, campground_id);
		
		if (resultMonths.next()) {
			openFromMonth = Integer.parseInt(resultMonths.getString("open_from_mm"));
			openToMonth = Integer.parseInt(resultMonths.getString("open_to_mm"));
		}
		//Need to change this to get to an int that it's happy with. Because it doesn't like parsing 03
		int to_date_int = 0;
		int from_date_int = 0;
		String toSTR =  arrivalDate.substring(0,2);
		String fromSTR = departureDate.substring(0,2);
		if (toSTR.substring(0,1).equals("0")) {
			to_date_int = Integer.parseInt(toSTR.substring(1,2));
		} else {
			to_date_int = Integer.parseInt(arrivalDate.substring(0, 2));
		}
		
		if (fromSTR.substring(0,1).equals("0")) {
			from_date_int = Integer.parseInt(fromSTR.substring(1,2));
		} else {
			from_date_int = Integer.parseInt(arrivalDate.substring(0, 2));
		}

		if (openFromMonth <= to_date_int && openToMonth >= to_date_int && openFromMonth <= from_date_int && openToMonth >= from_date_int ) {
			String sqlIsSiteAvail = "SELECT * " +
					" FROM site JOIN campground ON site.campground_id = campground.campground_id WHERE site.campground_id = ? "
					+ "AND site.site_id" +
					" NOT IN " +
					"(SELECT site.site_id FROM site " +
					"JOIN reservation ON reservation.site_id = site.site_id" + 
					" WHERE ((to_date(?, 'YYYY/MM/DD')) <= reservation.to_date AND (to_date(?, 'YYYY/MM/DD')) >= reservation.from_date)) " +
					 " ORDER BY site.site_number LIMIT 5";
					DateFormat format = new SimpleDateFormat("MM/dd/yyyy");
					String newArrString = "";
					String newDepString = "";
					
					try {
						SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
						Date d = sdf.parse(arrivalDate);
						Date ds = sdf.parse(departureDate);
						sdf.applyPattern("yyyy/MM/dd");
						newArrString = sdf.format(d);
					    newDepString = sdf.format(ds);
					} catch (ParseException e) {
						e.printStackTrace();
					}
					System.out.println(" camp " + campground_id + " dep: " + newDepString + " arr " + newArrString);
					
					SqlRowSet results = jdbcTemplate.queryForRowSet(sqlIsSiteAvail, campground_id, newArrString, newDepString);
					while(results.next()) {
						Site site = mapRowToSite(results);
						siteList.add(site);
					}
			
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
