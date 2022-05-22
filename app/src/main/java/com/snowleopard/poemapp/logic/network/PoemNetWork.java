package com.snowleopard.poemapp.logic.network;

import com.snowleopard.poemapp.logic.model.BaseResponse;
import com.snowleopard.poemapp.utils.RetrofitCallback;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PoemNetWork {
    private static UserService userService = ServiceCreator.create(UserService.class);

    private static PoemService poemService = ServiceCreator.create(PoemService.class);

    public static void userLogin(String userName,String password){
        userService.userLogin(userName,password).enqueue(new RetrofitCallback<BaseResponse<Integer>>() {
            @Override
            public void onResponse(Call<BaseResponse<Integer>> call, Response<BaseResponse<Integer>> response) {

            }

        });
    }


}
