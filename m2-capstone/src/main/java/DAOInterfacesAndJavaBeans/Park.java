package DAOInterfacesAndJavaBeans;

import java.util.Date;

public class Park {
	private Long park_id;
	private String name;
	private String location;
	private Date establish_date; 
	private int area;
	private int visitors;
	private String description;
	
	
	public Long getPark_id() {
		return park_id;
	}
	public String getName() {
		return name;
	}
	public String getLocation() {
		return location;
	}
	public Date getEstablish_date() {
		return establish_date;
	}
	public int getArea() {
		return area;
	}
	public int getVisitors() {
		return visitors;
	}
	public String getDescription() {
		return description;
	}
	public void setPark_id(Long park_id) {
		this.park_id = park_id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public void setEstablish_date(Date established_date) {
		this.establish_date = established_date;
	}
	public void setArea(int area) {
		this.area = area;
	}
	public void setVisitors(int visitors) {
		this.visitors = visitors;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	

}
