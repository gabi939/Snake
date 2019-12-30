package Controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

import Model.Answer;
import Model.Player;
import Model.Question;
import Utils.E_Difficulty;

public class Sysdata {

	private static Sysdata instance;
	private static Player player;
	private ArrayList<Question> questionsarr = new ArrayList<>();
	private ArrayList<Player> prevGames = new ArrayList<>();

	public static Sysdata getInstance() {
		if (instance == null)
			instance = new Sysdata();
		return instance;
	}

	// ------------- Set&Get for Player -----------------

	public static Player getPlayer() {
		return player;
	}

	public static void setPlayer(Player player) {
		Sysdata.player = player;
	}

	// ------------- Manipulate Questions -----------------

	/*
	 * getQuestionsarr reads the JSON file with the questions and returns an array
	 * list containing them.
	 */
	public ArrayList<Question> getQuestionsarr() {
		readQuestionsJSON();
		return questionsarr;
	}

	/*
	 * given a difficulty returns an array list of all questions with the given
	 * difficulty.
	 */
	public ArrayList<Question> fetchQuestionsArr(E_Difficulty diff) {
		questionsarr = getQuestionsarr();
		ArrayList<Question> choosenQuestions = new ArrayList<Question>();
		for (Question q : questionsarr) {
			if (q.getDifficulty().equals(diff))
				choosenQuestions.add(q);
		}
		return choosenQuestions;
	}

	/*
	 * given a difficulty returns a random question with the requested difficulty.
	 */
	public Question fetchQuestion(E_Difficulty diff) {
		ArrayList<Question> arr = fetchQuestionsArr(diff);
		Random rand = new Random();
		int s = arr.size();
		if (s > 0) {
			Question choosenQuestion = arr.get(rand.nextInt(s));
			return choosenQuestion;
		} else {
			System.out.println("there are no questions with difficulty " + diff.toString());
			return null;
		}
	}

