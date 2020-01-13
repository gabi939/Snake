package view;

import java.net.URL;
import java.util.Optional;
import java.util.Random;
import java.util.ResourceBundle;

import Controller.ManageGame;
import Controller.Sysdata;
import Model.Board;
import Model.BodyPart;
import Model.Mouse;
import Model.Snake;
import Utils.Consts;
import Utils.GameState;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

/**
 * Class Play Game Controller ~ GUI Control that defines the board in the game
 * 
 * @author Shany Klein
 * @author Gabi Malin
 * @author David Duchovni
 * @author Nareed Hashem
 * 
 */
public class PlayGameController implements Initializable {

	// ============================== Variables =============================

	@FXML
	private AnchorPane pane;

	@FXML
	private Button nameBtn;

	@FXML
	private Button scoreBtn;

	@FXML
	private Button lifeBtn;

	@FXML
	private Button homeBtn;

	@FXML
	private Button settingsBtn;

	@FXML
	private Label pausePlayLabel;

	@FXML
	private Pane canvas;

	protected GameState state;

	private Snake snake;

	private BodyPart head;

	private Mouse mouse;

	private ManageGame control;

	private boolean up, down, right, left, pause, resume, start;

	/**
	 * The movement in X and Y-axis
	 */
	private int dx, dy;

	/**
	 * Boolean block to prevent pressing keys too fast, so that the snake's head
	 * could turn around. For example, when snake was moving left, pressing the up
	 * and right key very fast could just change the head's direction to right,
	 * without changing the position in Y-axis, causing the head to hit the second
	 * part of it's body
	 */
	private boolean keyActive;

	private GameSettings gameSettings;

	// =============================== Methods ==============================

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		ViewLogic.playGameController = this;

		// make buttons unclickable
		nameBtn.setDisable(true);
		lifeBtn.setDisable(true);
		scoreBtn.setDisable(true);

		// create board and its controller
		control = ManageGame.getInstance();
		control.initGame();

		snake = control.getBoard().getSnake();
		mouse = control.getBoard().getMouse();
		head = snake.getHead();

		gameSettings = GameSettings.getInstance();

		// sets labels on the buttons
		lifeBtn.setText("Life: " + Integer.toString(snake.getLife()));
		scoreBtn.setText("Score: " + Integer.toString(control.getScore()));
		nameBtn.setText("Name: " + Sysdata.getPlayer().getName());

		// show pause/play label
		pausePlayLabel.setVisible(true);

		// delta movement and key status
		dx = dy = 0;
		up = down = right = left = pause = resume = start = false;
		keyActive = true;

		state = GameState.Started;

