package com.snowleopard.poemapp.logic.network.service;

import com.snowleopard.poemapp.logic.model.BaseResponse;
import com.snowleopard.poemapp.logic.model.Poem;

import java.util.List;

import kotlin.jvm.JvmMultifileClass;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CollectionService {

    //用户添加收藏
    @FormUrlEncoded
    @POST("collection/addition")
    Call<BaseResponse<Integer>> addCollection(@Field("pId") String pid, @Field("username")String userName);

    //使用DELETE报错 FormUrlEncoded can only be specified on HTTP methods with request body (e.g., @POST).
//    这是什么
    //用户取消收藏
//    @FormUrlEncoded
    @DELETE("collection/deletion")
//    这样写不行
//    @Headers("Content-Type:application/x-www-form-urlencoded")
//    @HTTP(method = "DELETE", path = "collection/deletion?{pid}&{username}", hasBody = true)

    Call<BaseResponse<Integer>> deleteCollection(@Query("pId") String pid, @Query("username") String userName);

    //查看收藏夹

    @GET("collection/poems")
    Call<BaseResponse<List<Poem>>> getCollection(@Query("username") String userName);
}
