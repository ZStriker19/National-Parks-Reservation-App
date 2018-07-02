package JDBCs;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import DAOInterfacesAndJavaBeans.Campground;
import DAOInterfacesAndJavaBeans.CampgroundDAO;
import DAOInterfacesAndJavaBeans.Park;

public class JDBCCampgroundDAO implements CampgroundDAO {
	private JdbcTemplate jdbcTemplate;

	public JDBCCampgroundDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public List<Campground> getAllCampgrounds(int park_id) {
		List<Campground>  campgrounds = new ArrayList<Campground>();
		String sqlGetAllCampgrounds = "SELECT * FROM campground " +
								"WHERE campground.park_id = ?" ;
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetAllCampgrounds, park_id+1);
		while(results.next()) {
			Campground campground = mapRowToCampground(results);
			campgrounds.add(campground);
		}
		
		return campgrounds;
	}

	
	private Campground mapRowToCampground(SqlRowSet results) {
		Campground campground = new Campground();
		campground.setPark_id(results.getLong("park_id"));
		campground.setCampground_id(results.getLong("campground_id"));
		campground.setDaily_fee(results.getBigDecimal("daily_fee"));
		campground.setName(results.getString("name"));
		campground.setOpen_from_mm(Integer.parseInt(results.getString("open_from_mm")));
		campground.setOpen_to_mm(Integer.parseInt(results.getString("open_to_mm")));
		return campground;
	}

}
