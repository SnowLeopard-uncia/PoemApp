package com.snowleopard.poemapp.ui.login;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.snowleopard.poemapp.logic.Repository;
import com.snowleopard.poemapp.logic.model.UserInfo;
import com.snowleopard.poemapp.logic.model.User;

public class LoginViewModel extends ViewModel {

    public LiveData<UserInfo> userLogin(User user){
        return Repository.getInstance().userLogin(user);
    }

    public void saveUserName(String userName){
        Repository.getInstance().saveUserName(userName);
    }

}
