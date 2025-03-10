package view;

import java.net.URL;
import java.util.ResourceBundle;

import Controller.Sysdata;
import Model.Player;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Class Enter Name Controller ~ GUI Control that allows entering the player's name
 * in the beginning of them game 
 * 
 * @author Shany Klein
 * @author David Duchovni
 *
 */
public class EnterNameController implements Initializable{

	// ============================== Variables =============================

	@FXML
	private AnchorPane pane;

	@FXML
	private Button playBtn;

	@FXML
	private TextField nameTextField;

	@FXML
	private Label errorLabel;

	// =============================== Methods ==============================

	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}

	private void closeWindow() {
		((Stage) pane.getScene().getWindow()).close();
	}

	// ======================== Action Listeners =======================

	@FXML
	private void playClicked() {
		String playerName = "";
		playerName = nameTextField.getText();

		playerName = playerName.trim(); // Removing white spaces
		if (!playerName.isEmpty()) {

			// setting the current player in sysdata
			Player p = new Player(playerName);
			Sysdata.getInstance();
			Sysdata.setPlayer(p);
			System.out.println(p);

			closeWindow();
			ViewLogic.playGameWindow();
		} else
			errorLabel.setText("Please enter your name");
	}

	/**
	 * this method enables playing after pressing Enter
	 */
	@FXML
	private void onKeyReleased(KeyEvent e) {
		if (e.getCode() == KeyCode.ENTER)
			playClicked();
	}
}
