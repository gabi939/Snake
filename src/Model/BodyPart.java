package Model;

import javafx.scene.paint.Color;

public class BodyPart extends GameObject{

	/**
	 * Color of the snake's body
	 */
	public static Color BODY_COLOR = Color.DARKBLUE;
	/**
	 * Color of snake's head
	 */
	public static Color HEAD_COLOR = Color.ROYALBLUE;	
	
	public BodyPart(int x, int y) {
		super(x, y);
	}
}