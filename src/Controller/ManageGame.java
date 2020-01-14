package Controller;

import Model.Apple;
import Model.Banana;
import Model.Board;
import Model.BodyPart;
import Model.GameObject;
import Model.Mouse;
import Model.Pear;
import Model.QuestionObject;
import Model.Snake;
import Utils.Consts;
import Utils.E_Difficulty;
import Utils.E_GameObject;
import Utils.E_TimerStatus;
import Utils.GameState;
import javafx.animation.AnimationTimer;
import javafx.scene.paint.Color;
import view.GameSettings;
import view.ViewLogic;

/**
 * 
 * Class ManageGame ~ Connects the game screen and the classes in it. All manage
 * methods are here.
 * 
 * @author Shany Klein
 * @author Gabi Malin
 * @author David Duchovni
 * @author Nareed Hashem
 *
 */
public class ManageGame {

	private static ManageGame single_control = null;

	private Board board;
	private BodyPart head;
	private Snake snake;
	private GameObjectTimer timeApple, timeBanana, timeMouse;
	private QuestionObject questionEaten;
	private int score;
	private AnimationTimer time;
	private GameSettings gameSettings;

	public static ManageGame getInstance() {
		if (single_control == null)
			single_control = new ManageGame();

		return single_control;
	}

	private ManageGame() {
		super();
		this.board = new Board();
		this.snake = board.getSnake();
		this.head = snake.getHead();
		this.score = 0;

		gameSettings = GameSettings.getInstance();
		timeApple = new GameObjectTimer(E_GameObject.Apple);
		timeBanana = new GameObjectTimer(E_GameObject.Banana);
		timeMouse = new GameObjectTimer(E_GameObject.Mouse);

	}

	/**
	 * initializes the board
	 */
	public void initGame() {
		board.reset();
		board.setMouseSpeed(gameSettings.getMouseSpeed());
		board.setSnakeSpeed(gameSettings.getSnakeSpeed());
	}

	/**
	 * stops all timers except main game timer
	 */
	public void stopTimers() {
		timeApple.cancel();
		timeBanana.cancel();
		timeMouse.cancel();
	}

	/**
	 * apple is added to the board and the apple timer stops
	 */
	public void addApple() {
		timeApple.cancel();
		board.addApple();
	}

	/**
	 * banana is added to the board and the banana timer stops
	 */
	public void addBanana() {
		timeBanana.cancel();
		board.addBanana();
	}

	/**
	 * adds pear to the board
	 */
	public void addPear() {
		board.addPear();
	}

	/**
	 * adds questions to the board
	 */
	// public void addQuestions() {
	// board.addQuestions();
	// }
	//
	/**
	 * adds mouse to the board and stops mouse timer
	 */
	public void addMouse() {
		timeMouse.cancel();
		board.addMouse();
	}

	/**
	 * Method to check if an collision occurred
	 * 
	 * @return Returns the finished state of game
	 */
	public GameState checkCollision(BodyPart head) {
		if (board.checkCollision(head)) {
			Sound.playHitingSound();
			snake.reduceLife();
			stopTimers();
			return GameState.Finished;
		}
		return GameState.Running;
	}

	/**
	 * Method to check if snake ate a fruit
	 */
	public void eatUpdate(BodyPart head) {

		GameObject object = board.checkEaten(head);

		if (object != null) {
			Sound.playEatingSound();

			// case fruit eaten
			// check what type it is
			// and make correct changes
			if (object instanceof Apple) {
				score += ((Apple) object).getScore();

				timeApple.start();// timer for respawn
				board.addLength();// snake growth

			} else if (object instanceof Pear) {
				score = score + ((Pear) object).getScore();
				board.addPear();
				board.addLength();

				// case Question is eaten
			} else if (object instanceof QuestionObject) {
				// add immediately a new question
				if (((QuestionObject) object).getQuestion().getDifficulty().equals(E_Difficulty.EASY)) {
					board.addEasyQuestion();
				}
				if (((QuestionObject) object).getQuestion().getDifficulty().equals(E_Difficulty.MEDIUM)) {
					board.addMediumQuestion();
				}
				if (((QuestionObject) object).getQuestion().getDifficulty().equals(E_Difficulty.HARD)) {
					board.addHardQuestion();
				}

				// pass question eaten to pop up window and show it
				questionEaten = (QuestionObject) object;
				pauseGame();
				ViewLogic.popUpQuestionWindow();

			} else if (object instanceof Banana) {
				score = score + ((Banana) object).getScore();
				timeBanana.start();
				board.addLength();

			} else if (object instanceof Mouse) {
				score = score + ((Mouse) object).getScore();
				snake.addLife();
				timeMouse.start();
				board.addLength();
				board.addLength();
			}
		}
	}

	public void setScore(int score) {
		this.score = score;
	}

	/**
	 * updates mouse position on the screen
	 */
	public void updateMousePosition() {
		board.updateMousePosition();
	}

