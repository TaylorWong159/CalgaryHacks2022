package com.TDC.examGames;

import com.TDC.main.Player;

public abstract class Game {
	protected double score;
	protected boolean finished;
	
	public abstract void play(Player p);
	public abstract void showTutorial();
	
	public boolean isFinished() {
		return finished;
	}
	
	public double getScore() {
		return score;
	}
}
