package com.snowleopard.poemapp.ui.changeinfo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.snowleopard.poemapp.logic.Repository;
import com.snowleopard.poemapp.logic.model.UserInfo;

import java.util.List;

public class ChangeInfoViewModel extends ViewModel {

    public UserInfo getUser(){
        return Repository.getInstance().getUserName();
    }

    public LiveData<UserInfo> changePassword(String userName,String oldPassword,String newPassword){
        return Repository.getInstance().changePassword(userName,oldPassword,newPassword);
    }

    public LiveData<String> uploadPortrait(String userName,String filePath){
        return Repository.repository.uploadPortrait(userName,filePath);
    }

    public void savePortrait(String url){
        UserInfo userInfo = new UserInfo(url, getUser().getUsername());
         Repository.getInstance().saveUser(userInfo);
    }
}
