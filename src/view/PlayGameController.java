package view;

import java.net.URL;
import java.util.ResourceBundle;

import Model.Board;
import Model.BodyPart;
import Model.GameObject;
import Model.GameState;
import Model.Snake;
import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
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
	private Pane canvas;

	public static int score = 0; 
	protected static GameState state;

	private Snake snake;
	private BodyPart head;

	private Board board;
	private Color bodyColor;
	private boolean up, down, right, left, pause, resume, start ;
	/**
	 * The movement in X and Y-axis
	 */
	private int dx, dy;
	/**
	 * Variable to control snake's speed
	 */
	private int speedConstraint;
	/**
	 *  Boolean block to prevent pressing keys too fast, so that the snake's head could turn around.
		For example, when snake was moving left, pressing the up and right key very fast could just change the head's direction
		to right, without changing the position in Y-axis, causing the head to hit the second part of it's body  
	 */
	private boolean keyActive;
	private int speedPointsConstraint;
	
	public final static int HEIGHT = 580;
	public final static int WIDTH = 910;
	

	// =============================== Methods ==============================

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		nameBtn.setText("Name: " + ViewLogic.enterNameController.playerName);
		
		board = new Board();
		snake = board.getSnake();
		dx = dy = 0;
		up = down = right = left = pause = resume = start = false;
		keyActive = true;
		speedConstraint = 3;
		head = snake.getHead();
		state = GameState.Running;
		resume();
		
		 
	
		
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
	private void closeWindow(){	
		((Stage) pane.getScene().getWindow()).close();
	}
	
	private void whenRunning() {
		
		canvas.getChildren().clear(); // clear canvas  
		
		int snakeY, snakeX; // variables for loops
		
		// if snake is in super state set the right color
		if(board.getSuperState()) 
			bodyColor = BodyPart.SUPER_BODY_COLOR;
		else 
			bodyColor = BodyPart.BODY_COLOR;
		
		// snake's head to canvas
		Circle c = new Circle(snake.getHead().getX() , snake.getHead().getY(), GameObject.SIZE/2); 
		c.setFill(BodyPart.HEAD_COLOR);
		canvas.getChildren().add(c);
		
		
		for(int i = 1; i < snake.getSize(); ++i) {
			snakeX = snake.getBodyPart(i).getX();
			snakeY = snake.getBodyPart(i).getY();
			c = new Circle(snakeX , snakeY, GameObject.SIZE/2); 
			c.setFill(bodyColor);
			canvas.getChildren().add(c);
		}
	

	}
	/**
	 * Method to handle pressed keys on scene given as argument
	 * @param scene on which events are performed
	 */
	private void movement(Pane canvas) {
		
		canvas.getScene().setOnKeyPressed(new EventHandler<KeyEvent>() {
			
			public void	handle(KeyEvent e){
				switch(e.getCode()) {
					case UP:
						if(!down && keyActive && state == GameState.Running) {
							up = true;
							left = false;
							right = false;
							keyActive = false;

						}
						break;
					case DOWN:
						if(!up && keyActive && (left || right) && state == GameState.Running) {
							down = true;
							left = false;
							right = false;
							keyActive = false;
						}
						break;
					case LEFT:
						if(!right && keyActive && state == GameState.Running) {
							left = true;
							up = false;
							down = false;
							keyActive = false;
						}
						break;
					case RIGHT:
						if(!left && keyActive && state == GameState.Running) {	
							right = true;
							up = false;
							down = false;
							keyActive = false;
						}
						break;
					case SPACE: // pause or resume game
						if(state == GameState.Running || state == GameState.Paused) {
							if(pause == false) {
								pause = true;
								resume = false;
							}
							else {
								resume = true;
								pause = false;
								resume();
							}
						}
						break;
					case ENTER:{ // start or restart the game
							if(state == GameState.Started)
								start = true;
							if(state == GameState.Finished) {
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
	private void resume(){
		
		 new AnimationTimer(){
			 
				int i=0;
				@Override
				public void handle(long now) {
					
					
					// when moving up
					if(up && !down) {
						
						dy = -1;
						dx = 0;
					}
					// when moving down
					if(!up && down) {
						
						dy = 1 ;
						dx = 0;
					}
					// when moving left
					if(left && !right) {
						
						dy = 0;
						dx = -1;
					}
					//when moving right
					if(right && !left) {
						dy = 0;
						dx = 1;
					}
					// when game paused
					if(pause && !resume) {
						state = GameState.Paused;
						//view.render();
						stop();
					}
					// when game resumed
					if(resume && !pause) {
						state = GameState.Running;
						resume = false;
					}
					// when game started or restarted
					if(start && (state == GameState.Finished || state == GameState.Started)) {
						restart();
						start = false;
					}
					// when game finished
					if(state == GameState.Finished) {
						stop();
					}	
					// when game is running, make movement
					if(state == GameState.Running) {
						if(i==speedConstraint) { // control the speed of snake
							System.out.println(speedConstraint);
							move(dx, dy);
							keyActive = true; // unlock possibility to press another key after snake made it's move
							i=0; // counter to slow down the snake
						}

						++i;
					}
				
					update(); // updating the game parameters, positions, etc.
					render();
					movement(canvas); // handling user key input on actual scene
				}
			}.start(); // starting the timer
		
	}
	
	
	/**
	 * The render method, that displays the graphics
	 */
	public void render() {
			
		System.out.println(state);
		switch(state) { // checks for actual game state
			case Started:	// if game state is Started display the starting screen
				//whenStarted();
				break;
			case Running:	
				whenRunning(); // if Running show the board, snake, objects, etc.
				break;
			case Paused: // if Paused show the pause screen
				//whenPaused();
				break;
			case Finished: // if Finished show the ending game screen and display the score
				//whenFinished();
				break;
			default:
				break;
		}
	} 
	
	/**
	 * Restarting the game by setting basic parameters to their primary values
	 */
	private void restart() {
		state = GameState.Running;
		dx = dy = 0;
		up = down = left = right = false;
		speedConstraint = 3;
		speedPointsConstraint = 50;
	}
	
	

	/**
	 * Method to handle snake's position and movement on board
	 * @param dx - movement in X-axis, 1 for right, -1 for left
	 * @param dy - movement in Y-axis, 1 for down, -1 for up
	 */
	private void move(int dx, int dy) {
		if(dx != 0 || dy != 0) { // if snake is meant to move

			// temporary variables to hold BodyParts
			BodyPart prev = new BodyPart(head.getX(), head.getY()), next = new BodyPart(head.getX(), head.getY());
			
			// move head in X-axis
			head.setX(head.getX()+(dx*GameObject.SIZE));

			// check if head didn't go beyond screen(>WIDTH or <0), if yes set it on the other side
			if(head.getX() > WIDTH) {
				head.setX(GameObject.SIZE/2);
			}
			else if(head.getX() < 0) {
				head.setX(WIDTH - GameObject.SIZE/2);
			}
			
			// move head in Y-axis
			head.setY(head.getY()+(dy*GameObject.SIZE));

			// check if head didn't go beyond screen(>HEIGHT or <0), if yes set it on the other side
			if(head.getY() > HEIGHT) {
				// for 2 points next to ScoreView panel
				if((head.getX() == GameObject.SIZE/2 || head.getX() == HEIGHT - GameObject.SIZE/2) && head.getY() == HEIGHT + GameObject.SIZE/2);
				else
					head.setY(GameObject.SIZE/2);
			}
			else if(head.getY() < 0) {
				head.setY(HEIGHT - GameObject.SIZE/2);
			}
		
			// moving the snake's body, each point gets the position of the one in front
			for(int i = 1; i < snake.getSize(); ++i) {

				next.setX( snake.getBodyPart(i).getX());
				next.setY( snake.getBodyPart(i).getY());
		
				snake.getBodyPart(i).setX(prev.getX());
				snake.getBodyPart(i).setY(prev.getY());
				prev.setX(next.getX());
				prev.setY(next.getY());
			}
		}		
	}
	
	

	/**
	 * The update method
	 */
	private void update() {
		
		board.updateFruit(); // updates the state of fruits
		//board.updateObstacles(); // updating the obstacles on board
		board.checkEaten(); // check if a fruit has been eaten
		board.updateScore(); // updating score(passing it to scoreView class to show it on screen)
		if(board.checkCollision() == GameState.Finished) { // check if a collision occurred
			state = GameState.Finished; // 
		}
		// TODO 
		//setSound(); // updating the sound
		
		// setting snake speed due to gathered points
		if(speedConstraint > 2 && board.getScore() >= speedPointsConstraint)
			speedConstraint = 2; 		   //snake will move faster
		if((speedConstraint == 2) && (board.getScore() - speedPointsConstraint) >= 10) {
			speedPointsConstraint += 30;  // next interval 30 points further
			speedConstraint = 3; 	   	  // back to original speed
		}
	}
	
	
	

}
