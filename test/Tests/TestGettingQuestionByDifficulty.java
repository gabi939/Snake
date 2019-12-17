package Tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import Controller.Sysdata;
import Model.Question;
import Utils.E_Difficulty;
/**
 * Test case to check if we have at least 1 question of every difficulty level in our JSon file and that the java codes reads it correctly. 
 *
 */
class TestGettingQuestionByDifficulty {

	@Test
	void test() {
		Sysdata instance = Sysdata.getInstance();
		instance.getQuestionsarr();
		ArrayList<Question> hardQtest = instance.fetchQuestionsArr(E_Difficulty.HARD);
		ArrayList<Question> medQtest = instance.fetchQuestionsArr(E_Difficulty.MEDIUM);
		ArrayList<Question> easyQtest = instance.fetchQuestionsArr(E_Difficulty.EASY);
		assertFalse(hardQtest.isEmpty());
		assertFalse(medQtest.isEmpty());
		assertFalse(easyQtest.isEmpty());

	}

}
