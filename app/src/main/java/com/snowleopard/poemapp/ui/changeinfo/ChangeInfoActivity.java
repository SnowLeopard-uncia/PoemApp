package com.snowleopard.poemapp.ui.changeinfo;

import static android.content.ContentValues.TAG;

import static androidx.core.content.PermissionChecker.PERMISSION_GRANTED;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.snowleopard.poemapp.databinding.ActivityChangeInfoBinding;
import com.snowleopard.poemapp.ui.main.BaseActivity;
import com.snowleopard.poemapp.R;
import com.snowleopard.poemapp.logic.model.UserInfo;
import com.snowleopard.poemapp.utils.FileChooseUtil;

/**
 * 修改用户名的后端接口有问题，所以暂时没接
 */
public class ChangeInfoActivity extends BaseActivity {

    ActivityChangeInfoBinding changeInfoBinding;
    ChangeInfoViewModel changeInfoViewModel;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        changeInfoBinding = DataBindingUtil.setContentView(this, R.layout.activity_change_info);
        changeInfoViewModel = new ViewModelProvider(this).get(ChangeInfoViewModel.class);
        changeInfoBinding.setUserName(changeInfoViewModel.getUser().getUsername());

        changeInfoBinding.btnChangePwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String oldPassword = changeInfoBinding.etChangeOldPwd.getText().toString();
                String newPassword = changeInfoBinding.etChangeNewPwd.getText().toString();

                changeInfoViewModel.changePassword(changeInfoViewModel.getUser().getUsername(),
                        oldPassword, newPassword).observe(ChangeInfoActivity.this, new Observer<UserInfo>() {
                    @Override
                    public void onChanged(UserInfo userInfo) {
                        if (userInfo != null) {
                            Toast.makeText(ChangeInfoActivity.this, "修改成功!", Toast.LENGTH_SHORT).show();
                            changeInfoBinding.etChangeNewPwd.setText("");
                            changeInfoBinding.etChangeOldPwd.setText("");
                        }
                    }
                });

            }
        });

        changeInfoBinding.btnChangeHead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkPermission();
            }
        });

    }

    //权限检查
    public void checkPermission() {
        String[] permissions = {  //读写权限请求
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE};
        ActivityCompat.requestPermissions(ChangeInfoActivity.this, permissions, 100); //如果是获取不到权限列表里的权限，返回了100

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 100:
                boolean writeExternalStorage = grantResults[0] == PERMISSION_GRANTED;
                boolean readExternalStorage = grantResults[1] == PERMISSION_GRANTED;
                if (grantResults.length > 0 && writeExternalStorage && readExternalStorage) {
                    Log.e("", "grantResults:" + grantResults);
                    choosePhoto();
                } else {
                    Toast.makeText(ChangeInfoActivity.this, "请授予权限", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void choosePhoto() {
        //这里就是权限打开之后自己要操作的逻辑
        //打开文件选择器
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        //筛选文件类型，这里筛选的图片类型
        intent.setType("image/*");
        activityResultLauncher.launch(intent);

    }

    private ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == Activity.RESULT_OK) {
                        //选择文件后返回
                        String filePath = "";
                        Intent data = result.getData();
                        assert data != null;
                        Uri uri = data.getData();
                        //getApplicationContext() 用了这个contetx
                        filePath = FileChooseUtil.getInstance(getApplicationContext()).getChooseFileResultPath(uri);
                        Log.d(TAG, "选择文件返回：" + filePath);

                        changeInfoViewModel.uploadPortrait(changeInfoViewModel.getUser().getUsername(), filePath).observe(ChangeInfoActivity.this, new Observer<String>() {
                            @Override
                            public void onChanged(String s) {
                                if (s != null) {
                                    Toast.makeText(ChangeInfoActivity.this, "更换成功！", Toast.LENGTH_SHORT).show();
                                    changeInfoViewModel.savePortrait(s);
                                }

                            }
                        });
                    }
                }
            }
    );

}