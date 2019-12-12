package Model;

import javafx.scene.paint.Color;

public abstract class GameObject {

	protected int X, Y;
	protected Color body_color;

	public GameObject(int x, int y) {
		super();
		this.X = x;
		this.Y = y;
	}

	public Color getBody_color() {
		return body_color;
	}

	public void setBody_color(Color body_color) {
		this.body_color = body_color;
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
