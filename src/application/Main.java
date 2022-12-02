package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import view.ViewManager;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			ViewManager manager = new ViewManager();
			primaryStage = manager.getMainStage();
			primaryStage.setResizable(false);
			primaryStage.setTitle("Space Runner");
			primaryStage.getIcons().add(
					   new Image("view/resources/shipchooser/blue_life.png"));
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
