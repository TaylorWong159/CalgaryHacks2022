public class SkillsSelection extends VBox {
    private Player player;
    public SkillsSelection(Player player){
        super();
        this.player = player;

        for(Skills skill : Skills.values()){
            if(!player.hasSkill(skill)){
                this.getChildren().add(skill.getSkill());
            }
        }
    }
}