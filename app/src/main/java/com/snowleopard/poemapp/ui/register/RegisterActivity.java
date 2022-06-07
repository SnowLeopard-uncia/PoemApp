package com.snowleopard.poemapp.ui.register;

import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.snowleopard.poemapp.databinding.ActivityRegisterBinding;
import com.snowleopard.poemapp.ui.main.BaseActivity;
import com.snowleopard.poemapp.ui.main.MainActivity;
import com.snowleopard.poemapp.R;
import com.snowleopard.poemapp.logic.model.User;
import com.snowleopard.poemapp.logic.model.UserInfo;

public class RegisterActivity extends BaseActivity {

    private ActivityRegisterBinding registerBinding;
    RegisterViewModel registerViewModel;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(getColor(R.color.white));
        registerBinding= DataBindingUtil.setContentView(this,R.layout.activity_register);

        registerViewModel= new ViewModelProvider(this,(ViewModelProvider.Factory) new ViewModelProvider.
                AndroidViewModelFactory(getApplication())).get(RegisterViewModel.class);

        registerBinding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String userName= registerBinding.inputRegUserName.getInputStr();
               String password=registerBinding.inputRegPassword.getInputStr();
               String confirmPassword=registerBinding.inputRegConfirmPassword.getInputStr();


               if (!password.equals(confirmPassword)){
                   Toast.makeText(RegisterActivity.this,"两次密码不一致",Toast.LENGTH_SHORT).show();
               }else {
                   User user = new User(password,userName);
                   registerViewModel.userRegister(user).observe(RegisterActivity.this, new Observer<UserInfo>() {
                       @Override
                       public void onChanged(UserInfo userInfo) {
                           registerViewModel.saveUser(userInfo);
                           Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                           startActivity(intent);
                           finish();
                       }
                   });
               }

            }
        });
    }
}