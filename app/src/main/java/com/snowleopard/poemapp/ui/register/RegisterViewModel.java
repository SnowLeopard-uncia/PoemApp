package com.snowleopard.poemapp.ui.register;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.snowleopard.poemapp.logic.Repository;
import com.snowleopard.poemapp.logic.model.User;
import com.snowleopard.poemapp.logic.model.UserInfo;


public class RegisterViewModel extends ViewModel {

    public LiveData<UserInfo> userRegister(User user){
        return Repository.getInstance().userRegister(user);
    }
    public void saveUser(UserInfo userInfo){
        Repository.getInstance().saveUser(userInfo);
    }
}
