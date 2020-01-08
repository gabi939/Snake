package Model;

import java.util.ArrayList;

import Utils.Consts;

/**
 * Class Snake ~ represents a snake in the game
 * 
 * @author Gabi Malin
 * @author David Duchovni
 * @author Nareed Hashem
 * 
 */
public class Snake {

	/**
	 * head of the snake
	 */
	private BodyPart head;
	/**
	 * Array that holds the entire body
	 */
	private ArrayList<BodyPart> body;
	/**
	 * Snake's size variable and starting position of head
	 */
	private int size, headX = Consts.WIDTH / 2 + Consts.SIZE / 2, headY = Consts.HEIGHT / 2 + Consts.SIZE / 2;

	/**
	 * Counts the life of the snake
	 */
	private int life;

	/**
	 * snakes speed
	 */
	private double speed;

	public Snake() {

		body = new ArrayList<>();
		head = new BodyPart(headX, headY);
		size = 0;
		life = 3;
		setStart();
	}

	/**
	 * Method to set snake at starting position, at start or restart of the game
	 */
	public void setStart() {

		// set starting position
		if (size == 0) {

			body.add(head);
			size++;

			for (int i = 1; i < Consts.SNAKE_SIZE; i++) {
				addBodyPart(headX, headY + (i * Consts.SIZE));
			}
		}
		// if game restart
		else {

			body.clear();
			head.setX(headX);
			head.setY(headY);
			size = 0;
			setStart();
		}
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	/**
	 * Return snake's actual size
	 * 
	 * @return size
	 */
	public int getSize() {
		return this.size;
	}

	/**
	 * Return particular body part
	 * 
	 * @param i - position of part in body array
	 * @return the body part
	 */
	public BodyPart getBodyPart(int i) {
		return body.get(i);
	}

	/**
	 * Returns the head of the snake as BodyPart object
	 * 
	 * @return snake's head
	 */
	public BodyPart getHead() {
		return this.head;
	}

	/**
	 * Adds new body part at given point
	 * 
	 * @param x - position
	 * @param y - position
	 */
	public void addBodyPart(int x, int y) {
		body.add(new BodyPart(x, y));
		size++;
	}

	/**
	 * removes one life from the snake
	 */
	public void reduceLife() {
		life--;
	}

	/**
	 * adds one life to the snake
	 */
	public void addLife() {
		life++;
	}

	/**
	 * adds amount to life
	 * 
	 * @param amount
	 */
	public void addLife(int amount) {
		life += amount;
	}

	/**
	 * @return returns life counter
	 */
	public int getLife() {
		return life;
	}

	/**
	 * reset the fields of the snake
	 */
	public void resetFields() {
		setStart();
		life = 3;

	}

}