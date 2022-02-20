package com.TDC.examGames;

public enum Games {
	MATH, SCRAMBLE;
	
	public static Game getGame(Games game) {
		switch(game) {
			case MATH: return new MathGame();
			case SCRAMBLE: return new ScrambleGame();
		}
		return null;
	}
}
