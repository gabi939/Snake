package Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Model.Board;
import Model.BodyPart;
import Model.GameState;
/**
 * Testing if game will finish if snake head hits a wall.
 * setting snake head to be on a known wall location to resemble a 'collision'
 * then calling the method, asserting that game state will be finished (meaning a restart will happen). 
 * @author david
 *
 */
class TestObstacleHit {

	@Test
	void test() {
		BodyPart head = new BodyPart(0,0);
		Board testBoard = new Board();
		assertEquals(GameState.Finished, testBoard.checkCollision(head));
	}

}
