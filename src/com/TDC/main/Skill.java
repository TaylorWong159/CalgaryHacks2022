package com.TDC.main;

public class Skill {
	private double academic, mentalHealth, financials;
	
	public Skill(double academic, double mentalHealth, double finanicals) {
		this.academic = academic;
		this.mentalHealth = mentalHealth;
		this.financials = financials;
	}

	public double getAcademic() {
		return academic;
	}

	public double getMentalHealth() {
		return mentalHealth;
	}

	public double getFinancials() {
		return financials;
	}
	
	
}
