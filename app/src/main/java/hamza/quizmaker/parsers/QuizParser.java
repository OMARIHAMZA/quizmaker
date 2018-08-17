package hamza.quizmaker.parsers;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import hamza.quizmaker.models.Question;

public class QuizParser {

    public static ArrayList<Question> ParseQuiz(JSONArray jsonArray) {
        try {
            ArrayList<Question> questions = new ArrayList<>();
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                JSONObject choices = new JSONObject(jsonObject1.getString("options"));
                ArrayList<String> choicesList = new ArrayList<>();
                choicesList.add(choices.getString("a"));
                choicesList.add(choices.getString("b"));
                choicesList.add(choices.getString("c"));
                choicesList.add(choices.getString("d"));
                questions.add(new Question(jsonObject1.getString("question"), choicesList, jsonObject1.getInt("answere")));
            }
            return questions;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

}
