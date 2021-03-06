package com.snowleopard.poemapp.logic.network;

import android.widget.ListPopupWindow;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.snowleopard.poemapp.logic.model.BaseResponse;
import com.snowleopard.poemapp.logic.model.Poem;
import com.snowleopard.poemapp.logic.model.Question;
import com.snowleopard.poemapp.logic.model.UserInfo;
import com.snowleopard.poemapp.logic.model.User;
import com.snowleopard.poemapp.logic.network.service.CollectionService;
import com.snowleopard.poemapp.logic.network.service.MistakeService;
import com.snowleopard.poemapp.logic.network.service.PoemService;
import com.snowleopard.poemapp.logic.network.service.QuestionService;
import com.snowleopard.poemapp.logic.network.service.UserService;
import com.snowleopard.poemapp.utils.FileChooseUtil;
import com.snowleopard.poemapp.utils.RetrofitCallback;

import java.io.File;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Multipart;

public class PoemNetWork {

   static   MutableLiveData<UserInfo> loginData = new MutableLiveData<>();

    static   MutableLiveData<UserInfo> registerData= new MutableLiveData<>();

    static MutableLiveData<List<Poem>> poemData = new MutableLiveData<>();

    static MutableLiveData<List<Poem>> primaryPoemData = new MutableLiveData<>();

    static MutableLiveData<List<Poem>> middlePoemData = new MutableLiveData<>();

    static MutableLiveData<List<Poem>> highPoemData = new MutableLiveData<>();

    static MutableLiveData<Integer> isCollect = new MutableLiveData<>();

    static MutableLiveData<String> userHead = new MutableLiveData<>();

    static MutableLiveData<List<Poem>> collectionList = new MutableLiveData<>();

    static MutableLiveData<List<Question>> questionList = new MutableLiveData<>();

    static MutableLiveData<List<Question>> mistakesList = new MutableLiveData<>();

    static MutableLiveData<UserInfo> changePasswordData = new MutableLiveData<>();

    static MutableLiveData<String> uploadPortraitData = new MutableLiveData<>();

    static MutableLiveData<Integer> isCollectDelete = new MutableLiveData<>();

    private static UserService userService = ServiceCreator.create(UserService.class);

    private static PoemService poemService = ServiceCreator.create(PoemService.class);

    private static QuestionService questionService = ServiceCreator.create(QuestionService.class);

    private static CollectionService collectionService = ServiceCreator.create(CollectionService.class);

    private static MistakeService mistakeService = ServiceCreator.create(MistakeService.class);


    public static LiveData<UserInfo> userLogin(User user){
        userService.userLogin(user.getUsername(), user.getPassword()).enqueue(new RetrofitCallback<BaseResponse<UserInfo>>() {
            @Override
            public void onResponse(Call<BaseResponse<UserInfo>> call, Response<BaseResponse<UserInfo>> response) {
                UserInfo userInfo = response.body() != null ? response.body().getData() : null;
                loginData.postValue(userInfo);
            }
        });
        return loginData;
    }

    public static LiveData<UserInfo>  userRegister(User user){
        userService.userRegister(user.getPassword(),user.getUsername()).enqueue(new RetrofitCallback<BaseResponse<UserInfo>>() {
            @Override
            public void onResponse(Call<BaseResponse<UserInfo>> call, Response<BaseResponse<UserInfo>> response) {
                UserInfo userInfo=response.body()!=null?response.body().getData():null;
                registerData.postValue(userInfo);
            }

        });
        return registerData;
    }

    public static LiveData<List<Poem>> getAllPoem(){
        poemService.getAllPoems().enqueue(new RetrofitCallback<BaseResponse<List<Poem>>>() {
            @Override
            public void onResponse(Call<BaseResponse<List<Poem>>> call, Response<BaseResponse<List<Poem>>> response) {
                List<Poem> poemList = response.body()!=null?response.body().getData():null;
                poemData.postValue(poemList);
            }
        });
        return poemData;
    }

