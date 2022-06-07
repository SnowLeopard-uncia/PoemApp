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

import com.snowleopard.poemapp.databinding.FragmentHighBinding;
import com.snowleopard.poemapp.ui.main.MainViewModel;
import com.snowleopard.poemapp.ui.adapter.StudyAdapter;
import com.snowleopard.poemapp.logic.model.Poem;

import java.util.List;


public class HighFragment extends Fragment {

    FragmentHighBinding fragmentHighBinding;
    MainViewModel mainViewModel;
    public HighFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         fragmentHighBinding=FragmentHighBinding.inflate(inflater);
         mainViewModel =new ViewModelProvider(requireActivity()).get(MainViewModel.class);

        LinearLayoutManager linearLayoutManager =new LinearLayoutManager(this.getActivity());
        fragmentHighBinding.rvStudyHigh.setLayoutManager(linearLayoutManager);
        StudyAdapter adapter = new StudyAdapter(mainViewModel.getPoemListH());
        fragmentHighBinding.rvStudyHigh.setAdapter(adapter);

        mainViewModel.getPoemByLevelH().observe(this.getActivity(), new Observer<List<Poem>>() {
            @Override
            public void onChanged(List<Poem> poems) {
                if (poems!=null){
                    mainViewModel.getPoemListH().clear();
                    mainViewModel.setPoemListH(poems);
                    adapter.setPoems(mainViewModel.getPoemListH());
                    Log.e("TAG", "onChanged: "+"high" );
                }else {
                    Toast.makeText(getActivity(),"暂无诗词",Toast.LENGTH_SHORT).show();
                }
            }
        });

        return fragmentHighBinding.getRoot();
    }
}