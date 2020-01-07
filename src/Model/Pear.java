package Model;

import Utils.Consts;

/**
 * Class Pear ~ represents a Pear in the game
 * 
 * @author David Duchovni
 * @author Nareed Hashem
 * 
 */
public class Pear extends GameObject {

	private int score = Consts.PEAR_SCORE;

	public Pear(int x, int y) {
		super(x, y);
	}

	public int getScore() {
		return score;
	}

}