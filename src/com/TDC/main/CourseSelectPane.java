package com.TDC.main;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class CourseSelectPane extends VBox {
	private int coursesToTake = 3;
	private boolean selectionMade = false;
	
	private class CourseButton extends Button {
		
		public CourseButton(int val) {
			super(val + "");
			this.setFont(Font.font("arial", 18));
			this.setFocusTraversable(false);
			
			this.setOnMouseClicked(e -> {
				coursesToTake = val;
				selectionMade = true;
			});
		}
	}
	
	public CourseSelectPane(Player p) {
		super();
		Label title = new Label("What do you want to do this semester:");
		title.setFont(Font.font("arial", 42));
		
		Label remaining = new Label(String.format("You need %d more coursed to graduate", p.getRemainingCourses()));
		remaining.setFont(Font.font("arial", 36));
		
		int costPerCourse = p.getConfig().getLocation().getMonthyCost();
		double playerMoney = p.getStats().getFinancials();
		Label courseSelection = new Label(String.format("Take how many courses ($%d each)", costPerCourse));
		courseSelection.setFont(Font.font("arial", 24));

		HBox buttonBox = new HBox();
		if (playerMoney < 3 * costPerCourse) return;
		for (int i = 3; i < Math.min(playerMoney / costPerCourse, 7); i++) {
			buttonBox.getChildren().add(new CourseButton(i));
		}
		buttonBox.setAlignment(Pos.CENTER);
		buttonBox.setSpacing(8);
		
		Label or = new Label("or");
		or.setFont(Font.font("arial", 24));
		Button dropOut = new Button("Drop Out");
		dropOut.setFont(Font.font("arial", 24));
		dropOut.setOnAction(e -> {
			coursesToTake = 0;
			selectionMade = true;
		});
		
		this.getChildren().addAll(title, remaining, courseSelection, buttonBox, or, dropOut);
		this.setAlignment(Pos.BASELINE_CENTER);
		this.setSpacing(16);
	}
	
	public int getCoursesToTake() {
		return coursesToTake;
	}
	
	public boolean isComplete() {
		return selectionMade;
	}
}
