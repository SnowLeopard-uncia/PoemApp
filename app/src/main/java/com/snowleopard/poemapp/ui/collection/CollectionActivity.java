package com.snowleopard.poemapp.ui.collection;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.snowleopard.poemapp.BaseActivity;
import com.snowleopard.poemapp.R;
import com.snowleopard.poemapp.databinding.ActivityCollectionBinding;
import com.snowleopard.poemapp.logic.model.Poem;
import com.snowleopard.poemapp.ui.adapter.CollectionAdapter;

import java.util.List;

public class CollectionActivity extends BaseActivity {

    ActivityCollectionBinding activityCollectionBinding;

    CollectionViewModel collectionViewModel;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityCollectionBinding = DataBindingUtil.setContentView(this,R.layout.activity_collection);
        collectionViewModel = new ViewModelProvider(this).get(CollectionViewModel.class);


        collectionViewModel.getCollectionList(collectionViewModel.getUser().getUsername()).observe(this, new Observer<List<Poem>>() {

            @Override
            public void onChanged(List<Poem> poems) {
                if (poems!=null){
                    collectionViewModel.getPoemList().clear();
                    collectionViewModel.setPoemList(poems);
                    CollectionAdapter adapter  = new CollectionAdapter(poems);  //adapter要在这里创建，如果搞在View Model的话就不显示 ？
                    activityCollectionBinding.rvCollection.setLayoutManager(new LinearLayoutManager(CollectionActivity.this));
                    activityCollectionBinding.rvCollection.setAdapter(adapter);

                    Log.e("TAG", "onChanged: poemlist"+poems.size() );
                    activityCollectionBinding.tvNoCollection.setVisibility(View.GONE);

//                    activityCollectionBinding.rvCollection.getAdapter().notifyDataSetChanged();

                }else {
                    activityCollectionBinding.tvNoCollection.setVisibility(View.VISIBLE);
                    Toast.makeText(CollectionActivity.this,"暂无诗词",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}