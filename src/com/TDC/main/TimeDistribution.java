package com.TDC.main;

public class TimeDistribution {
	private final int TOTAL = 100;
	private int sleep, school, relax, work;
	
	public TimeDistribution(double[] percents) {
		if (percents.length != 3) throw new IllegalArgumentException();
		this.sleep = (int) (TOTAL * percents[0]);
		this.school = (int) (TOTAL * (percents[1] - percents[0]));
		this.relax = (int) (TOTAL * (percents[2] - percents[1]));
		this.work = (int) (TOTAL - TOTAL * percents[2]);
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
	
	@Override
	public String toString() {
		return String.format("Sleep: %d, School: %d, Relax: %d, Work: %d", sleep, school, relax, work);
	}
}
