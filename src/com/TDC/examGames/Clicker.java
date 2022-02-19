package com.TDC.examGames;
import com.TDC.main.Stats;
import javafx.*;

public class Clicker implements Game {
    private int score = 0;
    private Difficulty difficulty = Difficulty.getDifficulty(1 - ((Stats.academic() + Stats.mentalHealth())/2));

    private String[][] words = {{"clone", "cylce", "adult", "apple", "error", "looks", "table", "video", "youth", "patch", "venue", "pilot", "vague", "eagle", "drums", "tally", "about", "dusty", "sushi"},
                              {"ability", "absence", "channel", "culture", "liberty", "factful", "oddball", "citizen", "icecaps", "cancels", "healthy", "benefit", "adverse", "library", "massive", "meaning", "teacher", "protect", "satisfy"},
                              {"numerical", "passwords", "realizing", "thrilling", "explosion", "cosmetics", "guideline", "identity", "initially", "situation", "president", "questions", "thousands", "mountains", "sparkling", "recycling", "hairstyle"}}

    private String[][] scramble = {{"enlco", "ceycl", "dautl", "aeplp", "orerr", "oslok", "tlbea", "voedi", "htyuo", "hcpat", "uneve", "ltiop", "veuga", "gelae", "srmud", "atlyl", "utboa", "dytsu", "hiuss"},
                                {"lyatiib", "ebnseac", "helnnca", "tulceru", "beltryi", "lffctau", "adbdllo", "iizctne", "cpceais", "nacscel", "ehhlyta", "eftbnei", "seevard", "biyrlar", "amevsis", "geinamn", "cehtare", "eptctor", "itayfss"},
                                {"amiecurln", "wssdapsro", "eilrnzaig", "niigrhltl", "lpxeooisn", "ociestmsc", "uindigeel", "ietitnyd", "lnyiltiia", "utnitsaoi", "ptenrides", "ontsiuseq", "ohdunsast", "unsontmia", "iksalpnrg", "ilngrccye", "atirshley"}}

    public double play() {
        d = Math.floor(difficulty.asInt);
        int[] rand = new int[5];
        String[] picks = new String[5];
        HBox hBox = new HBox();
        TextField[] t = new TextField[][5];
        for (int i = 0, i < 5, i++){
            rand[i] = Math.randint(0,9);
            String word = words[d][rand[i]];
            picks[i] = word;
            Label label = new Label(word);
            TextField textField = new TextField();
            VBox vBox = new vBox();
            t[i] = textField;
            vBox.getChildren.addAll(label, textField);
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
              for (int i = 0; i < 5; i++)
                  if (!picks[i].equals(t[i].getValue())) return;

              for (int i = 0; i < 5; i++){
                  score = 0;
                  if(picks[i].equals(t[i].getValue())){
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
        })


        game.setScene(scene);
        game.showAndWait();

        return score / 5;

    }


}
