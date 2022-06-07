package com.snowleopard.poemapp.logic;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import com.snowleopard.poemapp.MyApplication;
import com.snowleopard.poemapp.logic.model.User;
import com.snowleopard.poemapp.logic.model.UserInfo;

public class UserDao {


   public static void saveUser(UserInfo userInfo){
        sharePreference().edit()
                .putString("userName",userInfo.getUsername())
                .putString("userHead",userInfo.getProPath())
                .apply();
//        sharePreference().edit().commit();

   }

   public static UserInfo getUser(){
       String userName = sharePreference().getString("userName", "hello");
       String uesrHead = sharePreference().getString("userHead","");
       return new UserInfo(uesrHead,userName);
   }

   public static  SharedPreferences sharePreference(){
       return MyApplication.getContext().getSharedPreferences("user_info", Context.MODE_PRIVATE);
   }
}
