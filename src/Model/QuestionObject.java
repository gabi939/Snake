package Model;

import javafx.scene.paint.Color;

public class QuestionObject extends GameObject {
	/**
	 * Color of normal fruit
	 */
	public  Question question;

	public static final Color FRUIT_COLOR = Color.YELLOW;
	
	public QuestionObject(int x, int y) {
		super(x, y);
		// NEED RANDOM QUESTION GENERATOR 
	}
}
