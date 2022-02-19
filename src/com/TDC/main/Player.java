package com.TDC.main;

import java.util.HashSet;
import java.util.Set;

import com.TDC.skills.Skills;

public class Player {
	private Stats stats;
	private Set<Skills> skills;
	
	public Player(Stats stats) {
		this.stats = stats;
		this.skills = new HashSet<Skills>();
	}
	
	public Stats getStats() {
		return stats;
	}
	
	public Set<Skills> getSkills() {
		return skills;
	}
	
	public boolean hasSkill(Skills skill) {
		return skills.contains(skill);
	}
}
