public class ClickerTutorial {
    public void getTutorial(){
        VBox beebleborx = new VBox();
        beebleborx.getChildren().add(new Label = "When exam time comes, unscramble the anagrams in the time limit to solve the problem!");
        Scene scene = new Scene(beebleborx, 800, 600);
        Window window = new Window();
        window.setScene(scene);
        window.setTitle("How to Play");
        window.show();
    }

}