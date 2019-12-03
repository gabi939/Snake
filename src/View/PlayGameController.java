package View;

import java.io.IOException;
import java.net.URL;
import java.util.ListIterator;
import java.util.ResourceBundle;

import Utils.Direction;
import control.Game;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.BananaTest;
import model.Fruit;
import model.SnakeConfiguration;
/**
 * 
 * @author Shany Klein
 * @author Gabi Malin
 * @author David Duchovni
 * @author Nareed Hashem
 * 
 */
public class PlayGameController implements Initializable{

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
	private Pane PaneCanvas;

	@FXML
	private Canvas canvas;

	private ObservableList<Node> snake;

	private Timeline timeline = new Timeline();

	public static int score = 0; 

	private double speed = 0.1;

	private Direction direction = Direction.RIGHT;

	private boolean running = false;

	private boolean moved = false;


	protected String playerName = "";

	// =============================== Methods ==============================


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setCanvas();
		timeline = new Timeline();
		score = 0;
		defineGameLogic();
		startGame();

		nameBtn.setDisable(true);
		scoreBtn.setDisable(true);
		lifeBtn.setDisable(true);

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
	 * Draws the boxes on the canvas
	 */
	private void setCanvas() {
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.setStroke(Color.DARKGREY);

		for (int x = 0; x <= SnakeConfiguration.getWidth(); x += SnakeConfiguration.getBlockSize()) 
			for (int y = 0; y <= SnakeConfiguration.getHeight(); y += SnakeConfiguration.getBlockSize())
				gc.strokeRect(x, y, 20, 20);

	}

	/**
	 * Defines games logic
	 */
	private void defineGameLogic() {

		Group snakeBody = new Group();
		snake = snakeBody.getChildren();

		Rectangle food = Fruit.newFruit();
		Rectangle bananaTest = BananaTest.newFruit();


		KeyFrame frame = new KeyFrame(Duration.seconds(speed), event -> {
			if (!running)
				return;

			// code explain
			// this chunck of code moves the tail of the snake to the head
			boolean toRemove = snake.size() > 1;

			Node tail = toRemove ? snake.remove(snake.size() - 1) : snake.get(0);  //he moves tail to head

			double tailX = tail.getTranslateX();
			double tailY = tail.getTranslateY();

			switch (direction) {
			case UP:
				tail.setTranslateX(snake.get(0).getTranslateX());
				tail.setTranslateY(snake.get(0).getTranslateY() - SnakeConfiguration.getBlockSize());
				break;
			case DOWN:
				tail.setTranslateX(snake.get(0).getTranslateX());
				tail.setTranslateY(snake.get(0).getTranslateY() + SnakeConfiguration.getBlockSize());
				break;
			case RIGHT:
				tail.setTranslateX(snake.get(0).getTranslateX() + SnakeConfiguration.getBlockSize());
				tail.setTranslateY(snake.get(0).getTranslateY());
				break;
			case LEFT:
				tail.setTranslateX(snake.get(0).getTranslateX() - SnakeConfiguration.getBlockSize());
				tail.setTranslateY(snake.get(0).getTranslateY());
				break;
			}
			moved = true;
			if (toRemove)
				snake.add(0, tail);


			// checks if the snake hit himself
			for (Node rect : snake) {
				if (rect != tail && tail.getTranslateX() == rect.getTranslateX()
						&& tail.getTranslateY() == rect.getTranslateY()) {
					stopGame();
					startGame();
					break;

				}

			}

			//checks if the snake left the canvas
			Game.tailConditions(tail);


			// this checks if the food is under the snake
			if (Game.checkConditions(tail, food)) {// if tail eat food
				ListIterator<Node> it = snake.listIterator();

				while (it.hasNext()) {// checks that new food position doesn't hit the snake

					Node x = it.next();
					boolean match = x.getTranslateX() == food.getTranslateX()
							&& x.getTranslateY() == food.getTranslateY();
					if (match) {
						food.setTranslateX(Game.randXY(SnakeConfiguration.getWidth()));
						food.setTranslateY(Game.randXY(SnakeConfiguration.getHeight()));
						while (it.hasPrevious()) 
							it.previous();
					}
				}
				Rectangle rect = Game.grow(tailX, tailY);
				rect.getStyleClass().add("snake"); // setting the score if the
				// food was eaten
				score += 10;

				snake.add(rect);
			}


			if (Game.checkConditions(tail, bananaTest)) {
				// this checks if the food is under the snake
				ListIterator<Node> it2 = snake.listIterator();

				while (it2.hasNext()) {

					Node x = it2.next();
					boolean match = x.getTranslateX() == bananaTest.getTranslateX()
							&& x.getTranslateY() == bananaTest.getTranslateY();
					if (match) {
						bananaTest.setTranslateX(Game.randXY(SnakeConfiguration.getWidth()));
						bananaTest.setTranslateY(Game.randXY(SnakeConfiguration.getHeight()));
						while (it2.hasPrevious()) {
							it2.previous();
						}

					}
				}
				{


					Rectangle rect = Game.grow(tailX, tailY);
					rect.getStyleClass().add("snake"); // setting the score if the
					// food was eaten
					score += 30;

					snake.add(rect);	
				}
			}

			scoreBtn.setText("Score: " + score);

		});

		timeline.getKeyFrames().addAll(frame);
		timeline.setCycleCount(Timeline.INDEFINITE);

		PaneCanvas.getChildren().addAll(food,bananaTest, snakeBody);


	}


	/**
	 * Close window
	 */
	private void closeWindow(){	
		((Stage) pane.getScene().getWindow()).close();
	}



	/**
	 * stop game
	 */
	private void stopGame() { 
		running = false;
		timeline.stop();
		snake.clear();

	}

	/**
	 * starts game
	 */
	private void startGame() { // starting the game

		direction = Direction.RIGHT;

		Rectangle head = new Rectangle(SnakeConfiguration.getBlockSize(), SnakeConfiguration.getBlockSize());

		head.setTranslateY(Game.randXY(SnakeConfiguration.getHeight())); 
		head.setFill(Color.FORESTGREEN); // head color
		head.getStyleClass().add("snake");

		snake.add(head);

		timeline.play();
		running = true;
	}

	/**
	 * keys config	
	 * @param event
	 */
	@FXML
	void keypressed(KeyEvent event) {
		if (!moved)
			return;

		switch (event.getCode()) {
		case UP:
			if (direction != Direction.DOWN)
				direction = Direction.UP;

			break;
		case DOWN:
			if (direction != Direction.UP)
				direction = Direction.DOWN;
			break;
		case RIGHT:
			if (direction != Direction.LEFT)
				direction = Direction.RIGHT;
			break;
		case LEFT:
			if (direction != Direction.RIGHT)
				direction = Direction.LEFT;
			break;
		case ESCAPE:
			stopGame();
			homeClicked();
			break;
		}

		moved = false;
	}





}
