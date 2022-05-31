package com.snowleopard.poemapp;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.snowleopard.poemapp.logic.Repository;
import com.snowleopard.poemapp.logic.model.Dialog;
import com.snowleopard.poemapp.logic.model.PoemDialog;

import java.util.ArrayList;
import java.util.List;

public class DialogViewModel extends ViewModel {
    private List<Dialog> dialogList = new ArrayList<>();
    private List<PoemDialog> poemList = new ArrayList<>();

    public List<PoemDialog> getPoemList() {
        return poemList;
    }

    public void setPoemList(List<PoemDialog> poemList) {
        this.poemList = poemList;
    }

    public List<Dialog> getDialogList() {
        return dialogList;
    }

    public void setDialogList(List<Dialog> dialogList) {
        this.dialogList = dialogList;
    }


    public LiveData<List<PoemDialog>> getDialogListByLevel(int level){
        return Repository.getInstance().getPoemDialogByLevel(level);
    }


}
