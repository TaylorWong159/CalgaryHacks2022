package com.TDC.main;

import javafx.animation.AnimationTimer;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class SetupPane extends ScrollPane {
	
	private double width, height;
	private int slide = 0;
	private Config config;
	private boolean scrolling = false;
	boolean complete = false;
	
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
				if (parent.complete) {
					
				}
			});
			this.setMinWidth(250);
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
		
		Font titleFont = Font.font("arial", FontWeight.BOLD, 36);
		
		// Major Selection
		MenuPane majorPane = new MenuPane();
		Label majorTitle = new Label("Select a major");
		majorTitle.setFont(titleFont);
		MenuPane.setMargin(majorTitle, new Insets(32));
		ScrollButton compsci = new ScrollButton("Computer Science", this) {
			public void setChoice() {
				config.setMajor(Major.COMPSCI);
			}
		};
		ScrollButton engineering = new ScrollButton("Engineering", this) {
			public void setChoice() {
				config.setMajor(Major.ENGINEERING);
			}
		};
		majorPane.getChildren().addAll(majorTitle, compsci, engineering);
		
		// Major Selection
		MenuPane locationPane = new MenuPane();
		Label locationTitle = new Label("Domestic or International Student");
		locationTitle.setFont(titleFont);
		MenuPane.setMargin(locationTitle, new Insets(32));
		ScrollButton domestic = new ScrollButton("Domestic", this) {
			public void setChoice() {
				config.setLocation(Location.DOMESTIC);
			}
		};
		ScrollButton international = new ScrollButton("International", this) {
			public void setChoice() {
				config.setLocation(Location.INTERNATIONAL);
			}
		};
		locationPane.getChildren().addAll(locationTitle, domestic, international);
		
		
		// Major Selection
		MenuPane housingPane = new MenuPane();
		Label housingTitle = new Label("Living Situation");
		housingTitle.setFont(titleFont);
		MenuPane.setMargin(housingTitle, new Insets(32));
		ScrollButton campus = new ScrollButton("On Campus", this) {
			public void setChoice() {
				config.setHousing(Housing.CAMPUS);
			}
		};
		ScrollButton home = new ScrollButton("From Home", this) {
			public void setChoice() {
				config.setHousing(Housing.HOME);
			}
		};
		housingPane.getChildren().addAll(housingTitle, campus, home);
		
		
		HBox container = new HBox();
		container.getChildren().addAll(majorPane, locationPane, housingPane);
		
		
		this.setContent(container);
		this.addEventFilter(ScrollEvent.SCROLL, e -> {
			if (!scrolling) e.consume();
		});
	}
	
	public void scroll() {
		this.scrolling = true;
		long duration = 50;
		SetupPane pane = this;
		AnimationTimer timer = new AnimationTimer() {
			long count = 0;
			@Override
			public void handle(long now) {
				if (count <= duration) {
					double progress = count * 1d / duration;
					pane.setHvalue(width * (slide - 1) + width * progress);
				} else {
					scrolling = false;
					this.stop();
				}
				count++;
			}
		};
		timer.start();
	}

	public Config getConfig() {
		return config;
	}

}
