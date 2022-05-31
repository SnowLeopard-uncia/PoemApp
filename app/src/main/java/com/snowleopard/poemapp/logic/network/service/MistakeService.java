package com.snowleopard.poemapp.logic.network.service;

import com.snowleopard.poemapp.logic.model.BaseResponse;
import com.snowleopard.poemapp.logic.model.Question;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface MistakeService {

    //添加错题
    @POST("/mistakes/addition")
    Call<BaseResponse<Integer>> addQuestion(@Field("qId") String username, String qId);

    //查看错题
    @GET("/mistakes/questions")
    Call<BaseResponse<List<Question>>> getQuestion(@Query("username") String userName);
}
