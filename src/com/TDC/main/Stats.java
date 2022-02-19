package com.TDC.main;

public class Stats {
	private double academics, mentalHealth, financials;
	private int month;
	
	public Stats(double academics, double mentalHealth, double financials, int month) {
		this.academics = academics;
		this.mentalHealth = mentalHealth;
		this.financials = financials;
		this.month = month;
	}

	public double getAcademics() {
		return academics;
	}

	public void setAcademics(double academics) {
		this.academics = academics;
	}

	public double getMentalHealth() {
		return mentalHealth;
	}

	public void setMentalHealth(double mentalHealth) {
		this.mentalHealth = mentalHealth;
	}

	public double getFinancials() {
		return financials;
	}

	public void setFinancials(double financials) {
		this.financials = financials;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}
	
	
}
