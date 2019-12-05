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

	public Question(int questionID, String content, E_Difficulty difficulty, ArrayList<Answer> answers,
			int correct_ans) {
		super();
		this.questionID = questionID;
		this.content = content;
		this.difficulty = difficulty;
		this.answers = answers;
		this.correct_ans = correct_ans;
		this.team = "Sloth";
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((answers == null) ? 0 : answers.hashCode());
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		result = prime * result + correct_ans;
		result = prime * result + ((difficulty == null) ? 0 : difficulty.hashCode());
		result = prime * result + questionID;
		result = prime * result + ((team == null) ? 0 : team.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Question other = (Question) obj;
		if (answers == null) {
			if (other.answers != null)
				return false;
		} else if (!answers.equals(other.answers))
			return false;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		if (correct_ans != other.correct_ans)
			return false;
		if (difficulty != other.difficulty)
			return false;
		if (questionID != other.questionID)
			return false;
		if (team == null) {
			if (other.team != null)
				return false;
		} else if (!team.equals(other.team))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Question [questionID=" + questionID + ", content=" + content + ", difficulty=" + difficulty
				+ ", answers=" + answers + ", correct_ans=" + correct_ans + ", team=" + team + "]";
	}

}
