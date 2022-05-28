package com.snowleopard.poemapp.logic.network;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.snowleopard.poemapp.logic.model.BaseResponse;
import com.snowleopard.poemapp.logic.model.User;
import com.snowleopard.poemapp.logic.model.UserLogin;
import com.snowleopard.poemapp.logic.network.service.PoemService;
import com.snowleopard.poemapp.logic.network.service.UserService;
import com.snowleopard.poemapp.utils.RetrofitCallback;

import retrofit2.Call;
import retrofit2.Response;

public class PoemNetWork {

   static   MutableLiveData<User> userLiveData= new MutableLiveData<>();
    private static UserService userService = ServiceCreator.create(UserService.class);

    private static PoemService poemService = ServiceCreator.create(PoemService.class);

    public static LiveData<User> userLogin(UserLogin userLogin){

        userService.userLogin(userLogin.getUsername(),userLogin.getPassword()).enqueue(new RetrofitCallback<BaseResponse<User>>() {
            @Override
            public void onResponse(Call<BaseResponse<User>> call, Response<BaseResponse<User>> response) {
                User user= response.body() != null ? response.body().getData() : null;
                userLiveData.postValue(user);
            }
        });
        return userLiveData;
    }


}
