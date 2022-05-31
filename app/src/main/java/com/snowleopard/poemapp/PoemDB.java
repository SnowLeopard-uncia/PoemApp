package com.snowleopard.poemapp;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.snowleopard.poemapp.logic.PoemDialogDao;
import com.snowleopard.poemapp.logic.model.PoemDialog;

@Database(entities = {PoemDialog.class},version = 1,exportSchema = false)
public abstract class PoemDB extends RoomDatabase {
    public abstract PoemDialogDao poemDialogDao();
    //用dao去访问数据库操作方法
    private static volatile PoemDB INSTANCE;

    public static PoemDB getDatabase(Context context){
        if (INSTANCE==null){
            synchronized (PoemDB.class){
            if (INSTANCE==null){
                INSTANCE= Room.databaseBuilder(context.getApplicationContext(),
                        PoemDB.class,"poem_database")
                        .addCallback(RoomDatabasesCallBack)
                        .build();
                }
            }
        }
        return INSTANCE;
    }

    public static Callback RoomDatabasesCallBack=new Callback() {
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new InsertDBAsync(INSTANCE).execute();
        }
    };

    private static class InsertDBAsync extends AsyncTask<Void,Void,Void>{
        private final PoemDialogDao dialogDao;

        public InsertDBAsync(PoemDB instance) {
            dialogDao=instance.poemDialogDao();
        }

        @Override
        protected Void doInBackground(Void... voids) { //初始化
            dialogDao.deleteAll();
            PoemDialog poemDialog = new PoemDialog("床前明月光","疑是地上霜",0);
            dialogDao.insert(poemDialog);
            poemDialog=new PoemDialog("千山鸟飞绝","万径人踪灭",0);
            dialogDao.insert(poemDialog);
            poemDialog=new PoemDialog("春眠不觉晓","处处闻啼鸟",0);
            dialogDao.insert(poemDialog);
            poemDialog=new PoemDialog("夜来风雨声","花落知多少",0);
            dialogDao.insert(poemDialog);
            poemDialog=new PoemDialog("日照香炉生紫烟","遥看瀑布挂前川",0);
            dialogDao.insert(poemDialog);

            poemDialog=new PoemDialog("君问归期未有期","巴山夜雨涨秋池",1);
            dialogDao.insert(poemDialog);
            poemDialog=new PoemDialog("何当共剪西窗烛","却话巴山夜雨时",1);
            dialogDao.insert(poemDialog);
            poemDialog = new PoemDialog("国破山河在","城春草木深",1);
            dialogDao.insert(poemDialog);
            poemDialog = new PoemDialog("烽火连三月","家书抵万金",1);
            dialogDao.insert(poemDialog);

            poemDialog = new PoemDialog("锦瑟无端五十弦","一弦一柱思华年",2);
            dialogDao.insert(poemDialog);
            poemDialog = new PoemDialog("沧海月明珠有泪","蓝田日暖玉生烟",2);
            dialogDao.insert(poemDialog);
            poemDialog = new PoemDialog("问君能有几多愁","恰似一江春水向东流",2);
            dialogDao.insert(poemDialog);
            poemDialog = new PoemDialog("无边落木萧萧下","不尽长江滚滚来",2);
            dialogDao.insert(poemDialog);
            return null;
        }
    }

}
