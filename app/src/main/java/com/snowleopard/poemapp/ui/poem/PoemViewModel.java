package com.snowleopard.poemapp.ui.poem;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.snowleopard.poemapp.logic.Repository;
import com.snowleopard.poemapp.logic.model.Poem;
import com.snowleopard.poemapp.logic.model.UserInfo;

import java.util.List;

public class PoemViewModel extends ViewModel {

    public String getUserName(){
        return Repository.getInstance().getUserName().getUsername();
    }

    public LiveData<Integer> collect(String pid,String userName){
        return Repository.repository.collect(pid, userName);
    }

    public LiveData<Integer> collectDelete(String pid,String userName){
        return Repository.repository.collectDelete(pid, userName);
    }
    public LiveData<List<Poem>> getCollectionList(String userName){
        return Repository.getInstance().getCollection(userName);
    }

    public UserInfo getUser() {
        return Repository.getInstance().getUserName();
    }
    //关于在View Model写业务逻辑的尝试
    private void collect() {

    }

    private void cancelCollect() {

    }
}
