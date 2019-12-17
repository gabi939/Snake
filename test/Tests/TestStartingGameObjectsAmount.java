package Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Model.Board;

class TestStartingGameObjectsAmount {

	@Test
	void test() {
		Board testBoard = new Board();
		assertEquals(7, testBoard.getFruits().size());
	}

}

