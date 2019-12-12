package Model;

import javafx.scene.paint.Color;

public class Banana extends GameObject {

	/**
	 * Color of normal fruit
	 */
	public static final int RESPAWN = 10000;
	public static final int SNAKE_SIZE = 1;
	public static final int SCORE = 20;

	public Banana(int x, int y) {
		super(x, y);
		setBody_color(Color.YELLOW);
	}
}
