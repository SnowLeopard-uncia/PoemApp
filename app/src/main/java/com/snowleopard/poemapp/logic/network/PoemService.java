package com.snowleopard.poemapp.logic.network;

import com.snowleopard.poemapp.logic.model.BaseResponse;
import com.snowleopard.poemapp.logic.model.Poem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PoemService {

    //作者名诗词
//    @GET("poem/author")

//    难度搜诗词
    @GET("poem/level")
    Call<BaseResponse<List<Poem>>> getPoemByLevel();

    //查看所有诗词
    @GET("poem/poems")
    Call<BaseResponse<List<Poem>>> getAllPoems();

//    //每个诗词的音频  [接口有错误]
//    @GET("poem/record")
//    Call<>
}

