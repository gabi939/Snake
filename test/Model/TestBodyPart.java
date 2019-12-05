package Model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestBodyPart {

	@Test
	final void testBodyPart() {
		
	for(int i = 0 ; i < 1000 ; i++)	{
		int x = (int) Math.random();
		int y = (int) Math.random();
		BodyPart b = new BodyPart(x, y);
		assertNotNull(b);
	}
	}
}
