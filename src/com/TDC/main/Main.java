package com.TDC.main;

import com.TDC.skills.Skills;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}
	
	public void start(Stage window) throws Exception {
		SetupPane setup = new SetupPane(800, 600);
		
		BorderPane container = new BorderPane();
		container.setCenter(setup);
		container.setBottom(Skills.DISTRACTABLE.getSkill());
		
		AnimationTimer gameLoop = new AnimationTimer() {
			@Override
			public void handle(long now) {
				if (setup.isComplete()) {
					Config config = setup.getConfig();
					System.out.println(config);
					this.stop();
				}
			}
		};
		
		gameLoop.start();
		
		Scene mainScene = new Scene(container, 800, 600);
		
		window.setOnCloseRequest(e -> {
			gameLoop.stop();
		});
		//window.setResizable(false);
		window.setScene(mainScene);
		window.setTitle("School Simulator");
		window.show();
	}

}
