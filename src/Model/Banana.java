package Model;
/**
 * Class Banana ~ represents a Banana in the game
 * @author Gabi Malin
 * @author David Duchovni
 * @author Nareed Hashem
 * 
 */
public class Banana extends GameObject {

	public static final int RESPAWN = 10000;
	public static final int SNAKE_SIZE = 1;
	public static final int SCORE = 20;

	public Banana(int x, int y) {
		super(x, y);
	}
}
