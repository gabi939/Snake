package Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Model.Board;
/**
 * Testing amount of starting objects when board is initialized, there should be 7 of them
 * 3 question object + 1 mouse + 3 fruits = 7
 *
 */
class TestStartingGameObjectsAmount {

	@Test
	void test() {
		Board testBoard = new Board();
		assertEquals(7, testBoard.getFruits().size());
	}

}

