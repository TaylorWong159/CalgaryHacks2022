package com.TDC.main;

import javafx.animation.AnimationTimer;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.effect.InnerShadow;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class SetupPane extends ScrollPane {
	
	private double width, height;
	private int slide = 0;
	private Major major;
	private Housing housing;
	private Location location;
	private boolean scrolling = false;
	
	private class MenuPane extends VBox {
		public MenuPane() {
			super();
			this.setMinWidth(width);
			this.setMinHeight(height);
			this.setAlignment(Pos.BASELINE_CENTER);
			this.setSpacing(16);
			//this.setPadding(new Insets(32));
		}
	}
	
	private abstract class ScrollButton extends Button {
		public ScrollButton(String text, SetupPane parent) {
			super(text);
			this.setFont(Font.font("arial", 24));
			this.setOnAction(e -> {
				slide++;
				parent.scroll();
				setChoice();
			});
			this.setMinWidth(200);
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
		this.setHmax(width * 2);
		this.setHmin(0);
		
		// Major Selection
		MenuPane majorPane = new MenuPane();
		Label majorTitle = new Label("Select a major");
		majorTitle.setFont(Font.font("arial", 36));
		MenuPane.setMargin(majorTitle, new Insets(32));
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
		
		// Major Selection
		MenuPane locationPane = new MenuPane();
		
		// Major Selection
		MenuPane housingPane = new MenuPane();
		
		
		HBox container = new HBox();
		container.getChildren().addAll(majorPane, locationPane, housingPane);
		
		
		this.setContent(container);
		this.addEventFilter(ScrollEvent.SCROLL, e -> {
			if (!scrolling) e.consume();
		});
	}
	
	public void scroll() {
		this.scrolling = true;
		long duration = 75;
		SetupPane pane = this;
		AnimationTimer timer = new AnimationTimer() {
			long count = 0;
			@Override
			public void handle(long now) {
				if (count <= duration) {
					double progress = count * 1d / duration;
					pane.setHvalue(width * slide * progress);
				} else {
					scrolling = false;
					this.stop();
				}
				count++;
			}
		};
		timer.start();
	}

}
