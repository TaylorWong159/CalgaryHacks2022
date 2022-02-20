package com.TDC.main;

import com.TDC.skills.Skill;
import com.TDC.skills.Skills;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class ExamResultPane extends VBox {
	private boolean confirmed = false;
	private Skills selectedSkill = null;
	
	public ExamResultPane(double score, Player player) {
		super();
		boolean passed = score > player.getCurrentCourses() * 0.5;
		
		Label title = new Label("Exam Results");
		title.setFont(Font.font("arial", 48));
		
		Label msg = new Label(String.format("You passed %d/%d of your classes!", (int) score, (int) player.getCurrentCourses()));
		msg.setFont(Font.font("arial", 36));
		
		VBox skillList = new VBox();
		for (Skills skill : Skills.values()) {
			if (!player.hasSkill(skill)) {
				if (skill.isPositive() ^ passed) continue;
				Skill skillButton = skill.getSkill();
				skillButton.setOnAction(e -> {
					selectedSkill = skill;
					confirmed = true;
				});
				skillList.getChildren().add(skillButton);
			}
		}
		
		ScrollPane skillSelect = new ScrollPane();
		skillSelect.setContent(skillList);
		
		this.getChildren().addAll(title, msg, skillSelect);
		this.setAlignment(Pos.BASELINE_CENTER);
		this.setSpacing(8);
	}
	
	public boolean isComplete() {
		return confirmed;
	}
	
	public Skills getSelectedSkill() {
		return selectedSkill;
	}
}
