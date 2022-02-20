package com.TDC.main;

import java.util.ArrayList;
import java.util.List;

import com.TDC.skills.Skill;

public class Stats {
	private double academics, mentalHealth, financials;
	private Config config;
	private TimeDistribution timeDist;
	private int month;
	private List<Skill> skills = new ArrayList<Skill>();

	public Stats(Config config) {
		this.config = config;
		this.academics = 0.7;
		this.mentalHealth = 1;
		this.financials = 10000;
		this.month = 0;
	}

	public void update() {
		double incAcademics = 0, incMentalHealth = 0, incFinancials = 0;
		for (Skill skill : skills) {
			incAcademics += skill.getAcademic();
			incMentalHealth += skill.getMentalHealth();
			incFinancials += skill.getFinancials();
		}
		
		// MATHS
		academics = 0.3 * ((timeDist.getSchool() + 0.5 * timeDist.getSleep()) * (1.5 / 100) + incAcademics) + 0.7 * academics;
		mentalHealth = 0.3 * ((timeDist.getRelax() + 0.5 * timeDist.getSleep()) * (1.5 / 100) + incMentalHealth) + 0.7 * mentalHealth;
		financials = financials + timeDist.getWork() * 60 - config.getLocation().getMonthyCost() + incFinancials;
		
		academics = Math.min(1, academics);
		mentalHealth = Math.min(1, mentalHealth);
		month++;
	}

	public TimeDistribution getTimeDist() {
		return timeDist;
	}
	
	public void subtractTuition(int courses) {
		this.financials -= courses * config.getLocation().getTuitionPerCourse();
	}

	public void setTimeDist(TimeDistribution timeDist) {
		this.timeDist = timeDist;
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
