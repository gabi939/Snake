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
	public static GameObject getGameObject(int x, int y, E_Difficulty questionDifficulty) {
		if(questionDifficulty.equals(E_Difficulty.EASY)) {
			return new QuestionObject(x,y,Sysdata.getInstance().fetchQuestion(E_Difficulty.EASY));
		}
		if(questionDifficulty.equals(E_Difficulty.MEDIUM)) {
			return new QuestionObject(x,y,Sysdata.getInstance().fetchQuestion(E_Difficulty.MEDIUM));
		}
		if(questionDifficulty.equals(E_Difficulty.HARD)) {
			return new QuestionObject(x,y,Sysdata.getInstance().fetchQuestion(E_Difficulty.HARD));
		}
		return null;
	}

}
