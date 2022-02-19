package com.TDC.main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}
	
	public void start(Stage window) throws Exception {
		BorderPane container = new BorderPane();
		Scene mainScene = new Scene(container, 800, 600);
		
		window.setScene(mainScene);
		window.setTitle("School Simulator");
		window.show();
	}

}
