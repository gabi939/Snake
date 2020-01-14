package view;

import Controller.Sound;
import Utils.Consts;
import javafx.scene.paint.Color;

/**
 * Class Game Settings ~ class that allows implementing chosen settings in the
 * game
 * 
 * @author Shany Klein
 * @author Nareed Hashem
 *
 */
public class GameSettings {
	private static GameSettings instance = null;
	private double snakeSpeed = Consts.DEFUALT_SNAKE_SPEED;
	private double mouseSpeed = Consts.DEFUALT_MOUSE_SPEED;
	private Color snakeBodyColor = Consts.DEFUALT_SNAKE_COLOR;
	private Color themeColor = Consts.DEFUALT_BG_COLOR;
	private String snakeHead = Consts.DEFUALT_SNAKE_HEAD;
	private boolean music = true;
	private boolean soundfx = true;

	public static GameSettings getInstance() {
		if (instance == null)
			instance = new GameSettings();
		return instance;
	}

	/**
	 * resets game settigns to default
	 */
	public void resetToDefault() {
		snakeSpeed = Consts.DEFUALT_SNAKE_SPEED;
		mouseSpeed = Consts.DEFUALT_MOUSE_SPEED;
		snakeBodyColor = Consts.DEFUALT_SNAKE_COLOR;
		themeColor = Consts.DEFUALT_BG_COLOR;
		snakeHead = Consts.DEFUALT_SNAKE_HEAD;
		music = true;
		soundfx = true;
		return;
	}

	/**
	 * changes the snake's body color
	 * 
	 * @param color
	 */
	public void changeSnakeColor(Color color) {
		this.snakeBodyColor = color;
		return;
	}

	/**
	 * changes the background color
	 * 
	 * @param color
	 */
	public void changeThemeColor(Color color) {
		this.themeColor = color;
		return;
	}

	/**
	 * changes the snake's speed
	 * 
	 * @param speed
	 */
	public void changeSnakeSpeed(Double speed) {
		this.snakeSpeed = speed;
		return;
	}

	/**
	 * changes the mouse's speed
	 * 
	 * @param speed
	 */
	public void changeMouseSpeed(Double speed) {
		this.mouseSpeed = speed;
		return;
	}

	/**
	 * changes the snake's head
	 * 
	 * @param pathToPic
	 */
	public void changeSnakeHead(String pathToPic) {
		this.snakeHead = pathToPic;
		return;
	}

	public double getSnakeSpeed() {
		return snakeSpeed;
	}

	public double getMouseSpeed() {
		return mouseSpeed;
	}

	public Color getSnakeBodyColor() {
		return snakeBodyColor;
	}

	/**
	 * 
	 * @return casted string of the snake body color
	 */
	public String getConvertedSnakeBodyColor() {
		Color c = snakeBodyColor;
		return String.format("rgba(%d, %d, %d, %f)", (int) (255 * c.getRed()), (int) (255 * c.getGreen()),
				(int) (255 * c.getBlue()), c.getOpacity());
	}

	/**
	 * 
	 * @return casted string of the snake body color
	 */
	public String getConvertedThemeColor() {
		Color c = themeColor;
		return String.format("rgba(%d, %d, %d, %f)", (int) (255 * c.getRed()), (int) (255 * c.getGreen()),
				(int) (255 * c.getBlue()), c.getOpacity());
	}

	public Color getThemeColor() {
		return themeColor;
	}

	public String getSnakeHead() {
		return snakeHead;
	}

	public boolean isMusic() {
		return music;
	}

	public void setMusic(boolean music) {
		this.music = music;
	}

	public boolean isSoundfx() {
		return soundfx;
	}

	public void setSoundfx(boolean soundfx) {
		this.soundfx = soundfx;
		Sound.setSoundFX(soundfx);
	}
}
