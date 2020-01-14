package view;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXRadioButton;

import Controller.ManageGame;
import Model.Question;
import Utils.Consts;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Class Pop Up Question Controller ~ GUI Control that allows answering a question while in game 
 * 
 * @author Shany Klein
 * @author Gabi Malin
 *
 */
public class PopUpQuestionController implements Initializable {

	// ============================== Variables =============================

	@FXML
	private AnchorPane pane;

	@FXML
	private Label questionLabel;

	@FXML
	private Label errorLabel;

	@FXML
	private ToggleGroup answerGroup;

	@FXML
	private JFXRadioButton ans1Check;

	@FXML
	private JFXRadioButton ans2Check;

	@FXML
	private JFXRadioButton ans3Check;

	@FXML
	private JFXRadioButton ans4Check;

	@FXML
	private Button sendBtn;

	private Question question;

	private ManageGame control;

	// =============================== Methods ==============================

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		control = ManageGame.getInstance();
		question = control.getQuestionEaten().getQuestion();

		// hide error label
		errorLabel.setVisible(false);

		// initiate question values
		questionLabel.setText(question.getContent());

		ans1Check.setSelected(false);
		ans1Check.setText(question.getAnswers().get(0).getContent());

		ans2Check.setSelected(false);
		ans2Check.setText(question.getAnswers().get(1).getContent());

		ans3Check.setSelected(false);
		ans3Check.setText(question.getAnswers().get(2).getContent());

		ans4Check.setSelected(false);
		ans4Check.setText(question.getAnswers().get(3).getContent());
	}

	private void closeWindow() {
		((Stage) pane.getScene().getWindow()).close();
		ViewLogic.playGameController.getControl().continueGame();
	}

	// ========================== Action Listeners ==========================

	/**
	 * This method enables playing after pressing Enter
	 */
	@FXML
	private void onKeyReleased(KeyEvent e) {
		if (e.getCode() == KeyCode.ENTER)
			answerQuestion();
	}

	/**
	 * this method checks if the question has been answered and if it's the correct
	 * answer.
	 */
	@FXML
	private void answerQuestion() {
		int correctAnsID = 0;
		try {
			switch (((RadioButton) answerGroup.getSelectedToggle()).getId()) {
			case "ans1Check":
				correctAnsID = 1;
				break;
			case "ans2Check":
				correctAnsID = 2;
				break;
			case "ans3Check":
				correctAnsID = 3;
				break;
			case "ans4Check":
				correctAnsID = 4;
				break;
			}
		} catch (Exception e) {
			errorLabel.setVisible(true);
		}

		// nothing is selected
		if (correctAnsID == 0) { 
			errorLabel.setVisible(true);
			// correct answer
		} else if (question.getCorrect_ans() == correctAnsID) {
			control.setScore(control.getScore() + question.getScore());
			handleAlertAndWindow(AlertType.INFORMATION, "Congrats! :D",
					"You received " + question.getScore() + " points");
			// wrong answer
		} else {
			control.setScore(control.getScore() + question.getPenalty());

			handleAlertAndWindow(AlertType.ERROR, "Uh oh! :(",
					"You lost " + question.getPenalty() + " points");
		}

		// reset the eaten question
		ViewLogic.playGameController.getControl().setQuestionEaten(null);
	}

	private void handleAlertAndWindow(AlertType at, String header, String context) {
		Alert alert = new Alert(at);
		alert.setTitle("Question Result");
		alert.setHeaderText(header);
		alert.setContentText(context);
		alert.showAndWait();

		closeWindow();
	}

	@FXML
	/**
	 * this method sets the correct answer when pressing A
	 * @param e
	 */
	private void answeringCheat(KeyEvent e) {
		if (e.getCode().equals(KeyCode.A) || e.getCharacter().toUpperCase().equals("A")) {
			control.setScore(control.getScore() + question.getScore());
			handleAlertAndWindow(AlertType.INFORMATION, "Congrats Cheater! ;)",
					"You received " + question.getScore() + " points");
			// reset the eaten question
			ViewLogic.playGameController.getControl().setQuestionEaten(null);
		}
	}
}
