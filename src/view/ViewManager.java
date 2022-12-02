package view;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
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
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.InfoLabel;
import model.SHIP;
import model.ShipPicker;
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

	private SpaceRunnerSubScene creditsSubScene;
	private SpaceRunnerSubScene helpSubScene;
	private SpaceRunnerSubScene scoreSubScene;
	private SpaceRunnerSubScene shipChooserScene;
	
	private SpaceRunnerSubScene sceneToHide;

	List<SpaceRunnerButton> menuButtons;
	List<ShipPicker> shipsList;
	private SHIP chosenShip;

	public ViewManager() {
		menuButtons = new ArrayList<>();
		mainPane = new AnchorPane();
		mainScene = new Scene(mainPane, WIDTH, HEIGHT);
		mainStage = new Stage();
		mainStage.setScene(mainScene);
		
		createSubScenes();
		createButtons();
		createBackground();
		createLogo();

	}

	public Stage getMainStage() {
		return mainStage;
	}
	
	private void showSubScene(SpaceRunnerSubScene subScene) {
		if(sceneToHide != subScene) {
			if(sceneToHide != null) {
				sceneToHide.moveSubScene();
			}
			
			subScene.moveSubScene();
			sceneToHide = subScene;	
		}else {
			sceneToHide = null;
			subScene.moveSubScene();
		}
	}

	private void createSubScenes() {
		createCreditSubScene();

		createScoreSubScene();

		createHelpSubScene();

		createShipChooseSubScene();
	}
	
	private void createShipChooseSubScene() {
		shipChooserScene = new SpaceRunnerSubScene();
		mainPane.getChildren().add(shipChooserScene);
		
		InfoLabel chooseShipLabel = new InfoLabel("CHOOSE YOUR SHIP");
		chooseShipLabel.setLayoutX(110);
		chooseShipLabel.setLayoutY(25);
		
		shipChooserScene.getPane().getChildren().add(chooseShipLabel);		
		shipChooserScene.getPane().getChildren().add(createShipsToChoose());
		shipChooserScene.getPane().getChildren().add(createButtonToStart());
	}
	
	private void createHelpSubScene() {
		helpSubScene = new SpaceRunnerSubScene();
		mainPane.getChildren().add(helpSubScene);
		InfoLabel help = new InfoLabel("                  HELP");
		help.setLayoutX(110);
		help.setLayoutY(25);
		
		helpSubScene.getPane().getChildren().add(help);
	}
	
	private void createScoreSubScene() {
		scoreSubScene = new SpaceRunnerSubScene();
		mainPane.getChildren().add(scoreSubScene);
		InfoLabel score = new InfoLabel(" PLAYER SCORES ");
		score.setLayoutX(110);
		score.setLayoutY(25);
		
		scoreSubScene.getPane().getChildren().add(score);
	}
	
	private void createCreditSubScene() {
		creditsSubScene = new SpaceRunnerSubScene();
		mainPane.getChildren().add(creditsSubScene);
		InfoLabel credit = new InfoLabel("             CREDITS");
		credit.setLayoutX(110);
		credit.setLayoutY(25);
		
		Text nama = new Text("Nama : Keyisa Raihan");
		Text nrp = new Text("NRP : 5025211002");
		
		Text linkYoutube = new Text("Tutorial from Youtube : Java Carving / takeCert");
		
		nama.setLayoutY(150);
		nama.setLayoutX(80);
		nrp.setLayoutY(200);
		nrp.setLayoutX(80);
		linkYoutube.setLayoutY(250);
		linkYoutube.setLayoutX(80);
		
		try {
			nama.setFont(Font.loadFont(new FileInputStream("src/model/resources/kenvector_future.ttf"), 15));
			nrp.setFont(Font.loadFont(new FileInputStream("src/model/resources/kenvector_future.ttf"), 15));
			linkYoutube.setFont(Font.loadFont(new FileInputStream("src/model/resources/kenvector_future.ttf"), 15));
		} catch (FileNotFoundException e) {
			nama.setFont(Font.font("Verdana", 40));
			nrp.setFont(Font.font("Verdana", 40));
			linkYoutube.setFont(Font.font("Verdana", 40));
		}
		
		creditsSubScene.getPane().getChildren().add(credit);
		creditsSubScene.getPane().getChildren().add(nama);
		creditsSubScene.getPane().getChildren().add(nrp);
		creditsSubScene.getPane().getChildren().add(linkYoutube);
	}
	
	private HBox createShipsToChoose() {
		HBox box = new HBox();
		box.setSpacing(20);
		shipsList = new ArrayList<>();
		for (SHIP ship : SHIP.values()) {
			ShipPicker shipToPick = new ShipPicker(ship);
			shipsList.add(shipToPick);
			box.getChildren().add(shipToPick);
			shipToPick.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent arg0) {
					for (ShipPicker ship : shipsList) {
						ship.setIsCircleChosen(false);
					}
					shipToPick.setIsCircleChosen(true);
					chosenShip = shipToPick.getShip();
				}
			});
		}
		box.setLayoutX(300 - 118 * 2);
		box.setLayoutY(120);
		return box;
	}
	
	private SpaceRunnerButton createButtonToStart() {
		SpaceRunnerButton startButton = new SpaceRunnerButton("START");
		startButton.setLayoutX(350);
		startButton.setLayoutY(320);
		
		startButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (chosenShip != null) {
					mainStage.hide();
					GameViewManager gameViewManagger = new GameViewManager();
					gameViewManagger.createNewGame(mainStage, chosenShip);
				}
			}
		});
		
		return startButton;
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

		startButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				showSubScene(shipChooserScene);
			}
		});
	}

	private void createScoresButton() {
		SpaceRunnerButton scoresButton = new SpaceRunnerButton("SCORES");
		addMenuButton(scoresButton);

		scoresButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				showSubScene(scoreSubScene);
			}
		});
	}

	private void createHelpButton() {
		SpaceRunnerButton helpButton = new SpaceRunnerButton("HELP");
		addMenuButton(helpButton);

		helpButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				showSubScene(helpSubScene);
			}
		});
	}

	private void createCreditsButton() {
		SpaceRunnerButton creditsButton = new SpaceRunnerButton("CREDITS");
		addMenuButton(creditsButton);

		creditsButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				showSubScene(creditsSubScene);
			}
		});
	}

	private void createExitButton() {
		SpaceRunnerButton exitButton = new SpaceRunnerButton("EXIT");
		addMenuButton(exitButton);
		
		exitButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				mainStage.close();
			}
		});
	}

	private void createBackground() {
		Image backgroundImage = new Image("view/resources/blue.png", 256, 256, false, true);
		BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT,
				BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
		mainPane.setBackground(new Background(background));
	}

	private void createLogo() {
		Text logo = new Text("SPACE RUNNER");
		try {
			logo.setFont(Font.loadFont(new FileInputStream("src/model/resources/kenvector_future.ttf"), 50));
		} catch (FileNotFoundException e) {
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
