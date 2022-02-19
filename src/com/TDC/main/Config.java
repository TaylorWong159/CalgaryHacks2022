package com.TDC.main;

public class Config {
	private Housing housing;
	private Location location;
	private Major major;
	
	public Config() {
		housing = null;
		location = null;
		major = null;
	}
	
	public boolean isComplete() {
		if (housing == null) return false;
		if (location == null) return false;
		if (major == null) return false;
		return true;
	}
	
	public Housing getHousing() {
		return housing;
	}
	
	public void setHousing(Housing housing) {
		this.housing = housing;
	}
	
	public Location getLocation() {
		return location;
	}
	
	public void setLocation(Location location) {
		this.location = location;
	}
	
	public Major getMajor() {
		return major;
	}
	
	public void setMajor(Major major) {
		this.major = major;
	}
	
	@Override
	public String toString() {
		return "Housing: " + housing + "\nLocation: " + location + "\nMajor: " + major;
	}
	
	
}
