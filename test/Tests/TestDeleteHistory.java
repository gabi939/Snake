package Tests;
/**
 * Tests if method deleting hiscore history is returning an empty list, not a null one and deleted all items. 
 */
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Controller.Sysdata;

class TestDeleteHistory {

	@Test
	void test() {
		Sysdata instance = Sysdata.getInstance();
		instance.deleteGameHistory();
		assertTrue(instance.getHistory().isEmpty());
	}

}
