package com.snowleopard.poemapp.ui;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;

import com.snowleopard.poemapp.BaseActivity;
import com.snowleopard.poemapp.R;

public class ComplexProblemActivity extends BaseActivity {

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complex_problem);
    }
}