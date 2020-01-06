package Controller;

import Model.Apple;
import Model.Banana;
import Model.GameObject;
import Model.Mouse;
import Model.Obstacle;
import Model.Pear;
import Model.QuestionObject;
import Utils.E_Difficulty;
import Utils.E_GameObject;
/**
 * Class ObjectFactory ~ creates instances of game objects
 * @author Gabi Malin
 * @author David Duchovni
 * 
 */
public class ObjectFactory {
	/**
	 * returns a game object type
	 * @param type
	 * @param x
	 * @param y
	 * @return
	 */
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

		return null;
	}
	/**
	 * returns a question based on a given difficulty
	 * @param x
	 * @param y
	 * @param questionDifficulty
	 * @return
	 */
	public static GameObject getGameObject(int x, int y, E_Difficulty questionDifficulty) {
		if (questionDifficulty.equals(E_Difficulty.EASY)) {
			return new QuestionObject(x,y,Sysdata.getInstance().fetchQuestion(E_Difficulty.EASY));
		}
		if (questionDifficulty.equals(E_Difficulty.MEDIUM)) {
			return new QuestionObject(x,y,Sysdata.getInstance().fetchQuestion(E_Difficulty.MEDIUM));
		}
		if (questionDifficulty.equals(E_Difficulty.HARD)) {
			return new QuestionObject(x,y,Sysdata.getInstance().fetchQuestion(E_Difficulty.HARD));
		}
		return null;
	}

}
