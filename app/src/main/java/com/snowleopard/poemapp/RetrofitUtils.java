package com.snowleopard.poemapp;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtils {

    private static RetrofitUtils retrofitUtils;

    public RetrofitUtils() {
    }
    //这单例写得我PDST了
    public static RetrofitUtils getInstance(){

        if (retrofitUtils==null){
            synchronized (RetrofitUtils.class){
                if (retrofitUtils == null){
                    retrofitUtils=new RetrofitUtils();
                }
            }
        }
        return retrofitUtils;
    }
    //返回Retrofit
    public static Retrofit getRetrofit(String url){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }

    public <T> T getApiService(String url,Class<T> service){
        Retrofit retrofit = getRetrofit(url);
        //Java动态代理模式获取代理对象
        T t = retrofit.create(service);
        return t;
    }
}
