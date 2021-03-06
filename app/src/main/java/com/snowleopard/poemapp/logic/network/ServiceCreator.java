package com.snowleopard.poemapp.logic.network;

import com.snowleopard.poemapp.utils.BaseInterceptor;
import com.snowleopard.poemapp.utils.CustomGsonConverterFactory;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Retrofit单例类
 * @param <T>
 */
public class ServiceCreator<T> {
    //电脑连接手机热点  但是
//    private static final String BASE_URL="http://192.168.43.174:8080";
    //手机连接电脑热点  这个不关闭防火墙也能用
    private static final String BASE_URL="http://192.168.137.1:8080";
//电脑连接有线的 这个要关闭防火墙才能用
// 头像的URL返回的是电脑的ipconfig的IPV4 的地址  但是也可以用137的那个地址 也不关防火墙能用，感觉不关防火墙的前提是我手机在共享电脑网络
//    private static final String BASE_URL="http://192.168.180.171:8080";

//   static OkHttpClient.Builder builder = new OkHttpClient().newBuilder().addInterceptor(new BaseInterceptor());
//
//   static OkHttpClient client = builder.build();

    private static Retrofit retrofit = new Retrofit.Builder()  //使用Retrofit.Builder构建一个Retrofit对象
          .baseUrl(BASE_URL)  //baseUrl()方法用于指定所有Retrofit请求的根路径
          .addConverterFactory(CustomGsonConverterFactory.create())  //addConverterFactory()方法用于指定Retrofit在解析数据时所使用的转换库
//          .client(client)
            .build();
//提供了一个外部可见的create()方法，并接收一个Class类型的参数。当在外部调用这个方法时，实际上就是调用了Retrofit对象的create()方法，从而创建出相应Service接口的动态代理对象
    public static <T>T create(Class<T> serviceClass){
      return retrofit.create(serviceClass);
  }

    //关于静态方法里面泛型的原理需要研究,就是<T> T
}
