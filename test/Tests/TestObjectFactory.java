package Tests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import Controller.ObjectFactory;
import Model.Apple;
import Model.Banana;
import Model.GameObject;
import Model.Mouse;
import Model.Pear;
import Model.QuestionObject;
import Utils.E_GameObject;
/**
 * Checking is Factory method returns correct instances of objects
 *
 */
class TestObjectFactory {

	@Test
	void test() {
		GameObject appleTest = ObjectFactory.getGameObject(E_GameObject.Apple, 10, 10);
		GameObject bananaTest = ObjectFactory.getGameObject(E_GameObject.Banana, 10, 10);
		GameObject pearTest = ObjectFactory.getGameObject(E_GameObject.Pear, 10, 10);
		GameObject mouseTest = ObjectFactory.getGameObject(E_GameObject.Mouse, 10, 10);
		GameObject questionTest = ObjectFactory.getGameObject(E_GameObject.Question, 10, 10);

		assertTrue(appleTest  instanceof Apple);
		assertTrue(bananaTest  instanceof Banana);
		assertTrue(pearTest  instanceof Pear);
		assertTrue(mouseTest  instanceof Mouse);
		assertTrue(questionTest  instanceof QuestionObject);

	}

}
