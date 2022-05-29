package com.snowleopard.poemapp.ui.login;

import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.snowleopard.poemapp.BaseActivity;
import com.snowleopard.poemapp.MainActivity;
import com.snowleopard.poemapp.R;
import com.snowleopard.poemapp.databinding.ActivityLoginBinding;
import com.snowleopard.poemapp.logic.model.UserInfo;
import com.snowleopard.poemapp.logic.model.User;
import com.snowleopard.poemapp.ui.register.RegisterActivity;

public class LoginActivity extends BaseActivity {

    private ActivityLoginBinding loginBinding;
    LoginViewModel loginViewModel;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(getColor(R.color.white));
        loginBinding= DataBindingUtil.setContentView(this,R.layout.activity_login);
        loginViewModel=  new ViewModelProvider(this, (ViewModelProvider.Factory) new ViewModelProvider.
                AndroidViewModelFactory(getApplication())).get(LoginViewModel.class);

        loginBinding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName=loginBinding.inputUsername.getInputStr();
                String password=loginBinding.inputPassword.getInputStr();
                if (userName.equals("") || password.equals("")){
                    Toast.makeText(LoginActivity.this,"请输入账号和密码",Toast.LENGTH_SHORT).show();
                }else {
                    User user = new User(userName,password);
                    loginViewModel.userLogin(user).observe(LoginActivity.this, new Observer<UserInfo>() {
                        @Override
                        public void onChanged(UserInfo userInfo) {
                            if (userInfo !=null){
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent);
                            }
                            finish();
                        }
                    });
                }
            }
        });


        loginBinding.btnToRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
}