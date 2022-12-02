package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.text.Font;

public class SmallInfoLabel extends Label{
	private static final String FONT_PATH = "src/model/resources/kenvector_future.ttf";
	
	public SmallInfoLabel(String text) {
		setPrefHeight(30);
		setPrefWidth(130);
		BackgroundImage bgImg = new BackgroundImage(new Image("/view/resources/blue_info_label.png", 130, 30, false, true),
				BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);
		setBackground(new Background(bgImg));
		setAlignment(Pos.CENTER_LEFT);
		setPadding(new Insets(10, 10, 10, 10));
		setLabelFont();
		setText(text);
	}
	
	private void setLabelFont() {
		try {
			setFont(Font.loadFont(new FileInputStream(new File(FONT_PATH)), 10));
		} catch (FileNotFoundException e) {
			setFont(Font.font("Verdana", 15));
		}
		
	}
	
}