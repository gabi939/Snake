package Model;

public class Mouse extends GameObject {

	/**
	 * How long super fruit remains on board (in miliseconds)
	 */
	public static final int ON_BOARD_TIME = 4000;
	/**
	 * How long super state lasts (in miliseconds)
	 */
	public static final int SUPER_STATE_TIME = 9000;
	public static final int RESPAWN = 60000;
	public static final int SNAKE_SIZE = 2;
	public static final int SCORE = 30;
	public static final int ADD_LIFE = 1;

	// RANDOM MOVEMENT HERE
	public Mouse(int x, int y) {
		super(x, y);
	}
}