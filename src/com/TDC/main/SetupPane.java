package com.TDC.main;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class SetupPane extends ScrollPane {
	
	private double width, height;
	private int slide = 0;
	private Major major;
	private Housing housing;
	private Location location;
	
	private class MenuPane extends VBox {
		public MenuPane() {
			super();
			this.setMinWidth(width);
			this.setMinHeight(height);
		}
	}
	
	private abstract class ScrollButton extends Button {
		public ScrollButton(String text, SetupPane parent) {
			super(text);
			this.setOnAction(e -> {
				slide++;
				parent.scroll();
				setChoice();
			});
		}
		
		protected abstract void setChoice();
		
	}

	public SetupPane(double width, double height) {
		this.width = width;
		this.height = height;
		
		this.setHbarPolicy(ScrollBarPolicy.NEVER);
		this.setVbarPolicy(ScrollBarPolicy.NEVER);
		this.setMinSize(width, height);
		this.setMaxSize(width, height);
		this.setHmax(width * 3);
		this.setHmin(0);
		
		// Major Selection
		MenuPane majorPane = new MenuPane();
		Label majorTitle = new Label("Select a major");
		ScrollButton compsci = new ScrollButton("Computer Science", this) {
			public void setChoice() {
				major = Major.COMPSCI;
			}
		};
		ScrollButton engineering = new ScrollButton("Engineering", this) {
			public void setChoice() {
				major = Major.ENGINEERING;
			}
		};
		majorPane.getChildren().addAll(majorTitle, compsci, engineering);
		majorPane.setBackground(new Background(new BackgroundFill(Color.GRAY, new CornerRadii(0), new Insets(0))));

		// Major Selection
		MenuPane locationPane = new MenuPane();
		locationPane.setBackground(new Background(new BackgroundFill(Color.DARKGRAY, new CornerRadii(0), new Insets(0))));

		// Major Selection
		MenuPane housingPane = new MenuPane();
		housingPane.setBackground(new Background(new BackgroundFill(Color.BLACK, new CornerRadii(0), new Insets(0))));

		
		HBox container = new HBox();
		container.getChildren().addAll(majorPane, locationPane, housingPane);
		
		
		this.setContent(container);
	}
	
	public void scroll() {
		this.setHvalue(this.getHmax() * this.slide / 3);
	}

}
