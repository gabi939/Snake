package view;

import Utils.Consts;
import javafx.scene.paint.Color;

public class GameSettings {
	private static GameSettings instance = null;
	private double snakeSpeed = Consts.DEFUALT_SNAKE_SPEED;
	private double mouseSpeed = Consts.DEFUALT_MOUSE_SPEED;
	private Color snakeBodyColor = Consts.DEFUALT_SNAKE_COLOR;
	private String themeColor = Consts.DEFUALT_BG_COLOR;
	private String snakeHead = Consts.DEFUALT_SNAKE_HEAD;

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
		return;
	}

	public void changeSnakeColor(Color color) {
		this.snakeBodyColor = color;
		return;
	}

	public void changeThemeColor(String color) {
		this.themeColor = color;
		return;
	}

	public void changeSnakeSpeed(Double speed) {
		this.snakeSpeed = speed;
		return;
	}

	public void changeSnakeMouse(Double speed) {
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


	public String getThemeColor() {
		return themeColor;
	}

	public String getSnakeHead() {
		return snakeHead;
	}



}
