package com.snowleopard.poemapp;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.snowleopard.poemapp.logic.Repository;
import com.snowleopard.poemapp.logic.model.Poem;

import java.util.List;

public class MainViewModel extends ViewModel {

    public LiveData<List<Poem>> getPoemByLevelP(){
        return Repository.getInstance().getPoemByLevelP();
    }

    public LiveData<List<Poem>> getPoemByLevelM(){
        return Repository.getInstance().getPoemByLevelM();
    }

    public LiveData<List<Poem>> getPoemByLevelH(){
        return Repository.getInstance().getPoemByLevelH();
    }

}
