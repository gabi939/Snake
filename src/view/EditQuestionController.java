package view;

import java.net.URL;
import java.util.ResourceBundle;

import Model.Question;
import Utils.E_Difficulty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import com.jfoenix.controls.JFXCheckBox;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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
	    private JFXCheckBox ans1Check;

	    @FXML
	    private TextField ans1TextField;

	    @FXML
	    private JFXCheckBox ans2Check;

	    @FXML
	    private TextField ans2TextField;

	    @FXML
	    private JFXCheckBox ans3Check;

	    @FXML
	    private TextField ans3TextField;

	    @FXML
	    private JFXCheckBox ans4Check;

	    @FXML
	    private TextField ans4TextField;

	    @FXML
	    private Label errorLabel;

	    @FXML
	    private Button addBtn;

	    @FXML
	    private ComboBox<E_Difficulty> difficultyCombo;
	
	private Question question;
	
	private Question old;
	
	// =============================== Methods ==============================
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		question = ViewLogic.questionsManagementController.question;
		difficultyCombo.getItems().setAll(E_Difficulty.values());
		
		// update question
		if (question != null) {
			old = question;
			questionTextField.setText(question.getContent());
			
			ans1Check.setSelected(question.getAnswers().get(0).isCorrect());
			ans1TextField.setText(question.getAnswers().get(0).getContent());
			
			ans2Check.setSelected(question.getAnswers().get(1).isCorrect());
			ans2TextField.setText(question.getAnswers().get(1).getContent());
			
			ans3Check.setSelected(question.getAnswers().get(2).isCorrect());
			ans3TextField.setText(question.getAnswers().get(2).getContent());
			
			ans4Check.setSelected(question.getAnswers().get(3).isCorrect());
			ans4TextField.setText(question.getAnswers().get(3).getContent());
			
			difficultyCombo.getSelectionModel().select(question.getDifficulty());
		}
		
	}
	
	// ========================== Action Listeners ==========================

		@FXML
		private void saveQuestion() {
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
		
//			int rnum, rprice;
//			try {
//				rnum = Integer.parseInt(roomNumTextField.getText());
//				if (rnum > 0) {
//					try {
//						rprice = Integer.parseInt(priceTextField.getText());
//						if (rprice > 0) {
//							if (roomTypeGroup.getSelectedToggle() != null) {
//								String rtype = Consts.STANDARD;
//
//								if (roomTypeGroup.getSelectedToggle().equals(standardBut)) {
//									rtype = Consts.STANDARD; 
//								}
//								else if (roomTypeGroup.getSelectedToggle().equals(suiteBut)) {
//									rtype = Consts.SUITE; 
//								} else
//									errorLabel.setText("hello.");
//
//								if (bedsSlider.getValue() >= 0) {
//
//									try {
//										room.setCruiseShipID(IDTextField.getText());
//										room.setRoomNumber(rnum);
//										Double d = bedsSlider.getValue();
//										room.setBedsAmount(d.intValue());
//										room.setRoomType(rtype);
//										room.setPrice(rprice);
//										if (ViewLogic.controller.canAddRoomToShip(room, update)) {
//										if (update) {
//											ViewLogic.controller.updateRoom(room);
//											errorLabel.setText("Room updated successfully.");
//										}
//
//										else if (!update && ViewLogic.controller.insertRoom(room))
//											errorLabel.setText("Room added successfully. Add another?");
//										else if (!update)
//											errorLabel.setText("Room already exists.");
//										ViewLogic.adminShipsRoomsScreenController.setRoomTable();
//										} else
//											errorLabel.setText("The room's beds amount exceeds\nthe allowed number of people.");
//									} catch(Exception e) {
//										e.printStackTrace();
//										errorLabel.setText("Error occured.");
//									}
//								} else
//									errorLabel.setText("Please select a bed amount.");
//							} else
//								errorLabel.setText("Please select a room type.");
//						} else
//							errorLabel.setText("Price must be a positive number.");
//					} catch (NumberFormatException e) {
//						errorLabel.setText("Price must be an integer.");
//					}
//				} else
//					errorLabel.setText("Room Number must be a positive number.");
//			} catch (NumberFormatException e) {
//				errorLabel.setText("Room Number must be an integer.");
//			}
		}
}
