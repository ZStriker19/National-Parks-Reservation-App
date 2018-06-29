package DAOInterfacesAndJavaBeans;

import java.math.BigDecimal;

public class Campground {
	private long campground_id;
	private long park_id;
	private String name;
	private int open_from_mm;
	private int open_to_mm;
	private BigDecimal  daily_fee;
	
	
	public long getCampground_id() {
		return campground_id;
	}
	public void setCampground_id(long campground_id) {
		this.campground_id = campground_id;
	}
	public long getPark_id() {
		return park_id;
	}
	public void setPark_id(long park_id) {
		this.park_id = park_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getOpen_from_mm() {
		return open_from_mm;
	}
	public void setOpen_from_mm(int open_from_mm) {
		this.open_from_mm = open_from_mm;
	}
	public int getOpen_to_mm() {
		return open_to_mm;
	}
	public void setOpen_to_mm(int open_to_mm) {
		this.open_to_mm = open_to_mm;
	}
	public BigDecimal getDaily_fee() {
		return daily_fee;
	}
	public void setDaily_fee(BigDecimal daily_fee) {
		this.daily_fee = daily_fee;
	}
	
	
}
