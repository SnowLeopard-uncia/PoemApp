package com.snowleopard.poemapp.logic;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.snowleopard.poemapp.MyApplication;
import com.snowleopard.poemapp.PoemDB;
import com.snowleopard.poemapp.logic.model.PoemDialog;
import com.snowleopard.poemapp.ui.dialog.DialogActivity;

import java.util.List;

/**
 * 对王之王 模拟后端
 */
public class PoemDialogService {
    static MutableLiveData<PoemDialog> dialog = new MutableLiveData<>();

     static LiveData<List<PoemDialog>> poemDialogs;

     static LiveData<PoemDialog> getPoem(List<PoemDialog> list, int i){
         if (list.size()!=0){
             dialog.postValue(list.get(i));
         }
        return dialog;
    }

}
