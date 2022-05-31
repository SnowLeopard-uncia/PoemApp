package com.snowleopard.poemapp.logic.network.service;

import com.snowleopard.poemapp.logic.model.BaseResponse;
import com.snowleopard.poemapp.logic.model.Poem;

import java.util.List;

import kotlin.jvm.JvmMultifileClass;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface CollectionService {

    //用户添加收藏
    @POST("collection/addition")
    Call<BaseResponse<Integer>> addCollection(@Field("pid") String pid, @Field("username")String userName);

    //用户取消收藏
    @DELETE("collection/deletion")
    Call<BaseResponse<Integer>> deleteCollection(@Field("pid") String pid, @Field("username")String userName);

    //查看收藏夹
    @GET("collection/poems")
    Call<BaseResponse<List<Poem>>> getCollection(@Query("username") String userName);
}
