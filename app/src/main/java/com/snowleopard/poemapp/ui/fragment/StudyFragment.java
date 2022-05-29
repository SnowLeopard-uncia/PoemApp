package com.snowleopard.poemapp.ui.fragment;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.snowleopard.poemapp.MainViewModel;
import com.snowleopard.poemapp.MyApplication;
import com.snowleopard.poemapp.PagerAdapter;
import com.snowleopard.poemapp.R;
import com.snowleopard.poemapp.databinding.FragmentStudyBinding;


public class StudyFragment extends Fragment {

    FragmentStudyBinding fragmentStudyBinding;

    private Activity activity;

    public StudyFragment() {

    }
//用于解决getActivity返回null的方法：
//    原因：调用了getActivity()时，当前的Fragment已经onDetach()了宿主Activity
//    在pop了Fragment之后，该Fragment的异步任务仍然在执行，并且在执行完成后调用了getActivity()方法，这样就会空指针。
//在Fragment基类里设置一个Activity mActivity的全局变量，在onAttach(Activity activity)里赋值，使用mActivity代替getActivity()，保证Fragment即使在onDetach后，仍持有Activity的引用（有引起内存泄露的风险，但是相比空指针闪退，这种做法“安全”些）
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.activity=(Activity) context;
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentStudyBinding=FragmentStudyBinding.inflate(inflater); //fragment使用databinding
//        View view = inflater.inflate(R.layout.fragment_study, container, false);
        PagerAdapter pagerAdapter = new PagerAdapter((FragmentActivity) activity);
        //这里出现过空指针异常，因为Activity找不到
        //Return the FragmentActivity this fragment is currently associated with. May return null if the fragment is associated with a Context instead.
//        如果此fragment绑定的是一个context的话，可能会返回null
//        fragmentStudyBinding= DataBindingUtil.setContentView(activity,R.layout.fragment_study);
        //然而是databinding为null,没有绑定 怀疑是这个语句把navigation导航栏覆盖掉↑ 所以查了一下fragment使用databinding,结果还真是
        fragmentStudyBinding.vp2Study.setAdapter(pagerAdapter);
        pagerAdapter.addFragment(new PrimaryFragment());
        pagerAdapter.addFragment(new MiddleFragment());
        pagerAdapter.addFragment(new HighFragment());

//        fragmentStudyBinding.tbStudy.setTabTextColors(R.color.dark_blue,R.color.gray_blue);
        fragmentStudyBinding.tbStudy.addTab(fragmentStudyBinding.tbStudy.newTab().setText("小学"));
        fragmentStudyBinding.tbStudy.addTab(fragmentStudyBinding.tbStudy.newTab().setText("初中"));
        fragmentStudyBinding.tbStudy.addTab(fragmentStudyBinding.tbStudy.newTab().setText("高中"));
//fragment初始化的界面
        fragmentStudyBinding.vp2Study.setCurrentItem(0);

        fragmentStudyBinding.tbStudy.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                fragmentStudyBinding.vp2Study.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        //fragment与view pager绑定
        fragmentStudyBinding.vp2Study.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                fragmentStudyBinding.tbStudy.setScrollPosition(position, 0, false);
            }
        });


        return fragmentStudyBinding.getRoot();
    }
}