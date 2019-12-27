package Controller;

import Model.Apple;
import Model.Banana;
import Model.Board;
import Model.BodyPart;
import Model.GameObject;
import Model.GameState;
import Model.Mouse;
import Model.Pear;
import Model.QuestionObject;
import Model.Snake;
import Utils.Consts;
import Utils.E_Difficulty;
import javafx.animation.Animation.Status;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import view.Sound;

/**
 * 
 * Connects the game screen and the classes in it. All manage methods are here.
 * 
 * @author gabi9
 *
 */
public class ManageGame {

	private Board board;
	private BodyPart head;
	private Snake snake;
	private Timeline timeApple, timeBanana, timeMouse;

	public ManageGame(Board board) {
		super();
		this.board = board;
		this.snake = board.getSnake();
		this.head = snake.getHead();

		timeApple = new Timeline(new KeyFrame(Duration.millis(Apple.RESPAWN), lambda -> addApple()));
		timeBanana = new Timeline(new KeyFrame(Duration.millis(Banana.RESPAWN), lambda -> addBanana()));
		timeMouse = new Timeline(new KeyFrame(Duration.millis(Mouse.RESPAWN), lambda -> addMouse()));

	}

	public void stopTimers() {
		timeApple.stop();
		timeBanana.stop();
		timeMouse.stop();
	}

	/**
	 * apple is added to the board and the apple timer stops
	 */
	public void addApple() {
		timeApple.stop();
		board.addApple();
	}

	/**
	 * banana is added to the board and the banana timer stops
	 */
	public void addBanana() {
		timeBanana.stop();
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
//	public void addQuestions() {
//		board.addQuestions();
//	}
//
	/**
	 * adds mouse to the board and stops mouse timer
	 */
	public void addMouse() {
		timeMouse.stop();
		board.addMouse();
	}

	/**
	 * Method to check if an collision occurred
	 * 
	 * @return Returns the finished state of game
	 */
	public GameState checkCollision(BodyPart head) {
		if (board.checkCollision(head))
			return GameState.Finished;
		return GameState.Running;
	}

	/**
	 * Method to check if snake ate a fruit
	 */
	public int eatUpdate(BodyPart head, int score) {

		GameObject fruit = board.checkEaten(head);

		// case fruit eaten
		// check what type it is
		// and make correct changes
		if (fruit instanceof Apple) {
			Sound.playEatingSound();
			score = score + Apple.SCORE;// adds score
			timeApple.play();// timer for respawn
			board.addLength();// snake growth

		} else if (fruit instanceof Pear) {
			Sound.playEatingSound();
			score = score + Pear.SCORE;
			board.addPear();
			board.addLength();

		} else if (fruit instanceof QuestionObject) {
			Sound.playEatingSound();
			if (((QuestionObject) fruit).getQuestion().getDifficulty().equals(E_Difficulty.EASY)) {
				board.addEasyQuestion();
			}
			if (((QuestionObject) fruit).getQuestion().getDifficulty().equals(E_Difficulty.MEDIUM)) {
				board.addMediumQuestion();
			}
			if (((QuestionObject) fruit).getQuestion().getDifficulty().equals(E_Difficulty.HARD)) {
				board.addHardQuestion();
			}

		} else if (fruit instanceof Banana) {
			Sound.playEatingSound();
			score = score + Banana.SCORE;
			timeBanana.play();
			board.addLength();

		} else if (fruit instanceof Mouse) {
			Sound.playEatingSound();
			score = score + Mouse.SCORE;
			timeMouse.play();
			board.addLength();
		}

		return score;// update score

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

	public Timeline getTimeApple() {
		return timeApple;
	}

	public void setTimeApple(Timeline timeApple) {
		this.timeApple = timeApple;
	}

	public Timeline getTimeBanana() {
		return timeBanana;
	}

	public void setTimeBanana(Timeline timeBanana) {
		this.timeBanana = timeBanana;
	}

	public Timeline getTimeMouse() {
		return timeMouse;
	}

	public void setTimeMouse(Timeline timeMouse) {
		this.timeMouse = timeMouse;
	}

	public void pauseTimers() {
		if (timeApple.getStatus() == Status.RUNNING)
			timeApple.pause();

		if (timeBanana.getStatus() == Status.RUNNING)
			timeBanana.pause();

		if (timeMouse.getStatus() == Status.RUNNING)
			timeMouse.pause();

	}

	public void playTimers() {
		if (timeApple.getStatus() == Status.PAUSED)
			timeApple.play();

		if (timeBanana.getStatus() == Status.PAUSED)
			timeBanana.play();

		if (timeMouse.getStatus() == Status.PAUSED)
			timeMouse.play();
	}

}
