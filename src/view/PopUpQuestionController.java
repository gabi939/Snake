package view;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.ArrayBlockingQueue;

import Model.Answer;
import Model.Question;
import Utils.E_Difficulty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXRadioButton;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * 
 * @author Shany Klein
 *
 */
public class PopUpQuestionController implements Initializable{

	// ============================== Variables =============================

	@FXML
	private AnchorPane pane;

	@FXML
	private Label questionLabel;

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

	// =============================== Methods ==============================

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		E_Difficulty diff = E_Difficulty.MEDIUM; //TODO
		question = ViewLogic.sysdata.fetchQuestion(diff);

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

		// for testing
		for (Answer a: question.getAnswers()) {
			System.out.println(a);
		};
		System.out.println(question.getCorrect_ans());
	}

	// ========================== Action Listeners ==========================

	@FXML
	private void answerQuestion() {
		int correctAnsID = 0;
		try {
			switch(((RadioButton) answerGroup.getSelectedToggle()).getId()) {
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
			System.out.println("Nothing is selected");
		}

		if (correctAnsID == 0) {
			System.out.println("Select something");
		} else if (question.getCorrect_ans() == correctAnsID){ // correct answer
			ViewLogic.playGameController.score += question.getScore();
			handleAlertAndWindow(AlertType.INFORMATION, "Congrats! :D", "You received "+ question.getScore() + " points");
		} else { // wrong answer
			ViewLogic.playGameController.score -= question.getPenalty();
			handleAlertAndWindow(AlertType.ERROR, "Uh oh! :(", "You lost "+ question.getPenalty() + " points");
		}
	}

	private void handleAlertAndWindow(AlertType at, String header, String context) {
		Alert alert = new Alert(at);
		alert.setTitle("Question Result");
		alert.setHeaderText(header);
		alert.setContentText(context);
		alert.showAndWait();
		closeWindow();
	}

	private void closeWindow() {
		((Stage) pane.getScene().getWindow()).close();
	}

}
