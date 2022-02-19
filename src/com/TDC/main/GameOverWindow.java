package com.TDC.main;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class GameOverWindow {

	public static void show() {
		
		Label title = new Label("Game Over");
		title.setFont(Font.font("arial", FontWeight.BOLD, 48));
		
		StackPane container = new StackPane();
		container.getChildren().add(title);
		
		Scene scene = new Scene(container, 400, 400);
		
		Stage window = new Stage();
		window.setScene(scene);
		window.setTitle("Game Over");
		window.initModality(Modality.APPLICATION_MODAL);
		window.show();
	}
}
