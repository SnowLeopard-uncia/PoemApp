package com.snowleopard.poemapp.ui.dialog;

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
    private PoemDialog poemDialog= new PoemDialog(); ; //当前的
    /**
     * i是回合
     */
   private int i=0;

    public PoemDialog getPoemDialog() {
        return poemDialog;
    }

    public void setPoemDialog(PoemDialog poemDialog) {
        this.poemDialog = poemDialog;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public List<PoemDialog> getPoemList() {
        return poemList;
    }

    public void setPoemList(List<PoemDialog> poemList) {
        this.poemList = poemList;
    }

    public List<Dialog> getDialogList() {
//        dialogList.add(new Dialog("你好鸦",Dialog.TYPE_ANSWER));
//        dialogList.add(new Dialog("helli?",Dialog.TYPE_ASK));
        return dialogList;
    }

    public void setDialogList(List<Dialog> dialogList) {
        this.dialogList = dialogList;
    }


    public LiveData<List<PoemDialog>> getDialogByLevel(int level){
        return Repository.getInstance().getPoemDialogByLevel(level);
    }

    public LiveData<PoemDialog> getPoem(){
        return Repository.repository.getPoem(poemList,getI());
    }


}
