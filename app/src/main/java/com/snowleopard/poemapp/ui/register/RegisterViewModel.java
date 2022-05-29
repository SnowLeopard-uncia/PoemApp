package com.snowleopard.poemapp.ui.register;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.snowleopard.poemapp.logic.Repository;
import com.snowleopard.poemapp.logic.model.User;


public class RegisterViewModel extends ViewModel {

    public LiveData<String> userRegister(User user){
        return Repository.getInstance().userRegister(user);
    }
}
