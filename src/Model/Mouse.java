package Model;

import Utils.Consts;

/**
 * Class Mouse ~ represents an Mouse in the game
 * 
 * @author David Duchovni
 * @author Nareed Hashem
 * 
 */
public class Mouse extends GameObject {

	private double defualtSpeed = Consts.DEFUALT_MOUSE_SPEED;
	private int respawnTime = Consts.MOUSE_RESPAWN;
	private int score = Consts.MOUSE_SCORE;

	public Mouse(int x, int y) {
		super(x, y);
	}

	public double getDefualtSpeed() {
		return defualtSpeed;
	}

	public int getRespawnTime() {
		return respawnTime;
	}

	public int getScore() {
		return score;
	}

}