package com.TDC.main;

import java.util.ArrayList;
import java.util.List;

public class Stats {
	private double academics, mentalHealth, financials;
	private TimeDistribution timeDist;
	private int month;
	private List<Skill> skills = new ArrayList<Skill>();
	
	public Stats(double mentalHealth, double financials, int month, TimeDistribution timeDist, double monthlyCost) {
		this.academics = 0.5;
		this.mentalHealth = mentalHealth;
		this.financials = financials;
		this.timeDist = timeDist;
		this.month = month;
		this.monthlyCost = monthlyCost;
	}

	public void update() {
		double incAcademics = 0, incMentalHealth = 0, incFinancials = 0;
		for (Skill skill : skills) {
			incAcademics += skill.getAcademic();
			incMentalHealth += skill.getMentalHealth();
			incFinancials += skill.getFinancials();
		}

		academics = 0.3 * ((timeDist.getSchool() + 0.5 * timeDist.getSleep()) * (1.5 / 100) + incAcademics) + 0.7 * academics;
		mentalHealth = 0.3 * ((timeDist.getRelax() + 0.5 * timeDist.getSleep()) * (1.5 / 100) + incMentalHealth) + 0.7 * mentalHealth;
		financials = financials + timeDist.getWork() * 105 - monthlyCost
		
	}

	public double getAcademics() {
		return academics;
	}

	public double getMentalHealth() {
		return mentalHealth;
	}

	public double getFinancials() {
		return financials;
	}

	public int getMonth() {
		return month;
	}
	
	
}
