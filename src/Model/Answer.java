package Model;

public class Answer {
	// -------------------------------Class Members------------------------------
	private int answerID;
	private int questionID;
	private String content;
	private boolean isCorrect;

	// -------------------------------Constructors-------------------------------
	public Answer(int answerID, int questionID, String content, boolean isCorrect) {
		super();
		this.answerID = answerID;
		this.questionID = questionID;
		this.content = content;
		this.isCorrect = isCorrect;
	}

	// -------------------------------Getters And Setters-------------------------
	public int getAnswerID() {
		return answerID;
	}

	public void setAnswerID(int answerID) {
		this.answerID = answerID;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getQuestionID() {
		return questionID;
	}

	public void setQuestionID(int questionID) {
		this.questionID = questionID;
	}

	public boolean isCorrect() {
		return isCorrect;
	}

	public void setCorrect(boolean isCorrect) {
		this.isCorrect = isCorrect;
	}

	@Override
	public String toString() {
		return "Answer [answerID=" + answerID + ", questionID=" + questionID + ", content=" + content + ", isCorrect="
				+ isCorrect + "]";
	}

}
