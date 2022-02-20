package com.TDC.main;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class MonthParamPane extends VBox {
	private boolean ready = false;
	private TimeDistribution timeDist;
	
	public MonthParamPane() {
		Label text = new Label("Please partition you time for this month");
		
		Font font = Font.font("arial", 26);
		
		// Sleep
		Label sleep = new Label("Sleep");
		sleep.setFont(font);
		
		StackPane sleepPane = new StackPane();
		sleepPane.getChildren().add(sleep);
		
		// School
		Label school = new Label("School");
		school.setFont(font);
		
		StackPane schoolPane = new StackPane();
		schoolPane.getChildren().add(school);
		
		// Relax
		Label relax = new Label("Relaxation");
		relax.setFont(font);
		
		StackPane relaxPane = new StackPane();
		relaxPane.getChildren().add(relax);
		
		// Work
		Label work = new Label("Work");
		work.setFont(font);
		
		StackPane workPane = new StackPane();
		workPane.getChildren().add(work);
		
		SplitPane slider = new SplitPane();
		slider.getItems().addAll(sleepPane, schoolPane, relaxPane, workPane);
		
		Button confirm = new Button("Confirm");
		confirm.setOnAction(e -> {
			timeDist = new TimeDistribution(slider.getDividerPositions());
			ready = true;
		});
		
		this.getChildren().addAll(text, slider, confirm);
		
	}
	
	public boolean isComplete() {
		return ready;
	}
	
	public TimeDistribution getTimes() {
		return timeDist;
	}
}
