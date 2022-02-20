package com.TDC.main;

import java.util.Arrays;

import javafx.collections.ListChangeListener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.control.SplitPane.Divider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class MonthParamPane extends VBox {
	private boolean ready = false;
	private TimeDistribution timeDist;
	
	public MonthParamPane(String month) {
		Label text = new Label("Please partition your time for each day in " + month);
		text.setFont(Font.font("arial", 36));
		text.setWrapText(true);
		
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
		slider.setMinHeight(100);

		
		Label sleepReadout = new Label(format("Sleep: ", slider.getDividerPositions()[0]));
		Label schoolReadout = new Label(format("School: ", slider.getDividerPositions()[1] - slider.getDividerPositions()[0]));
		Label relaxReadout = new Label(format("Relaxation: ", slider.getDividerPositions()[2] - slider.getDividerPositions()[1]));
		Label workReadout = new Label("Work: " + 24 * (1 - (slider.getDividerPositions()[2])));
		
		VBox readout = new VBox();
		readout.getChildren().addAll(sleepReadout, schoolReadout, relaxReadout, workReadout);
		slider.getDividers().forEach(divider -> {
			divider.positionProperty().addListener(e -> {
				double[] positions = slider.getDividerPositions();
				sleepReadout.setText("Sleep: " + as1Decimal(positions[0]));
				schoolReadout.setText("School: " + as1Decimal(positions[1] - positions[0]));
				relaxReadout.setText("Relaxation: " + as1Decimal(positions[2] - positions[1]));
				workReadout.setText("Work: " + as1Decimal(24 * (1 - (positions[2]))));
			});
		});
		
		Button confirm = new Button("Confirm");
		confirm.setOnAction(e -> {
			timeDist = new TimeDistribution(slider.getDividerPositions());
			ready = true;
		});
		confirm.setFont(Font.font("arial", 16));
		
		VBox.setMargin(confirm, new Insets(16));
		this.getChildren().addAll(text, readout, slider, confirm);
		this.setSpacing(16);
		this.setAlignment(Pos.BASELINE_CENTER);
	}
	
	public boolean isComplete() {
		return ready;
	}
	
	public TimeDistribution getTimes() {
		return timeDist;
	}
	
	private String format(String msg, double perc) {
		return msg + (int)(perc * 24) + "h";
	}
	
	private String as1Decimal(double d) {
		return "" + Math.floor(d * 10) / 10;
	}
}
