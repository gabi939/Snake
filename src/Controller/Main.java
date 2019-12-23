package Controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import Model.Player;
import Model.Question;
import Utils.E_Difficulty;
import Controller.Sysdata;
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
		
		/*
		Sysdata.getInstance().addGameHistory((new Player("Shany", 1111, new Date())));
		Sysdata.getInstance().addGameHistory((new Player("Nareed", 9000, new Date())));
		Sysdata.getInstance().addGameHistory((new Player("David", 666, new Date())));
		Sysdata.getInstance().addGameHistory((new Player("Turtle", 0, new Date())));
		*/
		ViewLogic.initUI();
	}

	public static void main(String args[]) throws Exception {
		launch(args);
	}
}
