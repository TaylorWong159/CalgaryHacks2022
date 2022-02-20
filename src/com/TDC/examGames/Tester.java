package com.TDC.examGames;

import com.TDC.main.Config;
import com.TDC.main.Player;

import javafx.application.Application;
import javafx.stage.Stage;

public class Tester extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage arg0) throws Exception {
		MathGame game = new MathGame();
		game.play(new Player(new Config()));
	}
}
