package com.snowleopard.poemapp.ui.main.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.snowleopard.poemapp.databinding.FragmentPersonalBinding;
import com.snowleopard.poemapp.ui.main.MainViewModel;
import com.snowleopard.poemapp.R;
import com.snowleopard.poemapp.logic.model.UserInfo;
import com.snowleopard.poemapp.ui.changeinfo.ChangeInfoActivity;
import com.snowleopard.poemapp.ui.collection.CollectionActivity;
import com.snowleopard.poemapp.ui.mistakes.ComplexProblemActivity;

public class PersonalFragment extends Fragment {

    FragmentPersonalBinding personalBinding;
    MainViewModel mainViewModel;
    public PersonalFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.e("TAG", "onCreateView: " );
        personalBinding=FragmentPersonalBinding.inflate(inflater);

        mainViewModel=new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        UserInfo userInfo = mainViewModel.getUser();
//        personalBinding.tvName.setText(userName);  //用这个是显示不出来的，经典DataBinding覆盖问题
//        personalBinding.tvUserName.setText(userName);
        personalBinding.setTvUsernames(userInfo.getUsername());  //嗯就是被这个覆盖

        Log.e("TAG", "onCreateView: "+userInfo.getProPath());
 //初始化
        //关掉电脑防火墙才能加载 不然会Failed to load resource，
        Glide.with(requireActivity())
                .load(userInfo.getProPath())
                .apply(RequestOptions.bitmapTransform(new CircleCrop())) //使用Glide展示圆形图片
//                .skipMemoryCache(true) //不使用缓存
//                .diskCacheStrategy(DiskCacheStrategy.NONE) //不使用磁盘缓存  是用来试试看换头像那里的更新 并不能
                .error(R.drawable.error)
                .into(personalBinding.ivHead);

        personalBinding.llCollection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CollectionActivity.class);
                startActivity(intent);
            }
        });

        personalBinding.llMistakes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ComplexProblemActivity.class);
                startActivity(intent);
            }
        });

        personalBinding.llChangeInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ChangeInfoActivity.class);
                startActivity(intent);
            }
        });

        return personalBinding.getRoot();
    }


//    下面是关于Fragment生命周期的实验
    @Override
    public void onResume() {
        super.onResume();
        //这个的话，是用来返回的时候更改头像
        Log.e("TAG", "onResume: " );
        UserInfo userInfo = mainViewModel.getUser();
        Glide.with(requireActivity())
                .load(userInfo.getProPath())
                .apply(RequestOptions.bitmapTransform(new CircleCrop())) //使用Glide展示圆形图片
//                .skipMemoryCache(true) //不使用缓存
//                .diskCacheStrategy(DiskCacheStrategy.NONE) //不使用磁盘缓存  是用来试试看换头像那里的更新 并不能
                .error(R.drawable.error)
                .into(personalBinding.ivHead);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.e("TAG", "onActivityCreated: ");

    }

    @Override
    public void onStart() {
        super.onStart();
        Log.e("TAG", "onStart: " );

    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("TAG", "onPause: " );
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.e("TAG", "onStop: " );
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e("TAG", "onDestroyView: ");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.e("TAG", "onDestroy: " );
    }
    /**
     * 实验结果
     * 点击Fragment之后，分别启动了onCreateView，onActivityCreated，onStart，然后才onResume
     * I/HwViewRootImpl: removeInvalidNode all the node in jank list is out of time
     * D/HwGalleryCacheManagerImpl: mIsEffect:false
     * E/TAG: onResume:
     *
     * 点击进入新Activity之后，执行E/TAG: onPause: ，然后执行一堆东西之后执行onStop
     * 返回到Fragment的时候，执行onStart，onResume
     *
     * navigation切换其他Fragment之后，onPause，onStop，然后执行一些，之后onDestroyView，onDestroy，哇被销毁了
     */
}