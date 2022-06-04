package com.snowleopard.poemapp.ui.test;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.snowleopard.poemapp.logic.Repository;
import com.snowleopard.poemapp.logic.model.CorrectMistakes;
import com.snowleopard.poemapp.logic.model.Question;

import java.util.ArrayList;
import java.util.List;

public class TestViewModel extends ViewModel {
    private List<Question> questionList = new ArrayList<>();
    private int i=0; //回合
    private String answer;
    private Question question;
    private boolean isGetRight = false;
    private CorrectMistakes correctMistakes = new CorrectMistakes(0,0,0.0);

    public CorrectMistakes getCorrectMistakes() {
        return correctMistakes;
    }

    public void setCorrectMistakes(CorrectMistakes correctMistakes) {
        this.correctMistakes = correctMistakes;
    }

    public boolean isGetRight() {
        return isGetRight;
    }

    public void setGetRight(boolean getRight) {
        isGetRight = getRight;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

     MutableLiveData<Integer> mutableLiveData = new MutableLiveData<>();

    public List<Question> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<Question> questionList) {
        this.questionList = questionList;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public LiveData<List<Question>> getQuestions(String level, String type){
        return Repository.getInstance().getQuestion(level,type);
    }
}
