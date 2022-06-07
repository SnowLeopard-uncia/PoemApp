package com.snowleopard.poemapp.ui.poem;

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
import android.widget.CompoundButton;
import android.widget.Toast;

import com.snowleopard.poemapp.databinding.ActivityPoamBinding;
import com.snowleopard.poemapp.ui.adapter.CollectionAdapter;
import com.snowleopard.poemapp.ui.collection.CollectionActivity;
import com.snowleopard.poemapp.ui.main.BaseActivity;
import com.snowleopard.poemapp.R;
import com.snowleopard.poemapp.logic.model.Poem;

import java.util.ArrayList;
import java.util.List;

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
        Bundle bundle = intent.getBundleExtra("bundle");
        Poem poem= bundle.getParcelable("poem_data");

         poamBinding.setPoem(poem);

        ArrayList list = bundle.getParcelableArrayList("poem_list");
        List<Poem> poemList = (List<Poem>) list.get(0);



//         因为会一直监听所以不能用这个

//         poamBinding.cbCollect.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//             @Override
//             public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                 if (isChecked){
//                     String userName = poemViewModel.getUserName();
//                     poemViewModel.collectDelete(poem.getpId(),userName);
//                 }else {
//                     String userName = poemViewModel.getUserName();
//                     poemViewModel.collect(poem.getpId(),userName);
//                 }
//             }
//         });

         poamBinding.cbCollect.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 boolean state = poamBinding.cbCollect.isChecked();
                 if (state){
                     String userName = poemViewModel.getUserName();
                     poemViewModel.collect(poem.getpId(),userName); //逻辑反了 ，离谱
                     Toast.makeText(PoemActivity.this,"收藏成功!",Toast.LENGTH_SHORT).show();
//                     poamBinding.cbCollect.setChecked(true);
                 }else {
                     String userName = poemViewModel.getUserName();

                     poemViewModel.collectDelete(poem.getpId(),userName);

                     Toast.makeText(PoemActivity.this,"取消收藏",Toast.LENGTH_SHORT).show();
//                     poamBinding.cbCollect.setChecked(false);
                 }

             }
         });

        poemViewModel.getCollectionList(poemViewModel.getUser().getUsername()).observe(this, new Observer<List<Poem>>() {

            @Override
            public void onChanged(List<Poem> poems) {
                int flag=0;
                for (Poem p: poems) {
                    if (p.getpId().equals(poem.getpId())){
                        flag=1;
                        break;
                    }
                }
                    if (flag==1){
                        poamBinding.cbCollect.setChecked(true);
//                        poamBinding.cbCollect.setBackgroundResource(R.drawable.collected);
                    }
            }
        });


//        poemViewModel.collect("","").observe(this, new Observer<Integer>() {
//            @Override
//            public void onChanged(Integer integer) {
//                    Toast.makeText(PoemActivity.this,"收藏成功!",Toast.LENGTH_SHORT).show();
//                poamBinding.cbCollect.setChecked(true);
//            }
//        });
//
//        poemViewModel.collectDelete("","").observe(this, new Observer<Integer>() {
//            @Override
//            public void onChanged(Integer integer) {
//                    Toast.makeText(PoemActivity.this,"取消收藏",Toast.LENGTH_SHORT).show();
//                poamBinding.cbCollect.setChecked(false);
//            }
//        });

    }


}