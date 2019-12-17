package Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Model.Board;
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
		Board testBoard = new Board();
		testBoard.getSnake().getHead().setX(0);
		testBoard.getSnake().getHead().setY(0);
		assertEquals(GameState.Finished, testBoard.checkCollision());
	}

}
