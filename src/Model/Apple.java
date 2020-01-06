package Model;
/**
 * Class Apple ~ represents an apple in the game
 * @author Gabi Malin
 * @author David Duchovni
 * @author Nareed Hashem
 * 
 */
public class Apple extends GameObject {
	public static final int RESPAWN = 5000;
	public static final int SNAKE_SIZE = 1;
	public static final int SCORE = 10;

	public Apple(int x, int y) {
		super(x, y);
	}
}
