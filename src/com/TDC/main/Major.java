package com.TDC.main;

public enum Major {
	COMPSCI(40), ENGINEERING(45);
	
	private int courses;
	
	private Major(int courses) {
		this.courses = courses;
	}
	
	public int getCourses() {
		return courses;
	}
	
	public int getRemainingCourses(int finished) {
		return courses - finished;
	}
}
