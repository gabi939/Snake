package Model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestGameObject {

	@Test
	final void testGameObject() {
		for(int i = 0 ; i < 1000 ; i++)	{
			int x = (int) Math.random();
			int y = (int) Math.random();
			GameObject b = new GameObject(x,y);
			assertNotNull(b);
		}
		}
	}

	@Test
	final void testGetX() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	final void testGetY() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	final void testSetX() {
		fail("Not yet implemented"); // TODO
	}

	@Test
	final void testSetY() {
		fail("Not yet implemented"); // TODO
	}

}
