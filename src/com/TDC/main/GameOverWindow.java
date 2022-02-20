package com.TDC.main;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class GameOverWindow {

	public static void show(String gameOverMessage) {
		
		Label title = new Label("Game Over");
		title.setFont(Font.font("arial", FontWeight.BOLD, 48));
		
		Label msg = new Label(gameOverMessage);
		msg.setFont(Font.font("arial", 36));
		
		VBox container = new VBox();
		container.getChildren().addAll(title, msg);
		container.setAlignment(Pos.CENTER);
		
		Scene scene = new Scene(container, 400, 400);
		
		Stage window = new Stage();
		window.setScene(scene);
		window.setTitle("Game Over");
		window.initModality(Modality.APPLICATION_MODAL);
		window.show();
		window.setOnCloseRequest(e -> System.exit(0));
	}
}
