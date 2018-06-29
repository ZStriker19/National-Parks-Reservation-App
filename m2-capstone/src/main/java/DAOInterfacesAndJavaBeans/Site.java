package DAOInterfacesAndJavaBeans;

public class Site {
	private Long site_id;
	private int campground_id;
	private int site_number;
	private int max_occupancy;
	private boolean accessible;
	private int max_rv_length;
	private boolean utilities;
	
	
	public Long getSite_id() {
		return site_id;
	}
	public void setSite_id(Long site_id) {
		this.site_id = site_id;
	}
	public int getCampground_id() {
		return campground_id;
	}
	public void setCampground_id(int campground_id) {
		this.campground_id = campground_id;
	}
	public int getSite_number() {
		return site_number;
	}
	public void setSite_number(int site_number) {
		this.site_number = site_number;
	}
	public int getMax_occupancy() {
		return max_occupancy;
	}
	public void setMax_occupancy(int max_occupancy) {
		this.max_occupancy = max_occupancy;
	}
	public boolean isAccessible() {
		return accessible;
	}
	public void setAccessible(boolean accessible) {
		this.accessible = accessible;
	}
	public int getMax_rv_length() {
		return max_rv_length;
	}
	public void setMax_rv_length(int max_rv_length) {
		this.max_rv_length = max_rv_length;
	}
	public boolean isUtilities() {
		return utilities;
	}
	public void setUtilities(boolean utilities) {
		this.utilities = utilities;
	}
	
}
