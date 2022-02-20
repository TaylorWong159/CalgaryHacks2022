package com.TDC.examGames;

import com.TDC.main.MathTutorial;
import com.TDC.main.Player;

import javafx.animation.AnimationTimer;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MathGame extends Game {
    private Difficulty difficulty;

    private String[][] words = {{"43", "8", "14", "10", "16", "20", "37", "21", "32", "2", "23", "21", "24", "26", "20", "5", "25", "21"},
            {"36", "125", "100", "42", "27", "40", "21", "30", "23", "20", "32", "16", "12", "12", "36", "12"},
            {"6", "3", "7", "9", "8", "11", "7", "7", "9", "7", "9", "5", "9"}};

    private String[][] scramble = {{"18+25", "17-9", "6+8", "6+18-14", "18-0.5-1.5", "2+23-5", "18+19", "22+3-4", "17+15", "17-15", "18+2+3", "19+2", "18+6", "19+4+3", "18+2", "12-7", "11+14", "9+9+3"},
    		{"18*2", "25*5", "20*5", "6*7", "3*9", "4*10", "3*7", "15*2", "4*6", "5*4", "2*16", "4*4", "3*4", "2*6", "19*2", "4*3"},
    		{"18/3", "21/7", "35/5", "18/2", "48/6", "77/7", "63/9", "56/8", "81/9", "42/6", "99/11", "25/5", "72/8"}};


    public void showTutorial(){
        MathTutorial.getTutorial();
    }
    
    public void play(Player player) {
    	Stage game = new Stage();
        difficulty = Difficulty.getDifficulty(1 - ((player.getStats().getAcademics() + player.getStats().getMentalHealth())/2));
        int d = difficulty.asInt();
        int[] rand = new int[player.getCurrentCourses()];
        String[] picks = new String[player.getCurrentCourses()];
        HBox hBox = new HBox();
        TextField[] t = new TextField[player.getCurrentCourses()];
        Font questionFont = Font.font("arial", 16);
        for (int i = 0; i < player.getCurrentCourses(); i++){
            rand[i] = (int) (Math.random() * words[d].length);
            String word = words[d][rand[i]];
            picks[i] = word;
            Label label = new Label(scramble[d][rand[i]]);
            label.setFont(questionFont);
            TextField textField = new TextField();
            VBox vBox = new VBox();
            t[i] = textField;
            vBox.getChildren().addAll(label, textField);
            hBox.getChildren().add(vBox);
        }
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(8);
        
        Label countDown = new Label();
        countDown.setPadding(new Insets(16));
        countDown.setFont(Font.font("arial", 26));

        AnimationTimer timer = new AnimationTimer() {
          private int frame = 0, stop = 3000;
          @Override
          public void handle(long now) {
        	  countDown.setText("" + (int) ((stop - frame) / 100));
              frame++;
              if (frame >= stop) {
                  score = 0;
                  for (int i = 0; i < player.getCurrentCourses(); i++){
                      if(picks[i].equals(t[i].getText())){
                          score++;
                      }
                  }
                  finished = true;
                  this.stop();
                  game.close();
                  return;
              }
              for (int i = 0; i < player.getCurrentCourses(); i++)
                  if (!picks[i].equals(t[i].getText())) return;

              score = 0;
              for (int i = 0; i < player.getCurrentCourses(); i++){
                  if(picks[i].equals(t[i].getText())){
                      score++;
                  }
              }
              
              if (score == player.getCurrentCourses()) {
              	finished = true;
              	this.stop();
              	game.close();
              }
          }
        };

        timer.start();
        
        BorderPane container = new BorderPane();
        container.setCenter(hBox);
        container.setTop(countDown);

        Scene scene = new Scene(container, 800, 600);
        game.setOnCloseRequest(e -> {
            timer.stop();
        });

        game.setTitle("Math Exam");
        game.setScene(scene);
        game.initModality(Modality.APPLICATION_MODAL);
        game.show();

    }


}
