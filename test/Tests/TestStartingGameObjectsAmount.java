package Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import Model.Board;
import Model.GameObject;
/**
 * Testing amount of starting objects when board is initialized, there should be 7 of them
 * 3 question object + 1 mouse + 3 fruits = 7
 *
 */
class TestStartingGameObjectsAmount {

	@Test
	void test() {
		Board testBoard = new Board();
		ArrayList<GameObject> allFruits = testBoard.getFruits();
		assertEquals(7, allFruits.size());
	}

}

