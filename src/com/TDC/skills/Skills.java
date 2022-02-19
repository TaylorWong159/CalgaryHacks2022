package com.TDC.skills;

public enum Skills {
	LUCKY(true, 0.05, 0.05, 50, "Slightly improves schooling, mental health and financial situation"), 
	INTELLIGENCE(true, 0.2, 0, 0, "Drastically improves schooling"), 
	SOCIAL_SKILLS(true, 0, 0.2, 0, "Drastically improves mental health"), 
	FINANCIAL_LITERACY(true, 0, 0, 200, "Drastically improves financial literacy"), 
	GOOD_STUDY_HABBITS(true, 0.1, 0.1, 0, "Improves schooling and mental health"),
	LIGHT_SLEEPER(true, 0.1, 0.1, 0, "Improves schooling and mental health"),
	DISTRACTABLE(false, -0.1, -0.1, 0, "Reduces schooling and mental health"), 
	INSOMNIAC(false, -0.1, -0.1, 0, "Reduces schooling and mental health"), 
	POOR_HEALTH(false, -0.05, -0.05, -50, "Slightly reduces schooling, mental health and financial situation"), 
	UNLUCKY(false, -0.05, -0.05, -50, "Slightly reduces schooling, mental health and financial situation");
	
	
	private Skill skill;
	private boolean positive;
	
	private Skills(boolean positive, double academic, double mentalHealth, double financials, String description) {
		this.positive = positive;
		this.skill = new Skill(academic, mentalHealth, financials, toTitle(this.toString()), description);
	}
	
	private String toTitle(String s) {
		String res = "";
		
		for (String w : s.split("_"))
			res += w.substring(0, 1).toUpperCase() + w.substring(1, w.length()).toLowerCase();
		
		return res;
	}
	
	public Skill getSkill() {
		return skill;
	}
	
	public boolean isPositive() {
		return positive;
	}
}
