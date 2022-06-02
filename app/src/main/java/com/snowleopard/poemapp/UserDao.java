package com.snowleopard.poemapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import com.snowleopard.poemapp.logic.model.User;

public class UserDao {

   @SuppressLint("CommitPrefEdits")
   public static void saveUser(String userName){
        sharePreference().edit().putString("userName",userName);
        sharePreference().edit().apply();

   }

   public static String getUserName(){
       String userName = sharePreference().getString("userName", " ");
       return userName;
   }

   public static  SharedPreferences sharePreference(){
       return MyApplication.getContext().getSharedPreferences("user_info", Context.MODE_PRIVATE);
   }
}
