package view;

import Controller.Sysdata;
import Utils.Consts;
import javafx.scene.paint.Color;

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

	public void changeSnakeColor(Color color) {
		this.snakeBodyColor = color;
		return;
	}

	public void changeThemeColor(Color color) {
		this.themeColor = color;
		return;
	}

	public void changeSnakeSpeed(Double speed) {
		this.snakeSpeed = speed;
		return;
	}

	public void changeMouseSpeed(Double speed) {
		this.mouseSpeed = speed;
		return;
	}

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

	/*
	 * This method gets current background music state and reverses it if on stops
	 * it, if on starts it
	 * 
	 */
	public void toggleBackgroundMusic(boolean bool) {
		//Sound.setBackgroundMuted(Sound.isBackgroundMuted());
	}

	/*
	 * This method gets current eating sound state and reverses it.
	 */
	public void toggleSoundFX(boolean bool) {
		//Sound.setEatingSound(bool);
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
