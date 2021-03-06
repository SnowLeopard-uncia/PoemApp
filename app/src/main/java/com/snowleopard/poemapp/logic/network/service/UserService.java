package com.snowleopard.poemapp.logic.network.service;

import com.snowleopard.poemapp.logic.model.BaseResponse;
import com.snowleopard.poemapp.logic.model.UserInfo;
import com.snowleopard.poemapp.logic.model.User;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Query;

//前面的GET、POST、DELETE、PUT，是请求方法，加上注解之后，Retrofit会用这些方法请求（注解的原理要看）
//这里只需要传入请求地址的相对路径，因为前面初始化会初始化base url
//方法的返回值必须声明成Retrofit中内置的Call类型
// 并通过泛型来指定[服务器响应的数据]应该转换成什么对象。
// 由于服务器响应的是一个包含App数据的JSON数组
// 因此这里我们将泛型声明成List<App>。 Call内的是服务器返回的数据

public interface UserService {

//@Query注解对它们进行声明,当发起网络请求的时候，Retrofit就会自动按照带参数GET请求的格式将这两个参数构建到请求地址当中
    //查看用户个人信息
    @GET("/user/information")
    Call<BaseResponse<User>> getUserInfo(@Query("username") String userName);

    //用户登录

    @FormUrlEncoded  //用@Filed要搭配这个使用
    @POST("/user/login")
//    Call<BaseResponse<User>> userLogin(@Body UserLogin userLogin);
    Call<BaseResponse<UserInfo>> userLogin(@Field("password")String password, @Field("username")String username);

    //使用PUT请求来提交数据，需要将数据放到HTTP请求的body部分，这个功能在Retrofit中可以借助@Body注解来完成 多个数据封装成了一个类
    //声明了一个Data类型的参数，并给它加上了@Body注解。这样当Retrofit发出POST请求时，就会自动将Data对象中的数据转换成JSON格式的文本，
    // 并放到HTTP请求的body部分，服务器在收到请求之后只需要从body中将这部分数据解析出来即可【如果是两个参数怎么声明？】
    //使用ResponseBody，表示Retrofit能够接收任意类型的响应数据，并且不会对响应数据进行解析
    //用户修改密码
    @FormUrlEncoded
    @PUT("user/password")
    Call<BaseResponse<UserInfo>> changePassword(@Field("username") String userName,
                                            @Field("oldPassword") String oldPassword,
                                            @Field("newPassword") String newPassword
    );

    //用户注册接口
    @FormUrlEncoded
    @POST("user/register")
//    Call<BaseResponse<User>> userRegister(@Body String userName,String password);
    Call<BaseResponse<UserInfo>> userRegister(@Field("password")String password, @Field("username")String username);

    //用户更改用户名 后端接口有问题
    @PUT("user/username")
    Call<BaseResponse<User>> changeUserName(@Body String userName);

        //查看用户头像
    @GET("user/protrait")
    Call<BaseResponse<String>> getUserProtraits(@Query("username") String userName);
//

    //上传单张照片 头像
    @Multipart  //注解表示可以多个@Part
    @POST("user/protrait")
    Call<BaseResponse<String>> postPortrait(@Part("username") RequestBody userName, @Part MultipartBody.Part file);
//RequestBody 表示传递简单键值对， MultipartBody.Part表示文件
}
