package model;

import java.time.Duration;

import javafx.animation.TranslateTransition;
import javafx.scene.SubScene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;

public class SpaceRunnerSubScene extends SubScene{
	
	private final String FONT_PATH = "src/model/resources/kenvector_future.ttf";
	private final String BACKGROUND_IMAGE = "model/resources/yellow_panel.png";
	
	private boolean isHidden = true;
	
	public SpaceRunnerSubScene() {
		super(new AnchorPane(),600,400);
		prefWidth(600);
		prefHeight(400);
		
		Image backgroundImage = new Image(BACKGROUND_IMAGE, 600,400, false,true);
		BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);
		
		AnchorPane root2 = (AnchorPane) this.getRoot();
		
		root2.setBackground(new Background(background));
		
		setLayoutX(1024);
		setLayoutY(180);
	}
	
	public void moveSubScene() {
		TranslateTransition transition = new TranslateTransition();
//		transition.setDuration(Duration.seconds(0.5));
		transition.setNode(this);
		if(isHidden) {
			transition.setToX(-676);
			isHidden = false;			
		} else {
			transition.setToX(0);
			isHidden = true;
		}
		transition.play();
	}
}
