package com.snowleopard.poemapp.ui.dialog;

import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.snowleopard.poemapp.databinding.ActivityDialogBinding;
import com.snowleopard.poemapp.ui.main.BaseActivity;
import com.snowleopard.poemapp.R;
import com.snowleopard.poemapp.logic.model.Dialog;
import com.snowleopard.poemapp.logic.model.PoemDialog;

import java.util.List;

public class DialogActivity extends BaseActivity {

    ActivityDialogBinding dialogBinding;
    DialogViewModel dialogViewModel;
    int level;
    String content;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        level = intent.getIntExtra("level", 0);
        dialogBinding = DataBindingUtil.setContentView(this, R.layout.activity_dialog);
        dialogViewModel = new ViewModelProvider(this).get(DialogViewModel.class);

        Log.e("TAG", "onCreate: " + "重走了一次");
        dialogBinding.rvDialog.setLayoutManager(new LinearLayoutManager(this));
        dialogBinding.rvDialog.setAdapter(dialogViewModel.getAdapter());

        dialogBinding.ivBackDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        //清除
//        dialogViewModel.getDialogList().clear();
//
//        dialogViewModel.setI(0);

        dialogBinding.btnReply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                content = dialogBinding.etReply.getText().toString();
                if (!"".equals(content)) {
                    Dialog dialog = new Dialog(content, Dialog.TYPE_ANSWER);
                    dialogViewModel.getDialogList().add(dialog);

                    int pos = dialogViewModel.getDialogList().size() - 1;
                    dialogViewModel.getAdapter().notifyItemInserted(pos);
                    dialogBinding.rvDialog.scrollToPosition(pos);
                    //recyclerview滚动最后一行
                    dialogBinding.etReply.setText(""); //清空输入框内容

                    String answer = dialogViewModel.getPoemDialog().getAnswer();
                    if (answer != null && dialogViewModel.getI() <= dialogViewModel.getPoemList().size()) {
                        Dialog ask;
                        if (content.equals(answer)) {
                            ask = new Dialog("回答正确！", Dialog.TYPE_ASK);
//                        Toast.makeText(DialogActivity.this,"正确",Toast.LENGTH_SHORT).show();
                        } else {
                            ask = new Dialog("不对，答案是：" + answer, Dialog.TYPE_ASK);
//                        Toast.makeText(DialogActivity.this,"错误",Toast.LENGTH_SHORT).show();
                        }
                        dialogViewModel.getDialogList().add(ask);
                        pos = dialogViewModel.getDialogList().size() - 1;
                        dialogViewModel.getAdapter().notifyItemInserted(pos);     //adapter
                        dialogBinding.rvDialog.scrollToPosition(pos);
                        //recyclerview滚动最后一行

                    }
                    int i = dialogViewModel.getI() + 1;
                    dialogViewModel.poemDialogMutableLiveData.postValue(dialogViewModel.getI());
                    dialogViewModel.setI(i);

                } else {
                    Toast.makeText(DialogActivity.this, "输入不能为空", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //旋转屏幕的时候这里会重复观察
        dialogViewModel.poemDialogMutableLiveData.observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                if (integer >= dialogViewModel.getPoemList().size()) {
                    dialogViewModel.getDialogList().add(new Dialog("全部答完啦", Dialog.TYPE_ASK));
                    int pos = dialogViewModel.getDialogList().size() - 1;
                    dialogViewModel.getAdapter().notifyItemInserted(pos);
                    dialogBinding.rvDialog.scrollToPosition(pos);
                } else {
                    PoemDialog poemDialog = dialogViewModel.getPoemList().get(integer);
                    //初始化
                    dialogViewModel.getDialogList().add(new Dialog(poemDialog.getAsk(), Dialog.TYPE_ASK));
                    dialogViewModel.setPoemDialog(poemDialog);
                    int pos = dialogViewModel.getDialogList().size() - 1;
                    dialogViewModel.getAdapter().notifyItemInserted(pos);
                    dialogBinding.rvDialog.scrollToPosition(pos);
                    //recyclerview滚动最后一行
                }

            }
        });

//        dialogViewModel.getPoem().observe(DialogActivity.this, new Observer<PoemDialog>() {
//            @Override
//            public void onChanged(PoemDialog poemDialog) {
////就是getPoem这个方法确实只执行了一次，但是onChanged了三次 而且Activity都销毁了一次，还存着上一次Activity的Data
//                dialogViewModel.setPoemDialog(poemDialog);
//            }
//        });


        /**
         * 获取题目列表  list =0，报错
         */
        dialogViewModel.getDialogByLevel(level).observe(this, new Observer<List<PoemDialog>>() {
            @Override
            public void onChanged(List<PoemDialog> poemDialogs) {
                dialogViewModel.setPoemList(poemDialogs);
            }
        });

//        dialogViewModel.getPoem().observe(DialogActivity.this, new Observer<PoemDialog>() {
//            @Override
//            public void onChanged(PoemDialog poemDialog) {
//                dialogViewModel.setPoemDialog(poemDialog);
//                dialogViewModel.getDialogList().add(new Dialog(poemDialog.getAsk(),Dialog.TYPE_ASK));
//                int pos=dialogViewModel.getDialogList().size()-1;
//                adapter.notifyItemInserted(pos);
//                dialogBinding.rvDialog.scrollToPosition(pos);
//                //recyclerview滚动最后一行
//            }
//        });


    }

}