package Model;

import java.util.ArrayList;
import java.util.Random;

import Controller.Controller;
import Utils.Consts;
import Utils.E_GameObject;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import view.ScoreView;

public class Board {

	/**
	 * List of fruits
	 */
	private ArrayList<GameObject> fruits;
	/**
	 * Super fruit object
	 */
	private Pear sFruit;
	/**
	 * List of obstacles
	 */
	private ArrayList<Obstacle> obstacles; // List of obstacles
	/**
	 * Score value
	 */
	private int score, highscore, fruitsEaten;
	/**
	 * Snake object
	 */
	private Snake snake;
	/**
	 * Snake's head
	 */
	private BodyPart head;
	/**
	 * Random number for generating points to place objects on them
	 */
	Random rand;
	/**
	 * State of the game
	 */
	private GameState state;
	/**
	 * Object of ScoreView to exchange informations about actual score
	 */
	private ScoreView scoreView;

	/**
	 * Timers for super fruit and it's effect
	 */
	private Timeline timeApple, timeBanana, timeMouse;

	/**
	 * pear position on the board
	 */
	private pearPosition pear_position;

	/**
	 * Default constructor of board class to initialize starting variables
	 */
	public Board() {

		scoreView = new ScoreView();
		fruits = new ArrayList<>();
		obstacles = new ArrayList<>();
		score = fruitsEaten = 0;
		snake = new Snake();
		rand = new Random();
		head = snake.getHead();
		state = GameState.Started;
		pear_position = pearPosition.topLeft;
		createWalls();
		setTimers();
		addObjectsToBoard();

	}

	private void setTimers() {
		timeApple = new Timeline(new KeyFrame(Duration.millis(Apple.RESPAWN), lambda -> addApple()));
		timeBanana = new Timeline(new KeyFrame(Duration.millis(Banana.RESPAWN), lambda -> addBanana()));
		timeMouse = new Timeline(new KeyFrame(Duration.millis(Mouse.RESPAWN), lambda -> addMouse()));

	}

	/**
	 * adds all needed objects to the board
	 */
	private void addObjectsToBoard() {
		addApple();
		addBanana();
		addQuestion();
		addQuestion();
		addQuestion();
		addPear();
	}

	/**
	 * checks if the snake hit himself
	 * 
	 * @return
	 */
	private boolean checkSelfHit() {
		int headX, headY, helpX, helpY;

		headX = head.getX();
		headY = head.getY();

		for (int i = 1; i < snake.getSize(); i++) {

			helpX = snake.getBodyPart(i).getX();
			helpY = snake.getBodyPart(i).getY();

			if (helpX == headX && helpY == headY) {
				highscore = score;
				reset();
				return true;
			}
		}

		return false;
	}

	/**
	 * checks if snake hit obstacles
	 * 
	 * @return true if does, else false
	 */
	private boolean checkObstacleHit() {
		int headX, headY, helpX, helpY;

		headX = head.getX();
		headY = head.getY();

		for (int i = 0; i < obstacles.size(); i++) {

			helpX = obstacles.get(i).getX();
			helpY = obstacles.get(i).getY();

			if (helpX == headX && helpY == headY) {
				highscore = score;
				reset();
				return true;
			}
		}
		return false;
	}

	/**
	 * Method to check if an collision occurred, either of the snake head with it's
	 * body or with an obstacle on the board
	 * 
	 * @return Returns the finished state of game
	 */
	public GameState checkCollision() {
		if (checkSelfHit() || checkObstacleHit())
			return GameState.Finished;

		return Controller.getState();
	}

	/**
	 * creates walls for the game
	 */
	private void createWalls() {
		for (int i = 0; i < Consts.HEIGHT + 1; i++)
			addObstacle(0, i);

		for (int i = 0; i < Consts.WIDTH + 1; i++)
			addObstacle(i, 0);

		for (int i = 0; i < Consts.HEIGHT + 1; i++)
			addObstacle(Consts.WIDTH, i);

		for (int i = 0; i < Consts.WIDTH + 1; i++)
			addObstacle(i, Consts.HEIGHT);
	}

	/**
	 * 
	 * /** Add new obstacle to array
	 * 
	 * @param X coordinate
	 * @param Y coordinate
	 */
	private void addObstacle(int X, int Y) {
		obstacles.add(new Obstacle(X, Y));
	}

	/**
	 * Method to check if snake ate a fruit
	 */
	public void checkEaten() {

		int headX, headY, foodX, foodY;
		headX = head.getX();
		headY = head.getY();

		for (int i = 0; i < fruits.size(); i++) {

			foodX = fruits.get(i).getX();
			foodY = fruits.get(i).getY();

			if (foodX == headX && foodY == headY) {

				GameObject gameObject = removeFruit(i);

				if (gameObject instanceof Apple) {
					timeApple.play();

				} else if (gameObject instanceof Pear) {
					addPear();
				} else if (gameObject instanceof QuestionObject) {
					addQuestion();
				} else if (gameObject instanceof Banana) {
					timeBanana.play();
				} else if (gameObject instanceof Mouse) {
					timeMouse.play();
				}
				addLength();
				fruitsEaten++;
			}
		}
	}

	/**
	 * add mouse to the board
	 * 
	 * @return
	 */
	private Object addMouse() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * checks if the x,y are taken by snake
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	private boolean underSnake(int x, int y) {
		for (int i = 0; i < snake.getSize(); ++i)
			if (snake.getBodyPart(i).getX() == x && snake.getBodyPart(i).getY() == y)
				return true;

		return false;
	}

