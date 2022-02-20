package com.TDC.main;

import com.TDC.examGames.*;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {
	public GameState state = GameState.SETUP;
	public Player player;
	public SetupPane setup;
	public CourseSelectPane courseSelect;
	public MonthParamPane monthParam;
	public MonthEndConfirmation monthConfirm;
	public ExamResultPane resultPane;
	public static final String[] months = {"September", "October", "November", "December", "January", "February", "March", "April", "May", "June", "July", "August"};
	public static final Game[] games = {new Clicker(), new MathGame()};
	public Game curGame;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void start(Stage window) throws Exception {
		setup = new SetupPane(800, 600);
		
		BorderPane container = new BorderPane();
		container.setCenter(setup);
		
		Button viewStats = new Button("View Current Stats");
		viewStats.setOnAction(e -> {
			if (player != null) {
				player.getPreview().showAndWait();
			}
		});
		BorderPane.setMargin(viewStats, new Insets(8));
		container.setBottom(viewStats);
		
		AnimationTimer gameLoop = new AnimationTimer() {
			int curMonth = 0;
			
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
							player.getStats().subtractTuition(courses);
							player.setCurrentCourses(courses);
							monthParam = new MonthParamPane(months[curMonth]);
							container.setCenter(monthParam);
							state = GameState.MONTH_PARAMS;
						} else state = GameState.GAMEOVER;
						
						break;
					case MONTH_PARAMS:
						if (monthParam == null) break;
						if (!monthParam.isComplete()) break;
						TimeDistribution timeDist = monthParam.getTimes();
						System.out.println(timeDist);
						if (player != null) player.getStats().setTimeDist(timeDist);
						state = GameState.MAINLOOP;
						player.update();
						monthConfirm = new MonthEndConfirmation(months[curMonth = (curMonth + 1) % months.length], player);
						container.setCenter(monthConfirm);
						break;
					case MAINLOOP:
						if (monthConfirm == null) break;
						if (!monthConfirm.isComplete()) break;
						if (curMonth % 4 != 3) {
							monthParam = new MonthParamPane(months[curMonth]);
							container.setCenter(monthParam);
							state = GameState.MONTH_PARAMS;
						} else {
							int gameIndex = (int) (Math.random() * games.length);
							curGame = games[gameIndex];
							curGame.play(player);
							state = GameState.EXAM;
							curMonth = (curMonth + 1) % months.length;
						}
						break;
					case EXAM:
						if (curGame == null) break;
						if (!curGame.isFinished()) break;
						double score = curGame.getScore();
						resultPane = new ExamResultPane(score, player);
						container.setCenter(resultPane);
						state = GameState.RESULT;
						player.completeCourses((int) score);
						break;
					case RESULT:
						if (resultPane == null) break;
						if (!resultPane.isComplete()) break;
						courseSelect = new CourseSelectPane(player);
						container.setCenter(courseSelect);
						state = GameState.COURSE_SELECT;
						
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
		window.setResizable(false);
		window.setScene(mainScene);
		window.setTitle("School Simulator");
		window.show();
	}

}
