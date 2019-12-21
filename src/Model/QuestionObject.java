package Model;

public class QuestionObject extends GameObject {
	/**
	 * Color of normal fruit
	 */
	public Question question;
	/**
	 * THIS CONSTRUCTOR SHOULD BE DELETED!!!!!!!
	 * @param x
	 * @param y
	 */
	public QuestionObject(int x, int y) {
		super(x, y);
	}
	public QuestionObject(int x, int y,Question q) {
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
