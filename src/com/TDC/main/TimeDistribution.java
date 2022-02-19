package com.TDC.main;

public class TimeDistribution {
	private final int TOTAL = 100;
	private int sleep, school, relax, work;
	
	public TimeDistribution(int sleep, int school, int relax, int work) {
		this.sleep = sleep;
		this.school = school;
		this.relax = relax;
		this.work = work;
	}

	public int getSleep() {
		return sleep;
	}

	public void setSleep(int sleep) {
		this.sleep = sleep;
	}

	public int getSchool() {
		return school;
	}

	public void setSchool(int school) {
		this.school = school;
	}

	public int getRelax() {
		return relax;
	}

	public void setRelax(int relax) {
		this.relax = relax;
	}

	public int getWork() {
		return work;
	}

	public void setWork(int work) {
		this.work = work;
	}

	public int getTOTAL() {
		return TOTAL;
	}
	
	
	
}
