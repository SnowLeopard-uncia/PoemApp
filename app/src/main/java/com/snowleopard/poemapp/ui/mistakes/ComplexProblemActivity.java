package com.snowleopard.poemapp.ui.mistakes;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.snowleopard.poemapp.BaseActivity;
import com.snowleopard.poemapp.R;
import com.snowleopard.poemapp.databinding.ActivityCollectionBinding;
import com.snowleopard.poemapp.databinding.ActivityComplexProblemBinding;
import com.snowleopard.poemapp.logic.model.Question;
import com.snowleopard.poemapp.ui.adapter.MistakesAdapter;
import com.snowleopard.poemapp.ui.collection.CollectionViewModel;

import java.util.List;

public class ComplexProblemActivity extends BaseActivity {

   ActivityComplexProblemBinding activityComplexProblemBinding;
   MistakesViewModel mistakesViewModel;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityComplexProblemBinding = DataBindingUtil.setContentView(this,R.layout.activity_complex_problem);
        mistakesViewModel = new ViewModelProvider(this).get(MistakesViewModel.class);

        mistakesViewModel.getMistakesList(mistakesViewModel.getUser().getUsername()).observe(this, new Observer<List<Question>>() {
            @Override
            public void onChanged(List<Question> questions) {
                if (questions!=null){
                    mistakesViewModel.getQuestionList().clear();
                    mistakesViewModel.setQuestionList(questions);

                    MistakesAdapter mistakesAdapter = new MistakesAdapter(questions);
                    activityComplexProblemBinding.rvMistakes.setAdapter(mistakesAdapter);
                    activityComplexProblemBinding.rvMistakes.setLayoutManager(new LinearLayoutManager(ComplexProblemActivity.this));

                    activityComplexProblemBinding.tvNoMistakes.setVisibility(View.GONE);
                }else {
                    activityComplexProblemBinding.tvNoMistakes.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}