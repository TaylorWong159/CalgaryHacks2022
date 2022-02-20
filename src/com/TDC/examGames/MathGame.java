package com.TDC.examGames;

import com.TDC.main.Player;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MathGame implements Game {
    private int score = 0;
    private Difficulty difficulty;

    private String[][] words = {{"43", "8", "14", "10", "16", "20", "37", "21", "32", "2", "23", "21", "24", "26", "20", "5", "25", "21"},
            {"36", "125", "100", "42", "27", "40", "21", "30", "23", "20", "32", "16", "12", "12", "36", "12"},
            {"6", "3", "7", "9", "8", "11", "7", "6", "9", "7", "9", "5", "9"}};

    private String[][] scramble = {{"18+25", "17-9", "6+8", "6+18-14", "18-0.5-1.5", "2+23-5", "18+19", "22+3-4", "17+15", "17-15", "18+2+3", "19+2", "18+6", "19+4+3", "18+2", "12-7", "11+14", "9+9+3"},
    		{"18*2", "25*5", "20*5", "6*7", "3*9", "4*10", "3*7", "15*2", "4*6", "5*4", "2*16", "4*4", "3*4", "2*6", "19*2", "4*3"},
    		{"18/3", "21/7", "35/5", "18/2", "48/6", "77/7", "63/9", "54/8", "81/9", "42/6", "99/11", "25/5", "72/8"}};

    public double play(Player player) {
        difficulty = Difficulty.getDifficulty(1 - ((player.getStats().getAcademics() + player.getStats().getMentalHealth())/2));
        int d = difficulty.asInt();
        int[] rand = new int[player.getCurrentCourses()];
        String[] picks = new String[player.getCurrentCourses()];
        HBox hBox = new HBox();
        TextField[] t = new TextField[player.getCurrentCourses()];
        for (int i = 0; i < player.getCurrentCourses(); i++){
            rand[i] = (int) Math.random() * words[d].length;
            String word = words[d][rand[i]];
            picks[i] = word;
            Label label = new Label(scramble[d][rand[i]]);
            TextField textField = new TextField();
            VBox vBox = new VBox();
            t[i] = textField;
            vBox.getChildren().addAll(label, textField);
            hBox.getChildren().add(vBox);
        }

        AnimationTimer timer = new AnimationTimer() {
            private int frame = 0, stop = 15000;
            @Override
            public void handle(long now) {
                frame++;
                if (frame >= stop){
                    this.stop();
                    return;
                }
                for (int i = 0; i < player.getCurrentCourses(); i++)
                    if (!picks[i].equals(t[i].getText())) return;

                for (int i = 0; i < player.getCurrentCourses(); i++){
                    score = 0;
                    if(picks[i].equals(t[i].getText())){
                        score++;
                    }
                }
            }
        };

        timer.start();

        Scene scene = new Scene(hBox, 800, 600);
        Stage game  = new Stage();
        game.setOnCloseRequest(e -> {
            timer.stop();
        });


        game.setScene(scene);
        game.showAndWait();

        return score;

    }


}
