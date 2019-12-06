package Controller;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.stage.Stage;
import view.ViewLogic;

/**
 * @author Mateusz Krawczyk The Main class
 */
public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {

		
		  // Controller object, default constructor sets up basic game parameters and
	
		/*
		 * Controller setUpGame = new Controller(); primaryStage = setUpGame.getStage();
		 * // Stage can't change size primaryStage.setResizable(false); // Show the
		 * //stage and actual scenes primaryStage.show();
		 */
		
		ViewLogic.initUI();
	}

	public static void main(String args[]) throws Exception {
		launch(args);
	}
}
