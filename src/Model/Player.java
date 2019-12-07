package Model;

import java.util.Calendar;


public class Player {

	public String name;
	public int score; 
	public Calendar playDate;
	public Player(String name, int score, Calendar playDate) {
		super();
		this.name = name;
		this.score = 0;
		this.playDate = Calendar.getInstance();
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	
}
