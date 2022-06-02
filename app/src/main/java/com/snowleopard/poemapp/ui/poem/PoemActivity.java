package com.snowleopard.poemapp.ui.poem;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.snowleopard.poemapp.BaseActivity;
import com.snowleopard.poemapp.R;
import com.snowleopard.poemapp.databinding.ActivityPoamBinding;
import com.snowleopard.poemapp.logic.model.Poem;
import com.snowleopard.poemapp.ui.login.LoginViewModel;

public class PoemActivity extends BaseActivity {

    ActivityPoamBinding poamBinding;

    PoemViewModel poemViewModel;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        poamBinding= DataBindingUtil.setContentView(this,R.layout.activity_poam);

        poemViewModel=  new ViewModelProvider(this, (ViewModelProvider.Factory) new ViewModelProvider.
                AndroidViewModelFactory(getApplication())).get(PoemViewModel.class);

        Intent intent = getIntent();
         Poem poem= (Poem) intent.getSerializableExtra("poem_data");
         poamBinding.setPoem(poem);

         poamBinding.ivCollect.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                String userName = poemViewModel.getUserName();

             }
         });
    }
}