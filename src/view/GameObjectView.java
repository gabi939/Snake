package view;

import Model.GameObject;
import javafx.scene.paint.Color;

/**
 * object for view
 * 
 * @author gabi9
 *
 */
public class GameObjectView {

	private GameObject object;
	private Color body_color;

	public GameObjectView(GameObject object, Color body_color) {
		super();
		this.object = object;
		this.body_color = body_color;
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

	public void setBody_color(Color body_color) {
		this.body_color = body_color;
	}

}