	/**
	 * checks if the x,y are taken by objects
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	private boolean underObjects(int x, int y) {
		for (int i = 0; i < obstacles.size(); i++)
			if (x == obstacles.get(i).getX() && y == obstacles.get(i).getY())
				return true;

		return false;
	}

	/**
	 * checks if the x,y are taken by fruits
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	private boolean underFruits(int x, int y) {
		for (int i = 0; i < fruits.size(); ++i)
			if (fruits.get(i).getX() == x && fruits.get(i).getY() == y)
				return true;

		return false;
	}

	/**
	 * Method to place a fruit on the board
	 * 
	 * @return Returns point(X,Y) on board
	 */
	private int[] placeFruit() {

		int[] point = new int[2];
		boolean collision = true;

		while (collision) {

			point[0] = (rand.nextInt(Consts.WIDTH / Consts.SIZE) * Consts.SIZE);
			point[1] = (rand.nextInt(Consts.HEIGHT / Consts.SIZE) * Consts.SIZE);

			if (!underSnake(point[0], point[1]) && !underObjects(point[0], point[1])
					&& !underFruits(point[0], point[1]))
				collision = false;
		}

		return point;
	}

	/**
	 * adds apple to the board
	 */
	public void addApple() {
		timeApple.stop();
		int[] position = placeFruit();
		Apple apple = (Apple) ObjectFactory.getGameObject(E_GameObject.Apple, position[0], position[1]);
		fruits.add(apple);
		;

	}

	/**
	 * adds banana to the board
	 */
	public void addBanana() {
		timeBanana.stop();
		int[] position = placeFruit();
		Banana banana = (Banana) ObjectFactory.getGameObject(E_GameObject.Banana, position[0], position[1]);
		fruits.add(banana);

	}

	/**
	 * adds pear to the board
	 */
	public void addPear() {

		int[] position = new int[2];
		switch (pear_position) {
		case bottomLeft:

			position[0] = Consts.SIZE;
			position[1] = Consts.HEIGHT - Consts.SIZE;
			pear_position = pearPosition.topLeft;

			break;
		case bottomRight:

			position[0] = Consts.WIDTH - Consts.SIZE;
			position[1] = Consts.HEIGHT - Consts.SIZE;
			pear_position = pearPosition.bottomLeft;

			break;
		case topLeft:

			position[0] = 1 * Consts.SIZE;
			position[1] = 1 * Consts.SIZE;
			pear_position = pearPosition.topRight;

			break;
		case topRight:

			position[0] = Consts.WIDTH - Consts.SIZE;
			position[1] = Consts.SIZE;
			pear_position = pearPosition.bottomRight;
			break;

		default:

			position[0] = 1 * Consts.SIZE;
			position[1] = 1 * Consts.SIZE;
			pear_position = pearPosition.topLeft;
			break;
		}
		Pear pear = (Pear) ObjectFactory.getGameObject(E_GameObject.Pear, position[0], position[1]);
		fruits.add(pear);

	}

	/**
	 * adds question to the board
	 */
	public void addQuestion() {
		int[] position = placeFruit();
		QuestionObject q1 = (QuestionObject) ObjectFactory.getGameObject(E_GameObject.Question, position[0],
				position[1]);
		fruits.add(q1);
	}

	/**
	 * Method to remove a normal fruit from the board, after being eaten by snake
	 * 
	 * @param i Position of the fruit in array list
	 */
	public GameObject removeFruit(int i) {
		return fruits.remove(i);
	}

	/**
	 * Method for calling another method from ScoreView to add a point to the score
	 */
	public void updateScore() {
		scoreView.addScore(score);
	}

	/**
	 * Add new part to snake's body after eating a fruit
	 */
	public void addLength() {
		BodyPart b1 = snake.getBodyPart(snake.getSize() - 1), b2 = snake.getBodyPart(snake.getSize() - 2);
		if (b1.getX() > b2.getX())
			snake.addBodyPart(b1.getX() + Consts.SIZE, b1.getY());
		else if (b1.getX() < b2.getX())
			snake.addBodyPart(b1.getX() - Consts.SIZE, b1.getY());
		else if (b1.getY() >= b2.getY())
			snake.addBodyPart(b1.getX(), b1.getY() + Consts.SIZE);
		else if (b1.getY() >= b2.getY())
			snake.addBodyPart(b1.getX(), b1.getY() - Consts.SIZE);
	}

	/**
	 * Resets basic values of the game after lose
	 */
	private void reset() {
		snake.setStart();
		fruits.clear();
		addObjectsToBoard();
		score = fruitsEaten = 0;

	}

	/**
	 * Returns fruits
	 * 
	 * @return Array with fruits
	 */
	public ArrayList<GameObject> getFruits() {
		return fruits;
	}

	/**
	 * Returns the super fruit
	 * 
	 * @return Super fruit object
	 */
	public Pear getSuperFruit() {
		return sFruit;
	}

	/**
	 * Returns obstacles
	 * 
	 * @return Array with obstacles
	 */
	public ArrayList<Obstacle> getObstacles() {
		return obstacles;
	}

	/**
	 * Returns the snake
	 * 
	 * @return Snake object
	 */
	public Snake getSnake() {
		return snake;
	}

	/**
	 * Returns the object representing ScoreView in Board class
	 * 
	 * @return The ScoreView object
	 */
	public ScoreView getScoreView() {
		return scoreView;
	}

	/**
	 * Returns the actual score
	 * 
	 * @return Value of score
	 */
	public int getScore() {
		return score;
	}

	/**
	 * Returns the highscore when game finished
	 * 
	 * @return Value of final score
	 */
	public int getHighScore() {
		return highscore;
	}

	/**
	 * Returns the actual state of the game
	 * 
	 * @return Value of GameState
	 */
	public GameState getState() {
		return state;
	}

	private enum pearPosition {
		topLeft, topRight, bottomRight, bottomLeft
	}
}
