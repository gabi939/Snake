package Model;

import javafx.scene.paint.Color;

public class QuestionObject extends GameObject {
	/**
	 * Color of normal fruit
	 */
	public Question question;

	public QuestionObject(int x, int y) {
		super(x, y);
		setBody_color(Color.ORANGE);
		// TODO NEED RANDOM QUESTION GENERATOR
	}
}
