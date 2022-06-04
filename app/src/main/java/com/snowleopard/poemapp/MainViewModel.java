package com.snowleopard.poemapp;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.snowleopard.poemapp.logic.Repository;
import com.snowleopard.poemapp.logic.model.Poem;
import com.snowleopard.poemapp.logic.model.PoemDialog;
import com.snowleopard.poemapp.logic.model.UserInfo;

import java.util.ArrayList;
import java.util.List;

public class MainViewModel extends ViewModel {
    //ViewModel设置共享值

    private List<Poem> poemList = new ArrayList<>();

    public List<Poem> getPoemList() {
        return poemList;
    }

    public void setPoemList(List<Poem> poemList) {
        this.poemList = poemList;
    }

    public LiveData<List<Poem>> getPoemByLevelP() {
        return Repository.getInstance().getPoemByLevelP();
    }

    public LiveData<List<Poem>> getPoemByLevelM() {
        return Repository.getInstance().getPoemByLevelM();
    }

    public LiveData<List<Poem>> getPoemByLevelH() {
        return Repository.getInstance().getPoemByLevelH();
    }

    //    public LiveData<List<PoemDialog>> getPoemDialogByLevel(int level){
//        return Repository.repository.getPoemDialogByLevel(level);
//    }
    public UserInfo getUser() {
       return Repository.getInstance().getUserName();
    }

}
