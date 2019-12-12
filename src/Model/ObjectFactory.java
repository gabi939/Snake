package Model;

import Utils.E_GameObject;

public class ObjectFactory {
	public static GameObject getGameObject(E_GameObject type, int x, int y) {
		if (type.equals(E_GameObject.Apple))
			return new Apple(x, y);
		else if (type.equals(E_GameObject.Banana))
			return new Banana(x, y);
		else if (type.equals(E_GameObject.Pear))
			return new Pear(x, y);
		else if (type.equals(E_GameObject.Mouse))
			return new Mouse(x, y);
		else if (type.equals(E_GameObject.Obstacle))
			return new Obstacle(x, y);
		else if (type.equals(E_GameObject.Question))
			return new QuestionObject(x, y);

		return null;
	}
}
