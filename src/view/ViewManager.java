package view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.SpaceRunnerButton;
import model.SpaceRunnerSubScene;

public class ViewManager {

	private static final int HEIGHT = 650;
	private static final int WIDTH = 1024;
	
	private AnchorPane mainPane;
	private Scene mainScene;
	private Stage mainStage;
	
	private final static int MENU_BUTTON_START_X = 100;
	private final static int MENU_BUTTON_START_Y = 150;
	
	List<SpaceRunnerButton> menuButtons;
	
	public ViewManager() {
		menuButtons = new ArrayList<>();
		mainPane = new AnchorPane();
		mainScene = new Scene(mainPane,WIDTH,HEIGHT);
		mainStage = new Stage();
		mainStage.setScene(mainScene);
		createButtons();
		createBackground();
		createLogo();
		
		SpaceRunnerSubScene subScene = new SpaceRunnerSubScene();
		
		subScene.setLayoutX(200);
		subScene.setLayoutY(180);
		
		mainPane.getChildren().add(subScene);
	}
	
	public Stage getMainStage() {
		return mainStage;
	}
	
	private void addMenuButton(SpaceRunnerButton button) {
		button.setLayoutX(MENU_BUTTON_START_X);
		button.setLayoutY(MENU_BUTTON_START_Y + menuButtons.size() * 100);
		menuButtons.add(button);
		mainPane.getChildren().add(button);
	}
	
	
	private void createButtons() {
		createStartButton();
		createScoresButton();
		createHelpButton();
		createCreditsButton();
		createExitButton();
	}
	
	private void createStartButton() {
		SpaceRunnerButton startButton = new SpaceRunnerButton("PlAY");
		addMenuButton(startButton);
	}
	
	private void createScoresButton() {
		SpaceRunnerButton scoresButton = new SpaceRunnerButton("SCORES");
		addMenuButton(scoresButton);
	}
	
	private void createHelpButton() {
		SpaceRunnerButton helpButton = new SpaceRunnerButton("HELP");
		addMenuButton(helpButton);
	}
	
	private void createCreditsButton() {
		SpaceRunnerButton creditsButton = new SpaceRunnerButton("CREDITS");
		addMenuButton(creditsButton);
	}
	
	private void createExitButton() {
		SpaceRunnerButton exitButton = new SpaceRunnerButton("EXIT");
		addMenuButton(exitButton);
	}
	
	
	private void createBackground() {
		Image backgroundImage = new Image("view/resources/blue.png", 256,256, false,true);
		BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT, BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
		mainPane.setBackground(new Background(background));
	}
	
	private void createLogo() {
		Text logo = new Text("SPACE RUNNER");
		try {
			logo.setFont(Font.loadFont(new FileInputStream("src/model/resources/kenvector_future.ttf"), 50));
		}
		catch (FileNotFoundException e) {
			logo.setFont(Font.font("Verdana", 40));
		}
		
		logo.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				logo.setEffect(new DropShadow());
				
			}
		});
		
		logo.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				logo.setEffect(null);
				
			}
		});
		
		logo.setLayoutX(480);
		logo.setLayoutY(100);
		
		
		mainPane.getChildren().add(logo);
		
	}
}
