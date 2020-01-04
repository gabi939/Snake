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

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;

/**
 * 
 * @author Shany Klein
 *
 */
public class EditQuestionController implements Initializable{

	// ============================== Variables =============================

	@FXML
	private AnchorPane pane;

	@FXML
	private TextField questionTextField;

	@FXML
	private JFXRadioButton ans1Check;

	@FXML
	private TextField ans1TextField;

	@FXML
	private JFXRadioButton ans2Check;

	@FXML
	private TextField ans2TextField;

	@FXML
	private JFXRadioButton ans3Check;

	@FXML
	private TextField ans3TextField;

	@FXML
	private JFXRadioButton ans4Check;

	@FXML
	private TextField ans4TextField;

	@FXML
	private Label errorLabel;

	@FXML
	private Button addBtn;

	@FXML
	private ComboBox<E_Difficulty> difficultyCombo;

	@FXML
	private ToggleGroup answerGroup;

	private Question old;

	// =============================== Methods ==============================

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		old = ViewLogic.questionsManagementController.question;
		difficultyCombo.getItems().setAll(E_Difficulty.values());

		// initiate to empty values
		resetFields();


		// update question
		if (old != null) {
			questionTextField.setText(old.getContent());

			ans1Check.setSelected(old.getAnswers().get(0).getIsCorrect());
			ans1TextField.setText(old.getAnswers().get(0).getContent());

			ans2Check.setSelected(old.getAnswers().get(1).getIsCorrect());
			ans2TextField.setText(old.getAnswers().get(1).getContent());

			ans3Check.setSelected(old.getAnswers().get(2).getIsCorrect());
			ans3TextField.setText(old.getAnswers().get(2).getContent());

			ans4Check.setSelected(old.getAnswers().get(3).getIsCorrect());
			ans4TextField.setText(old.getAnswers().get(3).getContent());

			difficultyCombo.getSelectionModel().select(old.getDifficulty());
		}

	}

	private void resetFields() {
		questionTextField.setText("");

		ans1TextField.setText("");
		ans2TextField.setText("");
		ans3TextField.setText("");
		ans4TextField.setText("");

		ans1Check.setSelected(false);
		ans2Check.setSelected(false);
		ans3Check.setSelected(false);
		ans4Check.setSelected(false);

		difficultyCombo.getSelectionModel().clearSelection();
	}
	// ========================== Action Listeners ==========================

	@FXML
	private void saveQuestion() {

		String q = questionTextField.getText().trim();
		String ans1 = ans1TextField.getText().trim();
		String ans2 = ans2TextField.getText().trim();
		String ans3 = ans3TextField.getText().trim();
		String ans4 = ans4TextField.getText().trim();

		boolean ans1Correct = ans1Check.isSelected();
		boolean ans2Correct = ans2Check.isSelected();
		boolean ans3Correct = ans3Check.isSelected();
		boolean ans4Correct = ans4Check.isSelected();

		E_Difficulty diff = difficultyCombo.getSelectionModel().getSelectedItem();

		if (!q.isEmpty()) {
			if (!ans1.isEmpty()) {
				if (!ans2.isEmpty()) {
					if (!ans3.isEmpty()) {
						if (!ans4.isEmpty()) {
							if (ans1Correct || ans2Correct || ans3Correct || ans4Correct) {
								if (diff != null) {

									ArrayList<Answer> answers = new ArrayList<>(4);
									answers.add(new Answer(1, ans1, ans1Correct));
									answers.add(new Answer(2, ans2, ans2Correct));
									answers.add(new Answer(3, ans3, ans3Correct));
									answers.add(new Answer(4, ans4, ans4Correct));

									Question question = new Question(q, diff, answers);

									if (old != null) { // update question
										ViewLogic.sysdata.editQuestion(old, question);
										errorLabel.setText("Question updated successfully. Add a new question?");
										old = null;
									}
									else { // new question
										ViewLogic.sysdata.addQuestion(question);
										errorLabel.setText("Question added successfully. Add another?");
									}

									ViewLogic.questionsManagementController.setQuestionTable();
									resetFields();

								} else
									errorLabel.setText("Please select a difficulty level");
							} else
								errorLabel.setText("Please select the correct answer");
						} else
							errorLabel.setText("Please enter answer 4");
					} else
						errorLabel.setText("Please enter answer 3");							
				} else
					errorLabel.setText("Please enter answer 2");
			} else
				errorLabel.setText("Please enter answer 1");
		} else
			errorLabel.setText("Please enter a question");
	}
}
