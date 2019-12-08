package Model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Player {
	// -------------------------------Class Members------------------------------
	public String name;
	public int score;
	public Date playDate;

	// -------------------------------Constructors-------------------------------
	public Player(String name) {
		super();
		this.name = name;
		this.score = 0;
		this.playDate = new Date();
	}

	public Player(String name, int score, Date playDate) {
		super();
		this.name = name;
		this.score = score;
		this.playDate = playDate;
	}

	// -------------------------------Getters And Setters-------------------------
	public String getName() {
		return name;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public Date getPlayDate() {
		return playDate;
	}

	// -------------------------------Methods------------------------------------
	@Override
	public String toString() {
		Date date = Calendar.getInstance().getTime();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
		String strDate = dateFormat.format(date);
		return "Player [name=" + name + ", score=" + score + ", playDate=" + strDate + "]";
	}

}