		// applies the properties that were chosen in the settings window
		canvas.setStyle("-fx-background-color:" + gameSettings.getConvertedThemeColor());
		Sound.toggleMusic(gameSettings.isMusic());
		//Check for Color mode! 
		colorMode();
		resume(snake.getSpeed(), mouse.getSpeed());

	}

	private void closeWindow() {
		((Stage) pane.getScene().getWindow()).close();

	}

	@FXML
	/**
	 * this method adds 1 life when pressing L and adds 100 points when pressing S
	 * 
	 * @param e
	 */
	private void addingCheats(KeyEvent e) {
		// add 1 life when pressing L
		if (e.getCharacter().toUpperCase().equals("L")) {
			control.addLife(Consts.ADD_LIFE);
			lifeBtn.setText("Life: " + Integer.toString(snake.getLife()));

		}
		// add 100 points when pressing S
		else if (e.getCharacter().toUpperCase().equals("S")) {
			control.addScore(Consts.ADD_HIDDEN_BONUS);
			scoreBtn.setText("Score: " + Integer.toString(control.getScore()));
		}
	}

	@FXML
	/**
	 * this method mutes or plays the game music when pressing M
	 * 
	 * @param e
	 */
	private void setMusic(KeyEvent e) {
		if (e.getCode() == KeyCode.M || e.getCharacter().toUpperCase().equals("M")) {
			// mutes music
			if (gameSettings.isMusic()) {
				Sound.stopMusic();
				gameSettings.setMusic(false);
			}
			// plays music
			else {
				gameSettings.setMusic(true);
				Sound.toggleMusic(gameSettings.isMusic());
			}
		}
	}

	// =============================== Menu Methods ==============================

	@FXML
	public void homeClicked() {
		control.colorReset();
		control.gameOver();
		closeWindow();
		ViewLogic.mainMenuWindow();
	}

	@FXML
	private void settingsClicked() {
		control.colorReset();
		control.gameOver();
		closeWindow();
		ViewLogic.settingsWindow();
	}

	@FXML
	/**
	 * Prevents window to close when pressing space or enter. It actually prevents
	 * using SPACE or ENTER keys to open the menu items
	 * 
	 * @param e
	 */
	private void consumeEnterOrSpace(KeyEvent e) {
		if (e.getCode() == KeyCode.ENTER || e.getCode() == KeyCode.SPACE)
			e.consume();
	}

	// =============================== Board Methods ==============================

	/**
	 * Method to handle pressed keys on scene given as argument
	 * 
	 * @param scene on which events are performed
	 */
	private void movement(Pane canvas) {

		canvas.getScene().setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent e) {

				switch (e.getCode()) {
				case UP:
					if (!down && keyActive && state == GameState.Running) {
						up = true;
						left = false;
						right = false;
						keyActive = false;
						if (state == GameState.Started) {
							start = true;
							resume(snake.getSpeed(), mouse.getSpeed());
						}

					}
					break;
				case DOWN:
					if (!up && keyActive && (left || right) && state == GameState.Running) {
						down = true;
						left = false;
						right = false;
						keyActive = false;

					}
					break;
				case LEFT:
					if (!right && keyActive && state == GameState.Running) {
						left = true;
						up = false;
						down = false;
						keyActive = false;
						if (state == GameState.Started) {
							start = true;
							resume(snake.getSpeed(), mouse.getSpeed());

						}
					}
					break;
				case RIGHT:
					if (!left && keyActive && state == GameState.Running) {
						right = true;
						up = false;
						down = false;
						keyActive = false;
						if (state == GameState.Started) {
							start = true;
							resume(snake.getSpeed(), mouse.getSpeed());
						}
					}
					break;
				case P: // pause or resume game
					pausePlayLabel.setVisible(false);
					if (state == GameState.Started)
						start = true;
					if (state == GameState.Finished) {
						start = true;
						resume(snake.getSpeed(), mouse.getSpeed());

					}
					if (state == GameState.Running || state == GameState.Paused) {
						if (pause == false) {
							pausePlayLabel.setVisible(true);
							control.pauseGame();
						} else {
							pausePlayLabel.setVisible(false);
							control.continueGame();
						}
					}
					break;
				case ESCAPE: // exit program
					System.exit(0);
					break;
				default:
					break;
				}
			}
		});

		canvas.setOnKeyReleased(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
			}
		});
	}

	/**
	 * The gameloop, handles user input, updates and renders the game
	 */
	public void resume(double snakeSpeed, double mouseSpeed) {

		AnimationTimer time = new AnimationTimer() {

			private double i = 1;

			@Override
			public void handle(long now) {
				// when moving up
				if (up && !down) {

					dy = -1;
					dx = 0;
				}
				// when moving down
				if (!up && down) {

					dy = 1;
					dx = 0;
				}
				// when moving left
				if (left && !right) {

					dy = 0;
					dx = -1;
				}
				// when moving right
				if (right && !left) {
					dy = 0;
					dx = 1;
				}
				// when game paused
				if (pause && !resume) {
					state = GameState.Paused;

					stop();
				}
				// when game resumed
				if (resume && !pause) {
					state = GameState.Running;
					resume = false;
				}
				// when game started or restarted
				if (start && (state == GameState.Finished || state == GameState.Started)) {
					restart();
					start = false;
				}
				// when game finished
				if (state == GameState.Finished) {
					pausePlayLabel.setVisible(true);
					stop();
				}
				// when game is running, make movement
				if (state == GameState.Running) {
					if (i % snakeSpeed == 0) {
						move(dx, dy);
						keyActive = true; // unlock possibility to press another key after snake made it's move
						update(); // updating the game parameters, positions, etc.

						if (i % mouseSpeed == 0)
							control.updateMousePosition();
					}

				}

				render();
				movement(canvas); // handling user key input on actual scene
				i++;

			}
		};
		// starting the timer
		control.setTime(time);

	}

	/**
	 * The render method, that displays the graphics
	 */
	public void render() {
		Board board = control.getBoard();
		canvas.getChildren().clear(); // clear canvas

		int helpX, helpY, snakeY, snakeX; // variables for loops

		// snake's head to canvas
		Shape c = new Circle(snake.getHead().getX(), snake.getHead().getY(), Consts.SIZE / 2);
		// set default head
		c.setFill(new ImagePattern(new Image(Consts.DEFUALT_SNAKE_HEAD)));
		if (Sysdata.getPlayer().getName().toLowerCase().contains("sloth")) {
			c.setFill(new ImagePattern(new Image(Consts.SLOTH_HEAD)));
		}
		// if the username contains the word tsvika, we will tsvika's head
		else if (Sysdata.getPlayer().getName().toLowerCase().contains("tsvika")) {
			c.setFill(new ImagePattern(new Image(Consts.TSVIKA_HEAD)));
		}
		// the head that was chosen in the settings window
		else if (gameSettings != null) {
			c.setFill(new ImagePattern(new Image(gameSettings.getSnakeHead())));
		}
		canvas.getChildren().add(c);

		// update snake on screen
		for (int i = 1; i < snake.getSize(); ++i) {
			snakeX = snake.getBodyPart(i).getX();
			snakeY = snake.getBodyPart(i).getY();
			c = new Circle(snakeX, snakeY, Consts.SIZE / 2);
			c.setFill(new GameObjectView(snake.getBodyPart(i)).getBody_color());
			// if the username contains the word sloth, we will get a snake on acid
			Sysdata.getInstance();
			if (Sysdata.getPlayer().getName().toLowerCase().contains("sloth")) {
				Random rand = new Random();
				double r = rand.nextFloat();
				double g = rand.nextFloat();
				double b = rand.nextFloat();
				Color randomColor = new Color(r, g, b, 1);
				gameSettings.changeSnakeColor(randomColor);
				c.setFill(new GameObjectView(snake.getBodyPart(i)).getBody_color());

			}
			// if the username contains the word tsvika, we will get tsvika mode
			else if (Sysdata.getPlayer().getName().toLowerCase().contains("tsvika")) {
				Color dimGray = new Color(0.205, 0, 0.205, 0.8);
				gameSettings.changeSnakeColor(dimGray);
				c.setFill(new GameObjectView(snake.getBodyPart(i)).getBody_color());

			}

			canvas.getChildren().add(c);
		}
		// loading fruits to canvas
		for (int i = 0; i < board.getObjects().size(); ++i) {
			helpX = board.getObjects().get(i).getX();
			helpY = board.getObjects().get(i).getY();
			c = new Circle(helpX, helpY, Consts.SIZE / 2);
			c.setFill(new ImagePattern(new GameObjectView(board.getObjects().get(i)).getImg()));
			canvas.getChildren().add(c);
		}

	}

	/**
	 * Restarting the game by setting basic parameters to their primary values
	 */
	private void restart() {
		state = GameState.Running;
		dx = dy = 0;
		up = down = left = right = false;

	}
	/**
	 * Color Mode! 
	 * - Start with 500 points if body and background are chosen to be same color!
	 */
	private void colorMode() {
		if(gameSettings.getSnakeBodyColor().equals(gameSettings.getThemeColor())){
			control.addScore(Consts.COLOR_MODE_BONUS);
		}
	}
	/**
	 * Method to handle snake's position and movement on board
	 * 
	 * @param dx - movement in X-axis, 1 for right, -1 for left
	 * @param dy - movement in Y-axis, 1 for down, -1 for up
	 */
	private void move(int dx, int dy) {
		control.move(dx, dy);
	}

	// =============================== Methods ==============================

	/**
	 * The update method
	 */
	private void update() {

		control.eatUpdate(head);
		state = control.checkCollision(head); // check if a fruit has been eaten

		lifeBtn.setText("Life: " + Integer.toString(snake.getLife()));
		scoreBtn.setText("Score: " + Integer.toString(control.getScore()));

		if (snake.getLife() == 0) {

			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					showGameOverMessage();
					control.colorReset();
					control.gameOver();
				}

			});
		}
	}

	/**
	 * this method shows alert when the game is over
	 */
	private void showGameOverMessage() {
		// show a game over alert
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Game Over!");
		alert.setHeaderText("Game Over!");
		alert.setContentText("Your score is " + control.getScore());

		// open main menu after ok is pressed
		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK)
			homeClicked();
	}

	// =========================== Getters & Setters ===========================

	public ManageGame getControl() {
		return control;
	}

	public boolean isPause() {
		return pause;
	}

	public void setPause(boolean pause) {
		this.pause = pause;
	}

	public boolean isResume() {
		return resume;
	}

	public void setResume(boolean resume) {
		this.resume = resume;
	}
}
