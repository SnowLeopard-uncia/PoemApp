package com.snowleopard.poemapp.ui.collection;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.snowleopard.poemapp.logic.Repository;
import com.snowleopard.poemapp.logic.model.Poem;
import com.snowleopard.poemapp.logic.model.UserInfo;
import com.snowleopard.poemapp.ui.adapter.CollectionAdapter;

import java.util.ArrayList;
import java.util.List;

public class CollectionViewModel extends ViewModel {

    private List<Poem> poemList = new ArrayList<>();

    public List<Poem> getPoemList() {
        return poemList;
    }

    public void setPoemList(List<Poem> poemList) {
        this.poemList = poemList;
    }

    public LiveData<List<Poem>> getCollectionList(String userName){
        return Repository.getInstance().getCollection(userName);
    }

    public UserInfo getUser() {
        return Repository.getInstance().getUserName();
    }
}
