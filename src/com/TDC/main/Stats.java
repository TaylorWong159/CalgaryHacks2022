package com.TDC.main;

import java.util.ArrayList;
import java.util.List;

public class Stats {
	private double academics, mentalHealth, financials;
	private TimeDistribution timeDist;
	private int month;
	private List<Skill> skills = new ArrayList<Skill>();
	
	public Stats(double mentalHealth, double financials, int month, TimeDistribution timeDist) {
		this.academics = 0.5;
		this.mentalHealth = mentalHealth;
		this.financials = financials;
		this.timeDist = timeDist;
		this.month = month;
	}

	public void update() {
		double res = 0.3 * ((buffs + 0.5 * timeDist.getSleep()) * (target / 100) + 0.7 * stat;
		
		double incAcademics = 0, incMentalHealth = 0, incFinancials = 0;
		for (Skill skill : skills) {
			incAcademics += skill.getAcademic();
			incMentalHealth += skill.getMentalHealth();
			incFinancials += skill.getFinancials();
		}
		
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
