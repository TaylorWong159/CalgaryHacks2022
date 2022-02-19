package com.TDC.examGames;


public enum Difficulty {
    EASY, MEDIUM, HARD;
	
	public static Difficulty getDifficulty(double val) {
		if (val < 1/3) return EASY;
		if (val < 2/3) return MEDIUM;
		return HARD;
	}
}