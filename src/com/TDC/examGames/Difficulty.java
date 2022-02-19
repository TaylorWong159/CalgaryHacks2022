package com.TDC.examGames;


public enum Difficulty {
    EASY(0), MEDIUM(1), HARD(2);
	
	private int index;
	private Difficulty(int index) {
		this.index = index;
	}
	
	public static Difficulty getDifficulty(double val) {
		if (val < 1/3) return EASY;
		if (val < 2/3) return MEDIUM;
		return HARD;
	}
	
	public int asInt() {
		return index;
	}
	
}