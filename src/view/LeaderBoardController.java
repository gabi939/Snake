package view;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

import Model.Player;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * 
 * @author Shany Klein
 *
 */
public class LeaderBoardController implements Initializable {
	
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
	private TableView<Player> leaderTable;

	@FXML
	private TableColumn<Player, Integer> scoreColumn;

	@FXML
	private TableColumn<Player, String> nameColumn;

	@FXML
	private TableColumn<Player, Date> dateColumn;
	

	// =============================== Methods ==============================

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// initialize leader table columns
		scoreColumn.setCellValueFactory(new PropertyValueFactory<>("score")); // According to variable name
		nameColumn.setCellValueFactory(new PropertyValueFactory<>("name")); // Same here
		dateColumn.setCellValueFactory(new PropertyValueFactory<>("playDate")); // Same here

		// initialize the player list to appear in the table
		ArrayList<Player> players = ViewLogic.sysdata.getHistory();
		ObservableList<Player> p = FXCollections.observableArrayList(players);
		leaderTable.setItems(p);
		leaderTable.refresh();

	}

	protected void closeWindow() {
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
