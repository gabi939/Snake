package Model;

/**
 * Class QuestionObject ~ represents a Question Object in the game
 * @author David Duchovni
 * @author Nareed Hashem
 * 
 */
public class QuestionObject extends GameObject {
	private Question question;

	public QuestionObject(int x, int y, Question q) {
		super(x, y);
		this.question = q; 
	}
	public Question getQuestion() {
		return question;
	}
	public void setQuestion(Question question) {
		this.question = question;
	}
	
}
