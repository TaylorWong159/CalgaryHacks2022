package com.TDC.examGames;

import com.TDC.main.ScrambleTutorial;
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
import javafx.stage.Stage;

public class ScrambleGame extends Game {
    private Difficulty difficulty;

    private String[][] words = {{"bead", "tuba", "race", "seal", "gate", "cake", "icon", "kick", "many", "tape", "lame", "cake"},
    		{"clone", "cycle", "adult", "apple", "error", "looks", "table", "video", "youth", "patch", "venue", "pilot", "vague", "eagle", "drums", "tally", "about", "dusty", "sushi"},
    		{"better", "corner", "handle", "facing", "market", "strong", "turtle", "rating", "travel", "purple"}};

    private String[][] scramble = {{"eadb", "abut", "rcae", "leas", "teag", "kaec", "coni", "ickk", "mayn", "peta", "meal", "kaec"},
    		{"enlco", "ceycl", "dautl", "aeplp", "orerr", "oslok", "tlbea", "voedi", "htyuo", "hcpat", "uneve", "ltiop", "veuga", "gelae", "srmud", "atlyl", "utboa", "dytsu", "hiuss"},
    		{"btteer", "corren", "hslande", "fcangi", "rkaetm", "gronst", "urtlet", "ngitar", "veltra", "puprle"}};
    public void showTutorial(){
        ScrambleTutorial.getTutorial();
    }
    public void play(Player player) {
        Stage game  = new Stage();
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
        game.setTitle("English Exam");

        game.setScene(scene);
        game.show();
    }


}
