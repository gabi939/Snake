package Model;

import Utils.Consts;

/**
 * Class Banana ~ represents a Banana in the game
 * 
 * @author Gabi Malin
 * @author David Duchovni
 * @author Nareed Hashem
 * 
 */
public class Banana extends GameObject {

	private int respawnTime = Consts.BANANA_RESPAWN;
	private int score = Consts.BANANA_SCORE;

	public Banana(int x, int y) {
		super(x, y);

	}

	public int getRespawnTime() {
		return respawnTime;
	}

	public int getScore() {
		return score;
	}

}
