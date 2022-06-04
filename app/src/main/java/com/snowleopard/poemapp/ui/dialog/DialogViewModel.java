package com.snowleopard.poemapp.ui.dialog;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.snowleopard.poemapp.logic.Repository;
import com.snowleopard.poemapp.logic.model.Dialog;
import com.snowleopard.poemapp.logic.model.PoemDialog;
import com.snowleopard.poemapp.ui.adapter.DialogAdapter;

import java.util.ArrayList;
import java.util.List;

public class DialogViewModel extends ViewModel {
    private List<Dialog> dialogList = new ArrayList<>();
    private List<PoemDialog> poemList = new ArrayList<>();
    private PoemDialog poemDialog= new PoemDialog(); ; //当前的
   private DialogAdapter adapter = new DialogAdapter(getDialogList());


    public DialogViewModel() {
//        dialogList.add(new Dialog("欢迎来到对王之王",Dialog.TYPE_ASK));
//        dialogList.add(new Dialog("发送任意内容开始",Dialog.TYPE_ASK));
    }

//    如果这里有一个有参构造函数，但是没有无参构造函数，在Activity里面调用View Model就会报错
//    dialogViewModel=new ViewModelProvider(this).get(DialogViewModel.class);
//    会说 不能create a instance of DialogViewModel.class

//    public DialogViewModel(DialogAdapter adapter) {
//        this.adapter = adapter;
//    }

    public DialogAdapter getAdapter() {
        return adapter;
    }

    public void setAdapter(DialogAdapter adapter) {
        this.adapter = adapter;
    }

    MutableLiveData<Integer> poemDialogMutableLiveData= new MutableLiveData<>();
    /**
     * i是回合
     */
   private int i;

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
        return dialogList;
    }

    public void setDialogList(List<Dialog> dialogList) {
        this.dialogList = dialogList;
    }


    public LiveData<List<PoemDialog>> getDialogByLevel(int level){
        return Repository.getInstance().getPoemDialogByLevel(level);
    }

//    public LiveData<PoemDialog> getPoem(){
//        return Repository.repository.getPoem(poemList,getI());
//    }


}
