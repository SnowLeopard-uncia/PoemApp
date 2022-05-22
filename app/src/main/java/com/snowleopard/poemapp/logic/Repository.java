package com.snowleopard.poemapp.logic;

import androidx.lifecycle.LiveData;

import com.snowleopard.poemapp.logic.network.PoemNetWork;

/**
 * 一般在仓库层中定义的方法，为了能将异步获取的数据以响应式编程的方式通知给上一层，通常会返回一个LiveData对象。
 */
public class Repository {

    private void userLogin(String userName,String password){
        PoemNetWork.userLogin(userName,password);
    }

}
