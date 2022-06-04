package com.snowleopard.poemapp.logic.network.service;

import com.snowleopard.poemapp.logic.model.BaseResponse;
import com.snowleopard.poemapp.logic.model.Question;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface QuestionService {
    /**
     * 请求测试的问题
     * @param level 问题等级
     * @param type 问题类型
     * @return
     */

    @GET("/questions/learn")  //原来bug是这里question少了个s 无大语😶
    Call<BaseResponse<List<Question>>> getQuestion(@Query("level") String level, @Query("type") String type);
}
