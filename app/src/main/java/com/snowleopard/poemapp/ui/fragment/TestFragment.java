package com.snowleopard.poemapp.ui.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.snowleopard.poemapp.R;
import com.snowleopard.poemapp.databinding.FragmentPersonalBinding;
import com.snowleopard.poemapp.databinding.FragmentTestBinding;
import com.snowleopard.poemapp.ui.test.TestActivity;

public class TestFragment extends Fragment {

    FragmentTestBinding fragmentTestBinding;
    String TYPE = "";
    String LEVEL = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentTestBinding = FragmentTestBinding.inflate(inflater);

        fragmentTestBinding.rgTestChooseLevel.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb_test_primary:
                        LEVEL="0";
                        break;
                    case R.id.rb_test_middle:
                        LEVEL="1";
                        break;
                    case R.id.rb_test_high:
                        LEVEL="2";
                        break;
                }
            }
        });

        fragmentTestBinding.rgTestChooseType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId){
                    case R.id.rb_test_choose:
                        TYPE="0";
                        break;
                    case R.id.rb_test_check:
                        TYPE="1";
                        break;
                }
            }
        });

        fragmentTestBinding.btnStartTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TYPE.equals("") || LEVEL.equals("")){
                    Toast.makeText(getActivity(),"请选择等级和类型！",Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent = new Intent(getActivity(), TestActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("level",LEVEL);
                    bundle.putString("type",TYPE);
                    intent.putExtra("bundle",bundle);
                    startActivity(intent);
                }
            }
        });


        return fragmentTestBinding.getRoot();
    }
}