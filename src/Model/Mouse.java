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

	private double speed;
	private int respawnTime = Consts.MOUSE_RESPAWN;
	private int score = Consts.MOUSE_SCORE;

	public Mouse(int x, int y) {
		super(x, y);
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public int getRespawnTime() {
		return respawnTime;
	}

	public int getScore() {
		return score;
	}

}