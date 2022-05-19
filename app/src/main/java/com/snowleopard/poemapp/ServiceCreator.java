package com.snowleopard.poemapp;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceCreator<T> {
    private static String BASE_URL="http://localhost:8080";

  Retrofit retrofit = new Retrofit.Builder()  //使用Retrofit.Builder构建一个Retrofit对象
          .baseUrl(BASE_URL)  //baseUrl()方法用于指定所有Retrofit请求的根路径
          .addConverterFactory(GsonConverterFactory.create())  //addConverterFactory()方法用于指定Retrofit在解析数据时所使用的转换库
          .build();
//提供了一个外部可见的create()方法，并接收一个Class类型的参数。当在外部调用这个方法时，实际上就是调用了Retrofit对象的create()方法，从而创建出相应Service接口的动态代理对象
  public T create(Class<T> serviceClass){
      return retrofit.create(serviceClass);
  }

}