	/*
	 * given a question adds it to the data set of questions (the questionarr) and
	 * to the JSON file by rewriting it iff we don't already have the same question.
	 */
	public boolean addQuestion(Question q) {
		for (Question question : questionsarr) {
			if (question.equals(q)) {
				System.out.println("we already have this question");
				return false;
			}
			questionsarr.add(q);
			try {
				writeQuestionsToJSON();
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		return false;
	}

	/*
	 * given a question removes it from the array list and from the JSON file.
	 */
	public boolean removeQuestion(Question q) {
		if (q != null) {
			questionsarr.remove(questionsarr.indexOf(q));
			writeQuestionsToJSON();
			readQuestionsJSON();
			return true;
		}
		return false;
	}

	/*
	 * given an old and new Question adds the new one and removes the old.
	 */
	public boolean editQuestion(Question old, Question newq) {
		if (old != null && newq != null) {
			if (removeQuestion(old))
				if (addQuestion(newq))
					return true;
		}
		return false;
	}

	// ------------- Manipulate Game History -----------------

	/*
	 * getHistory reads the JSON file with the previous games and returns an array
	 * list containing them.
	 */
	public ArrayList<Player> getHistory() {
		readHistoryJSON();
		return prevGames;
	}

	/*
	 * given a player instance which holds a game that ended adds it to the data set
	 * of history games and to JSON file by rewriting it.
	 */
	public boolean addGameHistory(Player player) {
		if (player != null) {
			prevGames.add(player);
			writeHistoryToJSON();
			readHistoryJSON();
		}
		return false;
	}

	/*
	 * Deletes all game history from the data set and JSON file.
	 */
	public void deleteGameHistory() {
		for (int i = prevGames.size(); i > 0; i--)
			prevGames.remove(i - 1);
		writeHistoryToJSON();
	}

	// ------------- Read Write from JSON -----------------

	/*
	 * This method reads the questions written in JSON file and saves them in an
	 * array list.
	 */
	@SuppressWarnings("deprecation")
	public void readQuestionsJSON() {
		questionsarr = new ArrayList<Question>();
		try {
			if (questionsarr.isEmpty())
				for (int i = 0; i < questionsarr.size(); i++) {
					questionsarr.remove(i);
				}
			int k = 1;
			Object obj = new JSONParser().parse(new FileReader("questionsJSON.json"));
			JSONObject jo = (JSONObject) obj;
			JSONArray arr = (JSONArray) jo.get("questions");

			for (Object questionObj : arr) {
				JSONObject jsonQObjt = (JSONObject) questionObj;
				String context = (String) jsonQObjt.get("question");
				int correct_ans = Integer.parseInt((String) jsonQObjt.get("correct_ans"));
				JSONArray answersarr = (JSONArray) jsonQObjt.get("answers");
				ArrayList<Answer> arrlista = new ArrayList<Answer>();
				Iterator<?> itr = answersarr.iterator();
				int i = 1;
				while (itr.hasNext()) {
					String content = itr.next().toString();
					if (i == correct_ans) {
						Answer an = new Answer(i, content, true);
						arrlista.add(an);
					} else {
						Answer an = new Answer(i, content, false);
						arrlista.add(an);
					}
					i++;
				}
				E_Difficulty difficulty = E_Difficulty.returnEnum((String) jsonQObjt.get("level"));
				Question q = new Question(k, context, difficulty, arrlista, correct_ans);
				k++;
				questionsarr.add(q);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * Given an array list this method overrides the JSON questions file with the
	 * questions in the array list
	 */
	@SuppressWarnings({ "deprecation", "unchecked" })
	public void writeQuestionsToJSON() {
		try {
			JSONObject jo = new JSONObject();
			JSONArray ja = new JSONArray();

			for (Question q : questionsarr) {
				@SuppressWarnings("rawtypes")
				Map m = new LinkedHashMap(5);
				m.put("question", q.getContent());
				List<String> list = new ArrayList<>();
				for (Answer a : q.getAnswers()) {
					list.add(a.getContent());
				}
				JSONArray answersarr = new JSONArray(list);
				m.put("answers", answersarr);
				m.put("correct_ans", "" + q.getCorrect_ans());
				m.put("level", "" + q.getDifficulty().getNumber());
				m.put("team", q.getTeam());
				ja.add(m);
			}
			jo.put("questions", ja);
			PrintWriter pw = new PrintWriter("questionsJSON.json");
			pw.write(jo.toJSONString());
			pw.flush();
			pw.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

	/*
	 * This method reads the history games written in JSON file and saves them in an
	 * array list.
	 */
	@SuppressWarnings("deprecation")
	public void readHistoryJSON() {
		prevGames = new ArrayList<Player>();
		try {
			if (prevGames.isEmpty())
				for (int i = 0; i < prevGames.size(); i++)
					prevGames.remove(i);
			Object obj = new JSONParser().parse(new FileReader("historyJSON.json"));
			JSONObject jo = (JSONObject) obj;
			JSONArray arr = (JSONArray) jo.get("games");

			for (Object Obj : arr) {
				JSONObject jsonQObjt = (JSONObject) Obj;
				String playername = (String) jsonQObjt.get("player");
				int score = Integer.parseInt((String) jsonQObjt.get("score"));
				String dateStr = (String) jsonQObjt.get("date");
				SimpleDateFormat sdf = new SimpleDateFormat("EE MMM dd HH:mm:ss z yyyy", Locale.ENGLISH);
				Date gameDate = new java.util.Date(sdf.parse(dateStr).getTime());
				Player p = new Player(playername, score, gameDate);
				prevGames.add(p);
			}
			// sort the array
			Collections.sort(prevGames, new Comparator<Player>() {
				@Override
				public int compare(Player p1, Player p2) {
					return (p1.getScore() < p2.getScore() ? 1 : (p1.getScore() == p2.getScore() ? 0 : -1));
				}
			});
			// keeping top ten games only
			ArrayList<Player> topten = (ArrayList<Player>) prevGames.subList(0, 9);
			prevGames = topten;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * Given an array list this method overrides the JSON history file with the
	 * history games in the array list
	 */
	@SuppressWarnings("unchecked")
	public void writeHistoryToJSON() {
		try {
			JSONObject jo = new JSONObject();
			JSONArray ja = new JSONArray();

			for (Player p : prevGames) {
				@SuppressWarnings("rawtypes")
				Map m = new LinkedHashMap(3);
				m.put("player", p.getName());
				m.put("score", "" + p.getScore());
				m.put("date", p.getPlayDate().toString());
				ja.add(m);
			}
			jo.put("games", ja);
			PrintWriter pw = new PrintWriter("historyJSON.json");
			pw.write(jo.toJSONString());
			pw.flush();
			pw.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
