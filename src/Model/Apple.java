package Model;

import javafx.scene.paint.Color;

public class Apple extends GameObject{

	/**
	 * Color of normal fruit
	 */
	public static final int RESPAWN = 5000;
	public static final Color FRUIT_COLOR = Color.RED;
	public static final int SNAKE_SIZE = 1; 
	public static final int SCORE = 10; 

	public Apple(int x, int y) {
		super(x, y);
	}
}
