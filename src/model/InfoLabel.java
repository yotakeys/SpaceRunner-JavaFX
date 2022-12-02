package model;

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

public class InfoLabel extends Label{
	public final static String FONT_PATH = "src/model/resources/kenvector_future.ttf";
	
	public InfoLabel(String text) {
		setPrefWidth(600);
		setPrefHeight(400);
		setPadding(new Insets(40, 40, 40, 40));
		setText(text);
		setWrapText(true);
		setLabelFont();
				
	}
	
	private void setLabelFont() {
		try {
			setFont(Font.loadFont(new FileInputStream(FONT_PATH), 25));
		} catch (FileNotFoundException e) {
			setFont(Font.font("Verdana", 25));
		}
	}
}