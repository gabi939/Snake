package view;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

import com.jfoenix.controls.JFXColorPicker;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXToggleButton;

import Controller.Sysdata;
import Utils.Consts;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

/**
 * 
 * @author Shany Klein
 *
 */
public class SettingsController implements Initializable {

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
	private Button deleteScoreHistoryBtn;

	@FXML
	private Button manageQuestionsBtn;

	@FXML
	private Button resetToDefaultBtn;

	@FXML
	private JFXToggleButton musicToggle;

	@FXML
	private JFXToggleButton soundFXToggle;

	@FXML
	private JFXSlider snakeSpeedSlider;

	@FXML
	private JFXSlider mouseSpeedSlider;

	@FXML
	private JFXColorPicker bgColorPicker;

	@FXML
	private JFXColorPicker snakeBodyColorPicker;

	@FXML
	private ToggleGroup snakeHeadGroup;

	@FXML
	private JFXRadioButton defaultHeadRadio;

	@FXML
	private JFXRadioButton gabiHeadRadio;

	@FXML
	private JFXRadioButton davidHeadRadio;

	@FXML
	private JFXRadioButton shanyHeadRadio;

	@FXML
	private JFXRadioButton nareedHeadRadio;

	private static GameSettings gs = GameSettings.getInstance();

	private String chosenHead = "";

	// =============================== Methods ==============================

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		applyProperties();

	}

	private void closeWindow() {
		saveChanges();
		((Stage) pane.getScene().getWindow()).close();
	}

	/**
	 * this method saves the settings in Game Settings, and they'll be applied in the game when it runs
	 */
	private void saveChanges() {

		gs.setMusic(musicToggle.isSelected());
		gs.setSoundfx(soundFXToggle.isSelected());

		gs.changeSnakeSpeed(snakeSpeedSlider.getValue());
		gs.changeMouseSpeed(mouseSpeedSlider.getValue());

		gs.changeSnakeColor(snakeBodyColorPicker.getValue());
		gs.changeThemeColor(bgColorPicker.getValue());

		try {
			switch (((RadioButton) snakeHeadGroup.getSelectedToggle()).getId()) {
			case "gabiHeadRadio":
				chosenHead = Consts.GABI_HEAD;
				break;
			case "davidHeadRadio":
				chosenHead = Consts.DAVID_HEAD;
				break;
			case "shanyHeadRadio":
				chosenHead = Consts.SHANY_HEAD;
				break;
			case "nareedHeadRadio":
				chosenHead = Consts.NAREED_HEAD;
				break;
			default: // defaultHeadRadio
				chosenHead = Consts.DEFUALT_SNAKE_HEAD;
				break;
			}
			gs.changeSnakeHead(chosenHead);

		} catch (Exception e) {
			System.out.println("Nothing is selected");
		}
	}

	/*
	 * this method applies the settings as they're saved in Game Settings
	 */
	private void applyProperties() {
		// apply music + soundfx settings:
		musicToggle.setSelected(gs.isMusic());
		soundFXToggle.setSelected(gs.isMusic());

		// apply snake + mouse speed settings:
		snakeSpeedSlider.setValue(gs.getSnakeSpeed());
		mouseSpeedSlider.setValue(gs.getMouseSpeed());

		// apply color settings:
		bgColorPicker.setValue(gs.getThemeColor());
		PlayGameController.colorReset();
		snakeBodyColorPicker.setValue(gs.getSnakeBodyColor());
		gs.changeSnakeColor(snakeBodyColorPicker.getValue());

		// apply head settings:
		chosenHead = gs.getSnakeHead();
		try {
			switch (chosenHead) {
			case Consts.GABI_HEAD:
				gabiHeadRadio.setSelected(true);
				break;
			case Consts.DAVID_HEAD:
				davidHeadRadio.setSelected(true);
				break;
			case Consts.SHANY_HEAD:
				shanyHeadRadio.setSelected(true);
				break;
			case Consts.NAREED_HEAD:
				nareedHeadRadio.setSelected(true);
				break;
			default: // DEFUALT_SNAKE_HEAD
				defaultHeadRadio.setSelected(true);
				break;
			}

		} catch (Exception e) {
			System.out.println("Nothing is selected");
		}
	}

	// ---------------------------
/**
 * this method handles deleting all the history
 * @param event
 */
	@FXML
	private void deleteHistoryClicked(ActionEvent event) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Information Dialog");
		alert.setHeaderText("Are you sure you want to delete ALL the games' history?");
		alert.setContentText("By pressing YES the games' history won't be able to be restored");
		
		ButtonType buttonTypeYes = new ButtonType("Yes", ButtonData.YES);
		ButtonType buttonTypeNo = new ButtonType("No", ButtonData.NO);
		alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);
		Optional<ButtonType> answer = alert.showAndWait();
		
		if (answer.get().getButtonData() == ButtonData.YES) {
			Sysdata.getInstance().deleteGameHistory();
		}
	}

	@FXML
	private void questionManagementClicked(ActionEvent event) {
		ViewLogic.questionsManagementWindow();
	}

	@FXML
	private void resetToDefaultClicked(ActionEvent event) {
		gs.resetToDefault();
		applyProperties();
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
