package view;

import java.net.URL;
import java.util.ResourceBundle;

import Controller.ManageGame;
import Model.Board;
import Model.BodyPart;
import Model.GameState;
import Model.Snake;
import Utils.Consts;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;

/**
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
	private Pane canvas;

	public int score = 0;

	public int life = 3;

	protected GameState state;

	private Snake snake;

	private BodyPart head;

	public Board board;

	private ManageGame control;

	private boolean up, down, right, left, pause, resume, start;

	private static int flag = 0;
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

	private AnimationTimer time;

	// =============================== Methods ==============================

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		ViewLogic.playGameController = this;

		// makes buttons unclickable
		nameBtn.setDisable(true);
		lifeBtn.setDisable(true);
		scoreBtn.setDisable(true);

		// sets labels on the buttons
		lifeBtn.setText("Life: " + Integer.toString(life));
		scoreBtn.setText("Score: " + Integer.toString(score));
		nameBtn.setText("Name: ");// + ViewLogic.enterNameController.playerName);

		// create board and its controller
		board = new Board();
		control = new ManageGame(board);
		snake = board.getSnake();
		head = snake.getHead();

		// delta movement and key status
		dx = dy = 0;
		up = down = right = left = pause = resume = start = false;
		keyActive = true;

		state = GameState.Started;

		setGameSettings();
		resume();

	}

	private void setGameSettings() {
		// TODO GameSettings will be added
		canvas.setStyle("-fx-background-color: YELLOW"); // + GameSettings.getInstance().getThemeColor());
	}

	@FXML
	protected void homeClicked() {
		closeWindow();
		ViewLogic.mainMenuWindow();
	}

	@FXML
	protected void settingsClicked() {
		closeWindow();
		ViewLogic.settingsWindow();
	}

	/**
	 * Close window
	 */
	private void closeWindow() {
		((Stage) pane.getScene().getWindow()).close();
		time.stop();
	}

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
							resume();
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
							resume();
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
							resume();
						}
					}
					break;
				case SPACE: // pause or resume game
					if (state == GameState.Running || state == GameState.Paused) {
						if (pause == false) {
							pause = true;
							resume = false;
						} else {
							resume = true;
							pause = false;
							resume();
						}
					}
					break;
				case ENTER: { // start or restart the game
					if (state == GameState.Started)
						start = true;
					if (state == GameState.Finished) {
						start = true;
						resume();
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
	private void resume() {

		time = new AnimationTimer() {

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
					System.out.println("heere");
					start = false;
				}
				// when game finished
				if (state == GameState.Finished) {
					stop();
				}
				// when game is running, make movement
				if (state == GameState.Running) {
					if (i % 4 == 0) {
						move(dx, dy);
						keyActive = true; // unlock possibility to press another key after snake made it's move
						update(); // updating the game parameters, positions, etc.

						if (i % 8 == 0)
							control.updateMousePosition();
					}
				}

				render();
				movement(canvas); // handling user key input on actual scene
				i++;

			}
		}; // starting the timer
		time.start();
	}

	public void buildWalls() {
		int helpX, helpY; // variables for loops
		for (int i = 0; i < board.getObstacles().size(); ++i) {
			helpX = board.getObstacles().get(i).getX();
			helpY = board.getObstacles().get(i).getY();
			Rectangle r = new Rectangle(helpX - (Consts.SIZE / 2), helpY - (Consts.SIZE / 2), Consts.SIZE, Consts.SIZE);
			r.setFill(new GameObjectView(board.getObstacles().get(i)).getBody_color());
			canvas.getChildren().add(r);
		}

	}
	/**
	 * The render method, that displays the graphics
	 */
	public void render() {
		canvas.getChildren().clear(); // clear canvas

		int helpX, helpY, snakeY, snakeX; // variables for loops

		// snake's head to canvas TODO
		Shape c = new Circle(snake.getHead().getX(), snake.getHead().getY(), Consts.SIZE / 2);
		// c.setFill(BodyPart.HEAD_COLOR);
		// Shape c = new Rectangle(snake.getHead().getX(), snake.getHead().getY(),
		// Consts.SIZE, Consts.SIZE);
		c.setFill(new ImagePattern(new Image(Consts.DAVID_HEAD)));// GameSettings.getInstance().getSnakeHead())));
																	// //TODO

		canvas.getChildren().add(c);

		// update snake on screen
		for (int i = 1; i < snake.getSize(); ++i) {
			snakeX = snake.getBodyPart(i).getX();
			snakeY = snake.getBodyPart(i).getY();
			c = new Circle(snakeX, snakeY, Consts.SIZE / 2);
			c.setFill(new GameObjectView(snake.getBodyPart(i)).getBody_color());
			canvas.getChildren().add(c);
		}
		// loading fruits to canvas
		for (int i = 0; i < board.getFruits().size(); ++i) {
			helpX = board.getFruits().get(i).getX();
			helpY = board.getFruits().get(i).getY();
			// c = new Rectangle(helpX, helpY, Consts.SIZE/2, Consts.SIZE/2);
			c = new Circle(helpX, helpY, Consts.SIZE / 2);
			c.setFill(new ImagePattern(new GameObjectView(board.getFruits().get(i)).getImg()));
			// c = new Circle(helpX, helpY, Consts.SIZE/2);
			// c.setFill(new GameObjectView(board.getFruits().get(i)).getBody_color());
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
		control.stopTimers();

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

	/**
	 * The update method
	 */
	private void update() {

		score = control.eatUpdate(head, score);
		state = control.checkCollision(head); // check if a fruit has been eaten

		if (state == GameState.Finished) // check if a collision occurred
			life--;

		lifeBtn.setText("Life: " + Integer.toString(life));
		scoreBtn.setText("Score: " + Integer.toString(score));

		if (life == 0)
			homeClicked();

	}

}
