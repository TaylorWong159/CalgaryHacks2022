package com.TDC.main;

public enum Location {
	DOMESTIC(250, 750), INTERNATIONAL(750, 2000);
	
	private int monthly, semester;
	
	private Location(int monthly, int semester) {
		this.monthly = monthly;
		this.semester = semester;
	}
	
	public int getMonthyCost() {
		return monthly;
	}
	
	public int getTuitionPerCourse() {
		return semester;
	}
}
