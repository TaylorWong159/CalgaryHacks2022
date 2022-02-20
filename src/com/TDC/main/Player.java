package com.TDC.main;

import java.util.HashSet;
import java.util.Set;

import com.TDC.skills.Skill;
import com.TDC.skills.Skills;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Player {
	private Stats stats;
	private Set<Skills> skills;
	private Config config;
	private int completeCourses, currentCourses;
	
	public Player(Config config) {
		this.stats = new Stats(config);
		this.skills = new HashSet<Skills>();
		this.config = config;
		this.completeCourses = 0;
	}
	
	public void update() {
		stats.update();
	}
	
	public Stats getStats() {
		return stats;
	}
	
	public Set<Skills> getSkills() {
		return skills;
	}
	
	public void addSkill(Skills skill) {
		skills.add(skill);
	}
	
	public boolean hasSkill(Skills skill) {
		return skills.contains(skill);
	}
	
	public void setCurrentCourses(int c) {
		currentCourses = c;
	}
	
	public int getCurrentCourses() {
		return currentCourses;
	}
	
	public int getCompletedCourses() {
		return completeCourses;
	}
	
	public void completeCourses(int c) {
		completeCourses += c;
	}
	
	public int getRemainingCourses() {
		return config.getMajor().getRemainingCourses(completeCourses);
	}
	
	public Config getConfig() {
		return config;
	}
	
	public Stage getPreview() {
		
		Label title = new Label("Stats:");
		title.setFont(Font.font("arial", 36));
		
		Font statFont = Font.font("arial", 20);
		double labelWidth = 150;
		
		// School Stat
		Label schoolLabel = new Label("School: ");
		schoolLabel.setFont(statFont);
		schoolLabel.setMinWidth(labelWidth);
		
		Label schoolValue = new Label((int) (this.stats.getAcademics() * 100) + "%");
		schoolValue.setFont(statFont);
		
		HBox schoolBox = new HBox();
		schoolBox.getChildren().addAll(schoolLabel, schoolValue);

		// Mental Health Stat
		Label mentalHealthLabel = new Label("Mental Health: ");
		mentalHealthLabel.setFont(statFont);
		mentalHealthLabel.setMinWidth(labelWidth);
		
		Label mentalHealthValue = new Label((int) (this.stats.getMentalHealth() * 100) + "%");
		mentalHealthValue.setFont(statFont);
		
		HBox mentalHealthBox = new HBox();
		mentalHealthBox.getChildren().addAll(mentalHealthLabel, mentalHealthValue);

		// Mental Health Stat
		Label financialsLabel = new Label("Money: ");
		financialsLabel.setFont(statFont);
		financialsLabel.setMinWidth(labelWidth);
		
		Label financialsValue = new Label("$" + (int) this.stats.getFinancials());
		financialsValue.setFont(statFont);
		
		HBox financialsBox = new HBox();
		financialsBox.getChildren().addAll(financialsLabel, financialsValue);
		
		Label skillLabel = new Label("Skills:");
		skillLabel.setFont(statFont);

		VBox skillList = new VBox();
		for (Skills skill : skills) skillList.getChildren().add(skill.getSkill());
		
		VBox container = new VBox();
		container.getChildren().addAll(title, schoolBox, mentalHealthBox, financialsBox, skillLabel, skillList);
		container.setAlignment(Pos.BASELINE_CENTER);
		container.setSpacing(4);
		container.setPadding(new Insets(16));
		
		Scene scene = new Scene(container, 300, 400);
		
		Stage window = new Stage();
		window.setResizable(false);
		window.setScene(scene);
		window.setTitle("Player Stats");
		window.initModality(Modality.APPLICATION_MODAL);
		
		return window;
	}
}
