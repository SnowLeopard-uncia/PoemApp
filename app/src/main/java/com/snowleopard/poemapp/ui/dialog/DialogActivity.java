package com.snowleopard.poemapp.ui.dialog;

import androidx.annotation.RequiresApi;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.hardware.lights.LightsManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.snowleopard.poemapp.BaseActivity;
import com.snowleopard.poemapp.R;
import com.snowleopard.poemapp.databinding.ActivityDialogBinding;
import com.snowleopard.poemapp.logic.model.Dialog;
import com.snowleopard.poemapp.logic.model.PoemDialog;
import com.snowleopard.poemapp.ui.adapter.DialogAdapter;

import java.util.ArrayList;
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
         level = intent.getIntExtra("level",0);
        dialogBinding= DataBindingUtil.setContentView(this,R.layout.activity_dialog);
        dialogViewModel=new ViewModelProvider(this).get(DialogViewModel.class);



        DialogAdapter adapter = new DialogAdapter(dialogViewModel.getDialogList());
        dialogBinding.rvDialog.setLayoutManager(new LinearLayoutManager(this));
        dialogBinding.rvDialog.setAdapter(adapter);

        //清除
        dialogViewModel.getDialogList().clear();

//        dialogViewModel.setI(0);

        dialogBinding.btnReply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 content=dialogBinding.etReply.getText().toString();
                if (!"".equals(content)){
                    Dialog dialog = new Dialog(content,Dialog.TYPE_ANSWER);
                    dialogViewModel.getDialogList().add(dialog);

                    int pos=dialogViewModel.getDialogList().size()-1;
                    adapter.notifyItemInserted(pos);
                    dialogBinding.rvDialog.scrollToPosition(pos);
                    //recyclerview滚动最后一行
                    dialogBinding.etReply.setText(""); //清空输入框内容



                    if (content.equals(dialogViewModel.getPoemDialog().getAnswer())){

                        Toast.makeText(DialogActivity.this,"正确",Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(DialogActivity.this,"错误",Toast.LENGTH_SHORT).show();
                    }
//                   dialog=new Dialog(poemDialog.getAsk(),Dialog.TYPE_ASK);
//                    dialogViewModel.getDialogList().add(dialog);
                    //adapter
                    dialogViewModel.getDialogList().add(new Dialog(dialogViewModel.getPoemDialog().getAsk(),Dialog.TYPE_ASK));
                    int pos0=dialogViewModel.getDialogList().size()-1;
                    adapter.notifyItemInserted(pos0);
                    dialogBinding.rvDialog.scrollToPosition(pos0);
                    //recyclerview滚动最后一行
//                            dialogViewModel.setI(dialogViewModel.getI()+1);
                    dialogViewModel.setI(dialogViewModel.getI()+1);

                }else {
                    Toast.makeText(DialogActivity.this,"输入不能为空",Toast.LENGTH_SHORT).show();
                }
            }
        });

        dialogViewModel.getPoem().observe(DialogActivity.this, new Observer<PoemDialog>() {
            @Override
            public void onChanged(PoemDialog poemDialog) {
//就是getPoem这个方法确实只执行了一次，但是onChanged了三次 而且Activity都销毁了一次，还存着上一次Activity的Data
                dialogViewModel.setPoemDialog(poemDialog);

            }
        });


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