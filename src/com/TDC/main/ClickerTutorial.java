package com.TDC.main;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ClickerTutorial {
    public static void getTutorial(){
        VBox beebleborx = new VBox();
        beebleborx.getChildren().add(new Label("When exam time comes, unscramble the anagrams in the time limit to solve the problem!"));
        Scene scene = new Scene(beebleborx, 800, 600);
        Stage window = new Stage();
        window.setScene(scene);
        window.setTitle("How to Play");
        window.show();
    }

}