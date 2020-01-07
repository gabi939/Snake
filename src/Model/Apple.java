package Model;

import Utils.Consts;

/**
 * Class Apple ~ represents an apple in the game
 * 
 * @author Gabi Malin
 * @author David Duchovni
 * @author Nareed Hashem
 * 
 */
public class Apple extends GameObject {

	private int respawnTime = Consts.APPLE_RESPAWN;
	private int score = Consts.APPLE_SCORE;

	public Apple(int x, int y) {
		super(x, y);
	}

	public int getRespawnTime() {
		return respawnTime;
	}

	public int getScore() {
		return score;
	}

}
