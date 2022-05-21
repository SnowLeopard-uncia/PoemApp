package com.snowleopard.poemapp.logic.network;

import com.snowleopard.poemapp.logic.model.BaseResponse;
import com.snowleopard.poemapp.logic.model.Poem;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface CollectionService {

    //用户添加收藏
    @POST("collection/addition")
    Call<BaseResponse<Integer>> addCollection();

    //用户取消收藏
    @DELETE("collection/deletion")
    Call<BaseResponse<Integer>> deleteCollection();

    //查看收藏夹
    @GET("collection/poems")
    Call<BaseResponse<List<Poem>>> getCollection();
}
