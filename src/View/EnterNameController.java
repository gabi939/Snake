package View;

import java.net.URL;
import java.util.ResourceBundle;

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
 * 
 * @author Shany Klein
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

	protected String playerName = "";

	// =============================== Methods ==============================

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

	protected void closeWindow() {
		((Stage) pane.getScene().getWindow()).close();
	}

	// ======================== Menu Action Listeners =======================

	@FXML
	private void playClicked() {
		playerName = nameTextField.getText();

		if (playerName != null) {
			playerName = playerName.trim(); // Removing white spaces
			if (!playerName.isEmpty()) {
				closeWindow();
				ViewLogic.playGameWindow();
			} else
				errorLabel.setText("Please enter your name");
		} else
			errorLabel.setText("Please enter your name");
	}
	
	/**
	 * This method enables playing after pressing Enter
	 */
	@FXML
	private void onKeyReleased(KeyEvent e) {
		if (e.getCode() == KeyCode.ENTER)
			playClicked();
	}
}
