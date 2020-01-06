package Model;
/**
 * Class GameObject ~ represents a Game Object in the game
 * @author Gabi Malin
 * @author David Duchovni
 * @author Nareed Hashem
 * 
 */
public abstract class GameObject {

	private int X, Y;

	public GameObject(int x, int y) {
		super();
		this.X = x;
		this.Y = y;
	}

	public int getX() {
		return X;
	}

	public int getY() {
		return Y;
	}

	public void setX(int x) {
		X = x;
	}

	public void setY(int y) {
		Y = y;
	}

}
