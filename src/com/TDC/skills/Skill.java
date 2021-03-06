package com.TDC.skills;

import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.text.Font;

public class Skill extends Button {
	private double academic, mentalHealth, financials;
	
	public Skill(double academic, double mentalHealth, double financials, String name, String description) {
		super(name);
		this.academic = academic;
		this.mentalHealth = mentalHealth;
		this.financials = financials;
		this.setTooltip(new Tooltip(description));
		this.setFont(Font.font("arial", 28));
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
