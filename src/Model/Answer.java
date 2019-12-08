package Model;

public class Answer {
	// -------------------------------Class Members------------------------------
	private int answerID;
	private String content;
	private boolean isCorrect;

	// -------------------------------Constructors-------------------------------
	public Answer(int answerID, String content, boolean isCorrect) {
		super();
		this.answerID = answerID;
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

	public boolean getIsCorrect() {
		return isCorrect;
	}

	public void setCorrect(boolean isCorrect) {
		this.isCorrect = isCorrect;
	}

	@Override
	public String toString() {
		return "Answer [answerID=" + answerID + ", content=" + content + ", isCorrect=" + isCorrect + "]";
	}

}
