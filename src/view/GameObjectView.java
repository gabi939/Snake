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
import Utils.E_Difficulty;
import javafx.scene.image.Image;
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
	private Image img;

	public GameObjectView(GameObject object) {
		super();
		this.object = object;
		//chooseColor(object);
		chooseImage(object);
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

	/**
	 * Sets the color of the wrapper
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
			img = new Image(Consts.EASY_Q_IMG);
			/*
			QuestionObject q = (QuestionObject) object;
			
			if (E_Difficulty.EASY.equals(q.question.getDifficulty()))
				img =  new Image(Consts.EASY_Q_IMG);
			else if (E_Difficulty.MEDIUM.equals(q.question.getDifficulty())) {
				img =  new Image(Consts.MEDIUM_Q_IMG);
			}
			else if (E_Difficulty.HARD.equals(q.question.getDifficulty())) {
				img =  new Image(Consts.HARD_Q_IMG);
			}
			*/
		}
		//	else if (object instanceof Obstacle)
		//	body_color = Consts.OBSTACLE_COLOR;
		//img = new Image(Consts.WALL_IMG);
		else if (object instanceof Obstacle)
			body_color = Consts.OBSTACLE_COLOR;
			//img = new Image(Consts.WALL_IMG);
		else if (object instanceof Mouse)
			img = new Image(Consts.MOUSE_IMG);
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

	public Image getImg() {
		return img;
	}

	public void setImg(Image img) {
		this.img = img;
	}

}
