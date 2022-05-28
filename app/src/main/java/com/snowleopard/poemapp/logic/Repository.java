package com.snowleopard.poemapp.logic;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


import com.snowleopard.poemapp.logic.model.User;
import com.snowleopard.poemapp.logic.model.UserLogin;
import com.snowleopard.poemapp.logic.network.PoemNetWork;

/**
 * 一般在仓库层中定义的方法，为了能将异步获取的数据以响应式编程的方式通知给上一层，通常会返回一个LiveData对象。
 */
public class Repository {

    public static Repository repository;

    public static Repository getInstance(){
        if (repository==null){
            repository=new Repository();
        }
        return repository;
    }
    private MutableLiveData<UserLogin> userLiveData=new MutableLiveData<>();



    public LiveData<User> userLogin(UserLogin userLogin){
       return PoemNetWork.userLogin(userLogin);
    }

}
