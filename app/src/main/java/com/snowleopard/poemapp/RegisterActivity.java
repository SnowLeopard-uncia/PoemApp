package com.snowleopard.poemapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.snowleopard.poemapp.databinding.ActivityRegisterBinding;

public class RegisterActivity extends BaseActivity {

    private ActivityRegisterBinding registerBinding;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_register);
        getWindow().setStatusBarColor(getColor(R.color.main_blue));
        registerBinding= DataBindingUtil.setContentView(this,R.layout.activity_register);
        registerBinding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}