    public static LiveData<List<Poem>> getPoemByLevelP(){
        poemService.getPoemByLevel("0").enqueue(new RetrofitCallback<BaseResponse<List<Poem>>>() {
            @Override
            public void onResponse(Call<BaseResponse<List<Poem>>> call, Response<BaseResponse<List<Poem>>> response) {
                List<Poem> poemList = response.body()!=null?response.body().getData():null;
                    primaryPoemData.postValue(poemList);
            }
        });
        return primaryPoemData;
    }
    public static LiveData<List<Poem>> getPoemByLevelM(){
        poemService.getPoemByLevel("1").enqueue(new RetrofitCallback<BaseResponse<List<Poem>>>() {
            @Override
            public void onResponse(Call<BaseResponse<List<Poem>>> call, Response<BaseResponse<List<Poem>>> response) {
                List<Poem> poemList = response.body()!=null?response.body().getData():null;
                middlePoemData.postValue(poemList);
            }
        });
        return middlePoemData;
    }
    public static LiveData<List<Poem>> getPoemByLevelH(){
        poemService.getPoemByLevel("2").enqueue(new RetrofitCallback<BaseResponse<List<Poem>>>() {
            @Override
            public void onResponse(Call<BaseResponse<List<Poem>>> call, Response<BaseResponse<List<Poem>>> response) {
                List<Poem> poemList = response.body()!=null?response.body().getData():null;
                highPoemData.postValue(poemList);
            }
        });
        return highPoemData;
    }

    public static LiveData<Integer> collect(String pid,String userName){
        collectionService.addCollection(pid,userName).enqueue(new RetrofitCallback<BaseResponse<Integer>>() {
            @Override
            public void onResponse(Call<BaseResponse<Integer>> call, Response<BaseResponse<Integer>> response) {
                Integer collect = response.body()!=null?response.body().getData():null;
                isCollect.postValue(collect);
            }
        });
        return isCollect;
    }

    public static LiveData<List<Question>> getQuestion(String type,String level){
        questionService.getQuestion(level,type).enqueue(new RetrofitCallback<BaseResponse<List<Question>>>() {
            @Override
            public void onResponse(Call<BaseResponse<List<Question>>> call, Response<BaseResponse<List<Question>>> response) {
                List<Question> list = response.body()!=null?response.body().getData():null;
                questionList.postValue(list);
            }
        });
        return questionList;
    }

    public static LiveData<List<Poem>> getCollectionList(String userName){
        collectionService.getCollection(userName).enqueue(new RetrofitCallback<BaseResponse<List<Poem>>>() {
            @Override
            public void onResponse(Call<BaseResponse<List<Poem>>> call, Response<BaseResponse<List<Poem>>> response) {
                List<Poem> list = response.body()!=null?response.body().getData():null;
                collectionList.postValue(list);
            }
        });
        return collectionList;
    }

    public static LiveData<List<Question>> getMistakesList(String userName){
        mistakeService.getMistakesList(userName).enqueue(new RetrofitCallback<BaseResponse<List<Question>>>() {
            @Override
            public void onResponse(Call<BaseResponse<List<Question>>> call, Response<BaseResponse<List<Question>>> response) {
                List<Question> list = response.body()!= null?response.body().getData():null;
                mistakesList.postValue(list);
            }
        });
        return mistakesList;
    }

    public static LiveData<UserInfo> changePassword(String userName,String oldPassword,String newPassword){
        userService.changePassword(userName,oldPassword,newPassword).enqueue(new RetrofitCallback<BaseResponse<UserInfo>>() {
            @Override
            public void onResponse(Call<BaseResponse<UserInfo>> call, Response<BaseResponse<UserInfo>> response) {
                UserInfo userInfo = response.body()!= null?response.body().getData():null;
                changePasswordData.postValue(userInfo);
            }
        });
        return changePasswordData;
    }

    public static LiveData<String> uploadPortrait(String userName,String filePath){
        File file = new File(filePath);
        RequestBody requestBody = RequestBody.create(MediaType.parse("image/jpg"),file);
        MultipartBody.Part photo = MultipartBody.Part.createFormData("file","photo.jpg",requestBody);
        userService.postPortrait(RequestBody.create(null,userName),photo).enqueue(new RetrofitCallback<BaseResponse<String>>() {
            @Override
            public void onResponse(Call<BaseResponse<String>> call, Response<BaseResponse<String>> response) {
                String url  = response.body()!=null?response.body().getData():null;
                uploadPortraitData.postValue(url);
            }
        });
        return uploadPortraitData;
    }

    public static LiveData<Integer> deleteCollection(String pid, String userName){
        collectionService.deleteCollection(pid,userName).enqueue(new RetrofitCallback<BaseResponse<Integer>>() {
            @Override
            public void onResponse(Call<BaseResponse<Integer>> call, Response<BaseResponse<Integer>> response) {
                Integer integer=response.body()!=null?response.body().getData():null;
                isCollectDelete.postValue(integer);
            }
        });
        return isCollectDelete;
    }

}
