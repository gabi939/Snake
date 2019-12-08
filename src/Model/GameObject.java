package Model;

public abstract class GameObject {

	
	protected int X, Y;
	
	public GameObject(int x, int y){
		
		this.X = x;
		this.Y = y;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + X;
		result = prime * result + Y;
		return result;
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
