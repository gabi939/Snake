package Model;

import java.util.ArrayList;

import Utils.Consts;

public class Snake {

	/**
	 * Snake's starting size
	 */
	private static final int SIZE = 6;
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

			for (int i = 1; i < SIZE; i++) {
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
	 * @return returns life counter
	 */
	public int getLife() {
		return life;
	}

}