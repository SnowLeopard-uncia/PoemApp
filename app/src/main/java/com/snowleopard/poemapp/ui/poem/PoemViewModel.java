package com.snowleopard.poemapp.ui.poem;

import androidx.lifecycle.ViewModel;

import com.snowleopard.poemapp.logic.Repository;

public class PoemViewModel extends ViewModel {

    public String getUserName(){
        return Repository.getInstance().getUserName().getUsername();
    }

}
