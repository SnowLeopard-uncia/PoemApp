package com.snowleopard.poemapp.ui;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.snowleopard.poemapp.BaseActivity;
import com.snowleopard.poemapp.DialogViewModel;
import com.snowleopard.poemapp.MainViewModel;
import com.snowleopard.poemapp.R;
import com.snowleopard.poemapp.databinding.ActivityDialogBinding;
import com.snowleopard.poemapp.databinding.ActivityLoginBinding;
import com.snowleopard.poemapp.logic.model.Dialog;
import com.snowleopard.poemapp.ui.adapter.DialogAdapter;

import java.util.ArrayList;
import java.util.List;

public class DialogActivity extends BaseActivity {

    ActivityDialogBinding dialogBinding;
    DialogViewModel dialogViewModel;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dialogBinding= DataBindingUtil.setContentView(this,R.layout.activity_dialog);
        dialogViewModel=new ViewModelProvider(this).get(DialogViewModel.class);

        DialogAdapter adapter = new DialogAdapter(dialogViewModel.getDialogList());
        dialogBinding.rvDialog.setLayoutManager(new LinearLayoutManager(this));
        dialogBinding.rvDialog.setAdapter(adapter);

        dialogBinding.btnReply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String content=dialogBinding.etReply.getText().toString();
                if (!"".equals(content)){
                    Dialog dialog = new Dialog(content,Dialog.TYPE_ANSWER);
                    dialogViewModel.getDialogList().add(dialog);
                    //adapter
                    int pos=dialogViewModel.getDialogList().size()-1;
                    adapter.notifyItemInserted(pos);
                    dialogBinding.rvDialog.scrollToPosition(pos);
                    //recyclerview滚动最后一行
                    dialogBinding.etReply.setText(""); //清空输入框内容
                }
            }
        });
    }
}