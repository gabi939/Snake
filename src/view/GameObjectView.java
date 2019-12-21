package view;

import Model.Apple;
import Model.Banana;
import Model.BodyPart;
import Model.GameObject;
import Model.Mouse;
import Model.Obstacle;
import Model.Pear;
import Model.QuestionObject;
import Utils.Consts;
import javafx.scene.paint.Color;

/**
 * wrapper class for objects in the game
 * 
 * @author gabi9
 *
 */
public class GameObjectView {

	private GameObject object;
	private Color body_color;

	public GameObjectView(GameObject object) {
		super();
		this.object = object;
		chooseColor(object);
	}

	/**
	 * Sets the color of the wrapper
	 * 
	 * @param object
	 */
	private void chooseColor(GameObject object) {

		if (object instanceof Apple)
			body_color = Color.RED;
		else if (object instanceof Pear)
			body_color = Color.GREEN;
		else if (object instanceof Banana)
			body_color = Color.YELLOW;
		else if (object instanceof QuestionObject)
			body_color = Color.ORANGE;
		else if (object instanceof Obstacle)
			body_color = Consts.OBSTACLE_COLOR;
		else if (object instanceof Mouse)
			body_color = Color.GREY;
		else if (object instanceof BodyPart)
			body_color = Consts.DEFUALT_SNAKE_COLOR;

	}

	public GameObject getObject() {
		return object;
	}

	public void setObject(GameObject object) {
		this.object = object;
	}

	public Color getBody_color() {
		return body_color;
	}

}
