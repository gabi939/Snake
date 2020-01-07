package view;

import Model.Apple;
import Model.Banana;
import Model.BodyPart;
import Model.GameObject;
import Model.Mouse;
import Model.Pear;
import Model.QuestionObject;
import Utils.Consts;
import Utils.E_Difficulty;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
/**
 * Class Game Object View ~ wrapper class for objects in the game
 * that controls their appearance on the board
 * 
 * @author Shany Klein
 * @author Gabi Malin
 * @author David Duchovni
 */
public class GameObjectView {

	private GameObject object;
	private Color body_color;
	private Image img;

	public GameObjectView(GameObject object) {
		super();
		this.object = object;
		chooseImage(object);
	}

	/**
	 * Sets the icon of the game object
	 * 
	 * @param object
	 */
	private void chooseImage(GameObject object) {


		if (object instanceof Apple)
			img = new Image(Consts.APPLE_IMG);
		else if (object instanceof Pear)
			img = new Image(Consts.PEAR_IMG);
		else if (object instanceof Banana)
			img = new Image(Consts.BANANA_IMG);
		else if (object instanceof QuestionObject) {
			QuestionObject q = (QuestionObject) object;

			if (E_Difficulty.EASY.equals(q.getQuestion().getDifficulty()))
				img =  new Image(Consts.EASY_Q_IMG);

			else if (E_Difficulty.MEDIUM.equals(q.getQuestion().getDifficulty())) {
				img =  new Image(Consts.MEDIUM_Q_IMG);
			}
			else if (E_Difficulty.HARD.equals(q.getQuestion().getDifficulty())) {
				img =  new Image(Consts.HARD_Q_IMG);
			}
		}
		else if (object instanceof Mouse)
			img = new Image(Consts.MOUSE_IMG);
		else if (object instanceof BodyPart)
			body_color = GameSettings.getInstance().getSnakeBodyColor();
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

	public Image getImg() {
		return img;
	}

	public void setImg(Image img) {
		this.img = img;
	}

}