package com.TDC.examGames;

import com.TDC.main.Player;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Math implements Game {
    private int score = 0;
    private Difficulty difficulty;

    private String[][] words = {{"clone", "cylce", "adult", "apple", "error", "looks", "table", "video", "youth", "patch", "venue", "pilot", "vague", "eagle", "drums", "tally", "about", "dusty", "sushi"},
            {"ability", "absence", "channel", "culture", "liberty", "factful", "oddball", "citizen", "icecaps", "cancels", "healthy", "benefit", "adverse", "library", "massive", "meaning", "teacher", "protect", "satisfy"},
            {"numerical", "passwords", "realizing", "thrilling", "explosion", "cosmetics", "guideline", "identity", "initially", "situation", "president", "questions", "thousands", "mountains", "sparkling", "recycling", "hairstyle"}};

    private String[][] scramble = {{"enlco", "ceycl", "dautl", "aeplp", "orerr", "oslok", "tlbea", "voedi", "htyuo", "hcpat", "uneve", "ltiop", "veuga", "gelae", "srmud", "atlyl", "utboa", "dytsu", "hiuss"},
            {"lyatiib", "ebnseac", "helnnca", "tulceru", "beltryi", "lffctau", "adbdllo", "iizctne", "cpceais", "nacscel", "ehhlyta", "eftbnei", "seevard", "biyrlar", "amevsis", "geinamn", "cehtare", "eptctor", "itayfss"},
            {"amiecurln", "wssdapsro", "eilrnzaig", "niigrhltl", "lpxeooisn", "ociestmsc", "uindigeel", "ietitnyd", "lnyiltiia", "utnitsaoi", "ptenrides", "ontsiuseq", "ohdunsast", "unsontmia", "iksalpnrg", "ilngrccye", "atirshley"}};

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
