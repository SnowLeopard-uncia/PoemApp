package com.snowleopard.poemapp.ui.main.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.snowleopard.poemapp.databinding.FragmentPrimaryBinding;
import com.snowleopard.poemapp.ui.main.MainViewModel;
import com.snowleopard.poemapp.ui.adapter.StudyAdapter;
import com.snowleopard.poemapp.logic.model.Poem;

import java.util.List;

public class PrimaryFragment extends Fragment {
    FragmentPrimaryBinding fragmentPrimaryBinding;
//    private List<Poem> poemList = new ArrayList<>();
    MainViewModel mainViewModel;

    public PrimaryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentPrimaryBinding=FragmentPrimaryBinding.inflate(inflater);
        mainViewModel = new ViewModelProvider(requireActivity()).get(MainViewModel.class);

        LinearLayoutManager linearLayoutManager =new LinearLayoutManager(this.getActivity());
        fragmentPrimaryBinding.rvStudyPrimary.setLayoutManager(linearLayoutManager);
        StudyAdapter adapter = new StudyAdapter(mainViewModel.getPoemListP());
//        StudyAdapter adapter = new StudyAdapter();
        fragmentPrimaryBinding.rvStudyPrimary.setAdapter(adapter);

        mainViewModel.getPoemByLevelP().observe(this.getActivity(), new Observer<List<Poem>>() {
            @Override
            public void onChanged(List<Poem> poems) {
                if (poems!=null){

                    mainViewModel.getPoemListP().clear();
                    mainViewModel.setPoemListP(poems);
                    adapter.setPoems(mainViewModel.getPoemListP());
                    Log.e("TAG", "onChanged: "+"primary" );
                }else {
                    Toast.makeText(getActivity(),"暂无诗词",Toast.LENGTH_SHORT).show();
                }
            }
        });

        return fragmentPrimaryBinding.getRoot();
    }


}