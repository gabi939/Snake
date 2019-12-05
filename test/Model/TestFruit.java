package Model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestFruit {

	@Test
	final void testFruit() {
		for(int i = 0 ; i < 1000 ; i++)	{
			int x = (int) Math.random();
			int y = (int) Math.random();
			Fruit b = new Fruit(x, y);
			assertNotNull(b);
		}
	}

}
