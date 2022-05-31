package com.snowleopard.poemapp.ui;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import com.snowleopard.poemapp.BaseActivity;
import com.snowleopard.poemapp.R;
import com.snowleopard.poemapp.databinding.ActivityPoamBinding;
import com.snowleopard.poemapp.logic.model.Poem;

public class PoemActivity extends BaseActivity {

    ActivityPoamBinding poamBinding;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        poamBinding= DataBindingUtil.setContentView(this,R.layout.activity_poam);

        Intent intent = getIntent();
         Poem poem= (Poem) intent.getSerializableExtra("poem_data");
         poamBinding.setPoem(poem);
    }
}