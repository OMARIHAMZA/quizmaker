package hamza.quizmaker.models;

import java.io.Serializable;
import java.util.ArrayList;

public class Question implements Serializable {

    private String questionTitle;
    private ArrayList<String> choices;
    private int correctAnswerIndex;

    public Question(String questionTitle, ArrayList<String> choices, int correctAnswerIndex) {
        this.questionTitle = questionTitle;
        this.choices = choices;
        this.correctAnswerIndex = correctAnswerIndex;
    }

    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }

    public void setCorrectAnswerIndex(int correctAnswerIndex) {
        this.correctAnswerIndex = correctAnswerIndex;
    }

    public String getQuestionTitle() {
        return questionTitle;
    }

    public void setQuestionTitle(String questionTitle) {
        this.questionTitle = questionTitle;
    }

    public ArrayList<String> getChoices() {
        return choices;
    }

    public void setChoices(ArrayList<String> choices) {
        this.choices = choices;
    }
}
