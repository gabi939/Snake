package view;

import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import Model.Answer;
import Model.Question;
import Utils.E_Difficulty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;


/**
 * Class Questions Management Controller ~ GUI Control that allows managing the game's questions 
 * 
 * @author Shany Klein
 *
 */
public class QuestionsManagementController implements Initializable {

	// ============================== Variables =============================

	@FXML
	private AnchorPane pane;

	@FXML
	private TableView<Question> questionsTable;

	@FXML
	private TableColumn<Question, Integer> questionIDColumn;

	@FXML
	private TableColumn<Question, String> questionColumn;

	@FXML
	private TableColumn<Question, E_Difficulty> questionDifficultyColumn;

	@FXML
	private Button addBtn;

	@FXML
	private Button updateBtn;

	@FXML
	private Button deleteBtn;

	@FXML
	private Label errorLabel;

	@FXML
	private Label questionLabel;

	@FXML
	private TableView<Answer> answersTable;

	@FXML
	private TableColumn<Answer, Integer> answerIDColumn;

	@FXML
	private TableColumn<Answer, String> answerColumn;

	@FXML
	private TableColumn<Answer, Boolean> isCorrectColumn;

	protected ArrayList<Question> questions;

	protected Question question;

	// =============================== Methods ==============================

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ViewLogic.questionsManagementController = this;

		questionLabel.setWrapText(true);

		// initialize tables' columns
		questionIDColumn.setCellValueFactory(new PropertyValueFactory<>("questionID")); // According to variable name
		questionColumn.setCellValueFactory(new PropertyValueFactory<>("content")); // Same here
		questionDifficultyColumn.setCellValueFactory(new PropertyValueFactory<>("difficulty")); // Same here

		answerIDColumn.setCellValueFactory(new PropertyValueFactory<>("answerID")); // According to variable name
		answerColumn.setCellValueFactory(new PropertyValueFactory<>("content")); // Same here
		isCorrectColumn.setCellValueFactory(new PropertyValueFactory<>("isCorrect")); // Same here

		// initialize the Question list to appear in the table
		setQuestionTable();
	}

	@SuppressWarnings("unused")
	private void closeWindow() {
		((Stage) pane.getScene().getWindow()).close();
	}
/**
 * this method allows adding a new question (via the edit question controller)
 * @param event
 */
	@FXML
	private void addQuestion(ActionEvent event) {
		question = null;
		ViewLogic.editQuestionWindow();
	}

	/**
	 * this method handles deleting a question
	 * @param event
	 */
	@FXML
	private void deleteQuestion(ActionEvent event) {
		Question q = questionsTable.getSelectionModel().getSelectedItem();
		// a question wasn't selected
		if (q == null)
			errorLabel.setText("Please select a question to delete.");
		
		else {
			// set the alert's properties
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Information Dialog");
			alert.setHeaderText("Are you sure you want to delete this question?");
			alert.setContentText("\""+q.getContent()+"\"");
			ButtonType buttonTypeYes = new ButtonType("Yes", ButtonData.YES);
			ButtonType buttonTypeNo = new ButtonType("No", ButtonData.NO);
			alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);
			Optional<ButtonType> answer = alert.showAndWait();
		
			// yes was pressed, so we delete the question
			if (answer.get().getButtonData() == ButtonData.YES) {
				try {
					ViewLogic.sysdata.removeQuestion(q);
					setQuestionTable();
					errorLabel.setText("Question deleted successfully.");
				} catch (Exception e) {
					errorLabel.setText("Question cannot be deleted.");
				}
			}
		}
	}
	
	/**
	 * this method allows editing a question by opening the edit question window
	 * @param event
	 */
	@FXML
	private void updateQuestion(ActionEvent event) {
		question = questionsTable.getSelectionModel().getSelectedItem();
		if (question == null)
			errorLabel.setText("Please select a question to update.");
		else
			ViewLogic.editQuestionWindow();
	}

	/**
	 * this method makes the items appear in the questions table
	 */
	protected void setQuestionTable() {
		questions = ViewLogic.sysdata.getQuestionsarr();
		ObservableList<Question> qs = FXCollections.observableArrayList(questions);
		questionsTable.setItems(qs);
		questionsTable.refresh();
		setAnswerTable();
	}

	/**
	 * this method makes the items appear in the answers table
	 */
	@FXML
	private void setAnswerTable() {
		question = questionsTable.getSelectionModel().getSelectedItem();
		if (question != null) {
			questionLabel.setText(question.getContent());
			ArrayList<Answer> answers = question.getAnswers();
			ObservableList<Answer> ans = FXCollections.observableArrayList(answers);
			answersTable.setItems(ans);
		}
		else {
			questionLabel.setText("");
			answersTable.getItems().clear();
		}
		answersTable.refresh();
	}

}
