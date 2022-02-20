package com.TDC.main;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class MonthEndConfirmation extends VBox {
	private boolean confirmed = false;
	
	public MonthEndConfirmation(String month, Player player) {
		Label title = new Label("Results after " + month);
		title.setFont(Font.font("arial", 48));
		
		Label stats = new Label("Stats:");
		stats.setFont(Font.font("arial", 36));
		
		Font statFont = Font.font("arial", 20);
		double labelWidth = 150;
		
		// School Stat
		Label schoolLabel = new Label("School: ");
		schoolLabel.setFont(statFont);
		schoolLabel.setMinWidth(labelWidth);
		
		Label schoolValue = new Label((int) (player.getStats().getAcademics() * 100) + "%");
		schoolValue.setFont(statFont);
		
		HBox schoolBox = new HBox();
		schoolBox.getChildren().addAll(schoolLabel, schoolValue);

		// Mental Health Stat
		Label mentalHealthLabel = new Label("Mental Health: ");
		mentalHealthLabel.setFont(statFont);
		mentalHealthLabel.setMinWidth(labelWidth);
		
		Label mentalHealthValue = new Label((int) (player.getStats().getMentalHealth() * 100) + "%");
		mentalHealthValue.setFont(statFont);
		
		HBox mentalHealthBox = new HBox();
		mentalHealthBox.getChildren().addAll(mentalHealthLabel, mentalHealthValue);

		// Mental Health Stat
		Label financialsLabel = new Label("Money: ");
		financialsLabel.setFont(statFont);
		financialsLabel.setMinWidth(labelWidth);
		
		Label financialsValue = new Label("$" + (int) player.getStats().getFinancials());
		financialsValue.setFont(statFont);
		
		HBox financialsBox = new HBox();
		financialsBox.getChildren().addAll(financialsLabel, financialsValue);
		
		Button confirm = new Button("Confirm");
		confirm.setOnAction(e -> {
			confirmed = true;
		});
		
		this.getChildren().addAll(title, stats, schoolBox, mentalHealthBox, financialsBox, confirm);
		this.setAlignment(Pos.BASELINE_CENTER);
	}
	
	public boolean isComplete() {
		return confirmed;
	}
}
