package com.TDC.skills;

public enum Skills {
	HI(1, 1, 1, "hi");
	
	private Skill skill;
	
	private Skills(double academic, double mentalHealth, double financials, String description) {
		this.skill = new Skill(academic, mentalHealth, financials, toTitle(this.toString()), description);
	}
	
	private String toTitle(String s) {
		String res = "";
		
		for (String w : s.split(" "))
			res += w.substring(0, 1).toUpperCase() + w.substring(1, w.length());
		
		return res;
	}
	
	public Skill getSkill() {
		return skill;
	}
}
