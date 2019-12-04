package Controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import Model.Answer;
import Model.Question;
import Utils.E_Difficulty;

public class Sysdata {

	private static Sysdata instance;

	public static Sysdata getInstance() {
		if (instance == null)
			instance = new Sysdata();
		return instance;
	}

	/*
	 * This method reads the questions written in JSON file and returns them in an
	 * array list
	 */
	@SuppressWarnings("deprecation")
	public ArrayList<Question> readQuestionsJSON() throws Exception {
		try {
			ArrayList<Question> arrlistq = new ArrayList<Question>();
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
						Answer an = new Answer(i, k, content, true);
						arrlista.add(an);
					} else {
						Answer an = new Answer(i, k, content, false);
						arrlista.add(an);
					}
					i++;
				}
				E_Difficulty difficulty = E_Difficulty.returnEnum((String) jsonQObjt.get("level"));
				String team = (String) jsonQObjt.get("team");
				Question q = new Question(k, context, difficulty, arrlista, correct_ans, team);
				k++;
				arrlistq.add(q);
			}
			return arrlistq;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	/*
	 * Given an array list this method overrides the JSON questions file with the
	 * questions in the array list
	 */
	@SuppressWarnings({ "deprecation", "unchecked" })
	public boolean addQuestionToJSON(ArrayList<Question> arrlistq) throws Exception {
		try {
			JSONObject jo = new JSONObject();
			JSONArray ja = new JSONArray();

			for (Question q : arrlistq) {
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

			return true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean addGameHistory() {
		return true;
	}

}