	/**
	 * Method to handle snake's position and movement on board
	 * 
	 * @param dx - movement in X-axis, 1 for right, -1 for left
	 * @param dy - movement in Y-axis, 1 for down, -1 for up
	 */
	public void move(int dx, int dy) {
		if (dx != 0 || dy != 0) { // if snake is meant to move

			// temporary variables to hold BodyParts
			BodyPart prev = new BodyPart(head.getX(), head.getY()), next = new BodyPart(head.getX(), head.getY());

			// move head in X-axis
			head.setX(head.getX() + (dx * Consts.SIZE));

			// move head in Y-axis
			head.setY(head.getY() + (dy * Consts.SIZE));

			// moving the snake's body, each point gets the position of the one in front
			for (int i = 1; i < snake.getSize(); ++i) {

				next.setX(snake.getBodyPart(i).getX());
				next.setY(snake.getBodyPart(i).getY());

				snake.getBodyPart(i).setX(prev.getX());
				snake.getBodyPart(i).setY(prev.getY());
				prev.setX(next.getX());
				prev.setY(next.getY());
			}
		}
	}

	/**
	 * pauses running timers
	 */
	public void pauseTimers() {
		if (timeApple.getStatus() == E_TimerStatus.RUNNING)
			timeApple.pause();

		if (timeBanana.getStatus() == E_TimerStatus.RUNNING)
			timeBanana.pause();

		if (timeMouse.getStatus() == E_TimerStatus.RUNNING)
			timeMouse.pause();

	}

	/**
	 * plays paused timers
	 */
	public void playTimers() {
		if (timeApple.getStatus() == E_TimerStatus.PAUSED)
			timeApple.resume();

		if (timeBanana.getStatus() == E_TimerStatus.PAUSED)
			timeBanana.resume();

		if (timeMouse.getStatus() == E_TimerStatus.PAUSED)
			timeMouse.resume();
	}

	/**
	 * 
	 * @return returns the eaten question object
	 */
	public QuestionObject getQuestionEaten() {
		return questionEaten;
	}

	/**
	 * keeps the eaten question
	 * 
	 * @param questionEaten
	 */
	public void setQuestionEaten(QuestionObject questionEaten) {
		this.questionEaten = questionEaten;
	}

	/**
	 * pauses the game
	 */
	public void pauseGame() {
		pauseTimers();
		ViewLogic.playGameController.setPause(true);
		ViewLogic.playGameController.setResume(false);

	}

	/**
	 * continue game
	 */
	public void continueGame() {
		playTimers();
		ViewLogic.playGameController.setResume(true);
		ViewLogic.playGameController.setPause(false);
		if (GameSettings.getInstance() != null)
			ViewLogic.playGameController.resume(GameSettings.getInstance().getSnakeSpeed(),
					GameSettings.getInstance().getMouseSpeed());
		else
			ViewLogic.playGameController.resume(Consts.DEFUALT_SNAKE_SPEED, Consts.DEFUALT_MOUSE_SPEED);
	}

	/**
	 * @return returns the current score
	 */
	public int getScore() {
		return score;
	}

	/**
	 * 
	 * @return returns the playing board
	 */
	public Board getBoard() {
		return board;
	}

	/**
	 * sets and starts the animation of snake
	 * 
	 * @param time
	 */
	public void setTime(AnimationTimer time) {

		this.time = time;
		this.time.start();
	}

	/**
	 * Reset colors back after playing in a special mode!
	 */
	public void colorReset() {
		if (Sysdata.getPlayer() != null) {
			if (Sysdata.getPlayer().getName().toLowerCase().contains("sloth")
					|| Sysdata.getPlayer().getName().toLowerCase().contains("tsvika")) {
				GameSettings.getInstance().changeSnakeColor(Color.BLUE);
			}
		}
	}

	/**
	 * returns the main game timer
	 * 
	 * @return
	 */
	public AnimationTimer getTime() {
		return time;
	}

	/**
	 * This method handles the game when the player loses his life
	 */
	public void gameOver() {
		if (score > 0 && snake.getLife() == 0)
			saveHistory();
		Sound.stopMusic();
		resetFields();
		stopTimers();
		time.stop();

	}

	private void resetFields() {
		score = 0;
		snake.resetFields();
	}

	/**
	 * Save the player's history in the array
	 */
	private void saveHistory() {
		// setting the player's score
		Sysdata.getPlayer().setScore(getScore());

		// adding the player to the array
		Sysdata.getInstance().addGameHistory(Sysdata.getPlayer());
	}

	/**
	 * adds a specific amount of life to the snake
	 * 
	 * @param amount
	 */
	public void addLife(int amount) {
		snake.addLife(amount);

	}

	/**
	 * adds points to the score
	 * 
	 * @param points
	 */
	public void addScore(int points) {
		score += points;

	}

}
