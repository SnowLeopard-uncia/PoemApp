package com.snowleopard.poemapp.ui.test;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.snowleopard.poemapp.BaseActivity;
import com.snowleopard.poemapp.R;
import com.snowleopard.poemapp.databinding.ActivityFinishTestBinding;
import com.snowleopard.poemapp.logic.model.CorrectMistakes;

public class FinishTestActivity extends BaseActivity {

    ActivityFinishTestBinding activityFinishTestBinding;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityFinishTestBinding = DataBindingUtil.setContentView(this,R.layout.activity_finish_test);

        Intent intent = getIntent();
        CorrectMistakes correctMistakes = (CorrectMistakes) intent.getSerializableExtra("rate_data");
        activityFinishTestBinding.setCorrectMistake(correctMistakes);

        activityFinishTestBinding.toMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}