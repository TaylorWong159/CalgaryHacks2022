package com.TDC.main;

import com.TDC.skills.Skills;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {
	public GameState state = GameState.SETUP;
	public Player player;
	public SetupPane setup;
	public CourseSelectPane courseSelect;

	public static void main(String[] args) {
		launch(args);
	}
	
	public void start(Stage window) throws Exception {
		setup = new SetupPane(800, 600);
		
		BorderPane container = new BorderPane();
		container.setCenter(setup);
		
		Button viewStats = new Button("Stats");
		viewStats.setOnAction(e -> {
			if (player != null) {
				player.getPreview().showAndWait();
			}
		});
		container.setBottom(viewStats);
		
		AnimationTimer gameLoop = new AnimationTimer() {
			@Override
			public void handle(long now) {
				switch(state) {
					case SETUP:
						if (!setup.isComplete()) break;
						
						Config config = setup.getConfig();
						player = new Player(config);
						courseSelect = new CourseSelectPane(player);
						container.setCenter(courseSelect);
						state = GameState.COURSE_SELECT;
						
						break;
					case COURSE_SELECT:
						if (courseSelect == null) break;
						if (!courseSelect.isComplete()) break;
						
						int courses = courseSelect.getCoursesToTake();
						if (courses > 0) {
							System.out.println(courses);
						} else state = GameState.GAMEOVER;
						
						break;
					case MAINLOOP:
						break;
					case GAMEOVER:
						GameOverWindow.show();
						this.stop();
						break;
				}
			}
		};
		
		gameLoop.start();
		
		Scene mainScene = new Scene(container, 800, 600);
		
		window.setOnCloseRequest(e -> {
			gameLoop.stop();
			System.exit(0);
		});
		//window.setResizable(false);
		window.setScene(mainScene);
		window.setTitle("School Simulator");
		window.show();
	}

}
