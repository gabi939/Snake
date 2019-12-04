package Model;

import java.util.ArrayList;

import Utils.E_Difficulty;

public class Question {
	// -------------------------------Class Members------------------------------
	private int questionID;
	private String content;
	private E_Difficulty difficulty;
	private ArrayList<Answer> answers;
	private int correct_ans;
	private String team;

	// -------------------------------Constructors-------------------------------

	public Question(int questionID, String content, E_Difficulty difficulty, ArrayList<Answer> answers, int correct_ans,
			String team) {
		super();
		this.questionID = questionID;
		this.content = content;
		this.difficulty = difficulty;
		this.answers = answers;
		this.correct_ans = correct_ans;
		this.team = team;
	}

	// -------------------------------Getters And Setters-------------------------
	public int getQuestionID() {
		return questionID;
	}

	public void setQuestionID(int questionID) {
		this.questionID = questionID;
	}

	public E_Difficulty getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(E_Difficulty difficulty) {
		this.difficulty = difficulty;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public ArrayList<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(ArrayList<Answer> answers) {
		this.answers = answers;
	}

	public int getCorrect_ans() {
		return correct_ans;
	}

	public void setCorrect_ans(int correct_ans) {
		this.correct_ans = correct_ans;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	// -------------------------------Methods------------------------------------

	// wanna check if the same answer already exists (?)
	public boolean addAnswer(Answer answer, boolean isCorrect) {
		return true;
	}

	// or do you want to send the id only (?)
	public boolean deleteAnswer(Answer answer) {
		if (answer != null) {
			answers.remove(answers.indexOf(answer));
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "Question [questionID=" + questionID + ", content=" + content + ", difficulty=" + difficulty
				+ ", answers=" + answers + ", correct_ans=" + correct_ans + ", team=" + team + "]";
	}

}
