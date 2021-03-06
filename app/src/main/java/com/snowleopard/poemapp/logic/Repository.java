package com.snowleopard.poemapp.logic;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


import com.snowleopard.poemapp.MyApplication;
import com.snowleopard.poemapp.logic.model.Poem;
import com.snowleopard.poemapp.logic.model.PoemDialog;
import com.snowleopard.poemapp.logic.model.Question;
import com.snowleopard.poemapp.logic.model.UserInfo;
import com.snowleopard.poemapp.logic.model.User;
import com.snowleopard.poemapp.logic.network.PoemNetWork;

import java.util.List;

/**
 * 一般在仓库层中定义的方法，为了能将异步获取的数据以响应式编程的方式通知给上一层，通常会返回一个LiveData对象。
 */
public class Repository {

    private PoemDialogDao dialogDao;
    private LiveData<List<PoemDialog>> dialogList;
    private PoemDialog poemDialog;
    public static Repository repository;


    public static Repository getInstance(){
        if (repository==null){
            repository=new Repository(MyApplication.getContext());
        }
        return repository;
    }

    public Repository(Context context) {
        PoemDB db =PoemDB.getDatabase(context);
        dialogDao= db.poemDialogDao();
    }

    private MutableLiveData<User> userLiveData=new MutableLiveData<>();

    public LiveData<UserInfo> userLogin(User user){
       return PoemNetWork.userLogin(user);
    }

    public LiveData<UserInfo> userRegister(User user){
        return PoemNetWork.userRegister(user);
    }

    public LiveData<List<Poem>> getPoemByLevelP(){
        return PoemNetWork.getPoemByLevelP();
    }

    public LiveData<List<Poem>> getPoemByLevelM(){
        return PoemNetWork.getPoemByLevelM();
    }

    public LiveData<List<Poem>> getPoemByLevelH(){
        return PoemNetWork.getPoemByLevelH();
    }

    public LiveData<List<PoemDialog>> getPoemDialogByLevel(int level){
        dialogList =dialogDao.getAllPoemsByLevel(level);
        return dialogList;
    }

    public void saveUser(UserInfo userInfo){
        UserDao.saveUser(userInfo);
    }

    public UserInfo getUserName(){
       return UserDao.getUser();
    }

    public LiveData<List<Question>> getQuestion(String level,String type){
        return PoemNetWork.getQuestion(type,level);
    }

    public LiveData<List<Poem>> getCollection(String username){
        return PoemNetWork.getCollectionList(username);
    }

    public LiveData<List<Question>> getMistakesList(String username){
        return PoemNetWork.getMistakesList(username);
    }

    public LiveData<UserInfo> changePassword(String userName,String oldPassword,String newPassword){
        return PoemNetWork.changePassword(userName,oldPassword,newPassword);
    }

    public LiveData<String> uploadPortrait(String userName,String filePath){
        return PoemNetWork.uploadPortrait(userName,filePath);
    }

    public LiveData<Integer> collect(String pid,String userName){
            return PoemNetWork.collect(pid,userName);
    }

    public LiveData<Integer> collectDelete(String pid,String userName){
        return PoemNetWork.deleteCollection(pid, userName);
    }

}
