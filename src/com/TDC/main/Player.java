package com.TDC.main;

import java.util.HashSet;
import java.util.Set;

import com.TDC.skills.Skills;

public class Player {
	private Stats stats;
	private Set<Skills> skills;
	private Config config;
	private int completeCourses, currentCourses;
	
	public Player(Stats stats, Config config) {
		this.stats = stats;
		this.skills = new HashSet<Skills>();
		this.config = config;
		this.completeCourses = 0;
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
	
	public int getCurrentCourses() {
		return currentCourses;
	}
	
	public int getCompletedCourses() {
		return completeCourses;
	}
	
	public int getRemainingCourses() {
		return config.getMajor().getRemainingCourses(completeCourses);
	}
}
