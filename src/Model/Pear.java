package Model;

import javafx.scene.paint.Color;

public class Pear extends GameObject{

	/**
	 * Color of super fruit
	 */
	public static final Color SUPER_FRUIT_COLOR = Color.GREEN;
	/**
	 * How long super fruit remains on board (in miliseconds)
	 */
	public static final int ON_BOARD_TIME = 4000; 
	/**
	 * How long super state lasts (in miliseconds)
	 * 
	 */
	public static final int STATE_TIME = 9000;
	/**
	 * Respawn time (in miliseconds)
	 * 
	 */
	//Side Only no respawn, make sure not on the same corner 
	public static final int RESPAWN = 0;
	public static final int SCORE = 30; 
	public static final int SNAKE_SIZE = 1; 

	public Pear(int x, int y) {
		super(x, y);
	}
}