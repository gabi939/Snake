
package view;

import java.io.IOException;
import java.net.URL;

import Controller.ManageGame;
import Controller.Sysdata;
import Utils.Consts;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

/**
 * Class ViewLogic ~ manages the windows in the system
 * 
 * @author Shany Klein
 * @author Gabi Malin
 * @author David Duchovni
 * @author Nareed Hashem
 * 
 */
public class ViewLogic {
	// ------------------------------ Variables ------------------------------

	protected static final Rectangle2D FULL_SCREEN = Screen.getPrimary().getBounds();
	protected static final Rectangle2D VISIBLE_SCREEN = Screen.getPrimary().getVisualBounds();
	protected static Sysdata sysdata = Sysdata.getInstance();
	protected static QuestionsManagementController questionsManagementController;
	public static PlayGameController playGameController;

	// ------------------------------ Methods ------------------------------
	/**
	 * this method starts the windows in the system
	 */
	public static void initUI() {
		mainMenuWindow();
	}

	/**
	 * this method manages the window properties
	 * 
	 * @param fxmlLocation
	 * @param stage
	 * @param prefWidth
	 * @param prefHeight
	 * @param minWidth
	 * @param minHeight
	 * @param maxWidth
	 * @param maxHeight
	 * @param resizable
	 * @param title
	 * @param waitFor
	 */
	protected static void newWindow(URL fxmlLocation, Stage stage, String title, boolean resizable, boolean waitFor) {
		//
		Platform.runLater(new Runnable() {

			@Override
			public void run() {
				try {
					FXMLLoader loader = new FXMLLoader(fxmlLocation);
					Parent root = loader.load();
					Scene scene = new Scene(root);

					Image image = new Image(Consts.SNAKE_LOGO);
					stage.getIcons().setAll(image);

					stage.setScene(scene);

					stage.setResizable(resizable);

					if (title != null && !title.isEmpty() && !title.trim().isEmpty())
						stage.setTitle(title);

					if (waitFor)
						stage.initModality(Modality.APPLICATION_MODAL);

					stage.showAndWait();

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
	}

	// =================================== Game ===================================
	protected static void gameWindow() {
		Stage stage = new Stage();

		newWindow(ViewLogic.class.getResource("PlayGame.fxml"), stage, "Snake!", false, false);

	}

	// =============================== Main Menu ===============================
	/**
	 * Open Main Menu Window
	 */

	protected static void mainMenuWindow() {
		Stage stage = new Stage();

		newWindow(ViewLogic.class.getResource("MainMenu.fxml"), stage, "Snake!", false, false);
	}

	// ============================= Leader Board =============================
	/**
	 * Open Leader Board Window
	 */

	protected static void leaderBoardWindow() {
		Stage stage = new Stage();

		newWindow(ViewLogic.class.getResource("LeaderBoard.fxml"), stage, "Leader Board", false, false);
	}

	// ============================= Settings =============================
	/**
	 * Open Settings Window
	 */

	protected static void settingsWindow() {
		Stage stage = new Stage();

		newWindow(ViewLogic.class.getResource("Settings.fxml"), stage, "Settings", false, false);
	}

	// ============================= Enter Name =============================
	/**
	 * Open Enter Name Window
	 */

	protected static void enterNameWindow() {
		Stage stage = new Stage();
		stage.setOnCloseRequest(new EventHandler<WindowEvent>() {

			@Override
			public void handle(WindowEvent event) {
				stage.close();
				mainMenuWindow();
			}
		});
		newWindow(ViewLogic.class.getResource("EnterName.fxml"), stage, "What's your name?", false, true);
	}

	// ============================= Play Game =============================
	/**
	 * Open Play Game Window
	 */

	protected static void playGameWindow() {
		Stage stage = new Stage();

		newWindow(ViewLogic.class.getResource("PlayGame.fxml"), stage, "Play Snake!", false, false);

		stage.setOnCloseRequest(new EventHandler<WindowEvent>() {

			@Override
			public void handle(WindowEvent event) {
				ManageGame.getInstance().gameOver();
				stage.close();
			}
		});

	}

	// ============================= Questions Manager =============================
	/**
	 * Open Question Management Window
	 */

	protected static void questionsManagementWindow() {
		Stage stage = new Stage();

		newWindow(ViewLogic.class.getResource("QuestionsManagement.fxml"), stage, "Questions Management", false, true);
	}

	// ============================= Add/Update Question
	// =============================
	/**
	 * Open Edit Question Window
	 */

	protected static void editQuestionWindow() {
		Stage stage = new Stage();

		newWindow(ViewLogic.class.getResource("EditQuestion.fxml"), stage, "Edit a Question", false, true);
	}

	// ============================= Pop Up Question =============================
	/**
	 * Open Pop Up Question Window
	 */

	public static void popUpQuestionWindow() {
		Stage stage = new Stage();
		stage.initStyle(StageStyle.UNDECORATED);
		newWindow(ViewLogic.class.getResource("PopUpQuestion.fxml"), stage, "Answer a Question", false, true);
	}

	// ============================= How To Play =============================
	/**
	 * Open How To Play Window
	 */

	protected static void howToPlayWindow() {
		Stage stage = new Stage();

		newWindow(ViewLogic.class.getResource("HowToPlay.fxml"), stage, "How To Play?", false, false);
	}

}
