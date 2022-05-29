package com.snowleopard.poemapp;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.snowleopard.poemapp.logic.PoemDialogDao;
import com.snowleopard.poemapp.logic.model.PoemDialog;

@Database(entities = {PoemDialog.class},version = 1,exportSchema = false)
public abstract class PoemDB extends RoomDatabase {
    public abstract PoemDialogDao poemDialogDao();
//用dao去访问数据库操作方法

}
