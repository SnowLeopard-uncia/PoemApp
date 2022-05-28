package com.snowleopard.poemapp.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.snowleopard.poemapp.logic.Repository;
import com.snowleopard.poemapp.logic.model.User;
import com.snowleopard.poemapp.logic.model.UserLogin;

public class LoginViewModel extends ViewModel {

    public LiveData<User> userLogin(UserLogin userLogin){
        return Repository.getInstance().userLogin(userLogin);
    }



}
