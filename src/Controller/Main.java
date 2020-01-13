package Controller;

import javafx.application.Application;
import javafx.stage.Stage;
import view.ViewLogic;

/**
 * Main Class
 * 
 * @author Shany Klein
 * @author Gabi Malin
 * @author David Duchovni
 * @author Nareed Hashem
 */

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		ViewLogic.initUI();
	}

	public static void main(String args[]) throws Exception {
		launch(args);
	}
}
