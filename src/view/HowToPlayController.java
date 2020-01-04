package view;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * 
 * @author Shany Klein
 *
 */
public class HowToPlayController implements Initializable{
	
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

	// =============================== Methods ==============================

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	private void closeWindow() {
		((Stage) pane.getScene().getWindow()).close();
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
