package view;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXToggleButton;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

/**
 * 
 * @author Shany Klein
 *
 */
public class SettingsController implements Initializable{

	// ============================== Variables =============================

	@FXML
	private AnchorPane pane;

	@FXML
	private Button homeBtn;

	@FXML
	private Button playBtn;

	@FXML
	private Button leaderBoardBtn;

	@FXML
	private Button settingsBtn;

	@FXML
	private Button howToPlayBtn;

	@FXML
	private Button exitBtn;

	@FXML
	private Button manageQuestionsBtn;

	@FXML
	private Button deleteScoreHistoryBtn;

	@FXML
	private JFXToggleButton themeToggle;

	@FXML
	private JFXToggleButton musicToggle;

	@FXML
	private JFXSlider speedSlider;

	// =============================== Methods ==============================

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

	protected void closeWindow() {
		((Stage) pane.getScene().getWindow()).close();
	}


	// TODO ADD OTHER METHODS HERE


	// ---------------------------

	@FXML
	private void deleteHistoryClicked(ActionEvent event) {
		//TODO
	}

	@FXML
	private void questionManagementClicked(ActionEvent event) {
		//closeWindow();
		ViewLogic.questionsManagementWindow();
	}

	// ======================== Menu Action Listeners =======================

	@FXML
	private void homeClicked(ActionEvent event) {
		closeWindow();
		ViewLogic.mainMenuWindow();
	}

	@FXML
	private void exitClicked(ActionEvent event) {
		closeWindow();
		//TODO
	}

	@FXML
	private void howToPlayClicked(ActionEvent event) {
		closeWindow();
		ViewLogic.howToPlayWindow();
	}

	@FXML
	private void leaderBoardClicked(ActionEvent event) {
		closeWindow();
		ViewLogic.leaderBoardWindow();
	}

	@FXML
	private void playClicked(ActionEvent event) {
		closeWindow();
		ViewLogic.enterNameWindow();
	}

	@FXML
	private void settingsClicked(ActionEvent event) {
		closeWindow();
		ViewLogic.settingsWindow();
	}

}
