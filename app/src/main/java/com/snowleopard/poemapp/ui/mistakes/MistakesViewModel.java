package com.snowleopard.poemapp.ui.mistakes;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.snowleopard.poemapp.logic.Repository;
import com.snowleopard.poemapp.logic.model.Question;
import com.snowleopard.poemapp.logic.model.UserInfo;

import java.util.ArrayList;
import java.util.List;

public class MistakesViewModel extends ViewModel {
    private List<Question> questionList=new ArrayList<>();

    public LiveData<List<Question>> getMistakesList(String userName){
        return Repository.getInstance().getMistakesList(userName);
    }

    public List<Question> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<Question> questionList) {
        this.questionList = questionList;
    }

    public UserInfo getUser(){
        return Repository.getInstance().getUserName();
    }
}
