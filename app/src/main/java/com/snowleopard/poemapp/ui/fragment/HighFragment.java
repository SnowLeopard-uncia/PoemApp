package com.snowleopard.poemapp.ui.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.snowleopard.poemapp.MainViewModel;
import com.snowleopard.poemapp.PrimaryStudyAdapter;
import com.snowleopard.poemapp.R;
import com.snowleopard.poemapp.databinding.FragmentHighBinding;
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
        PrimaryStudyAdapter adapter = new PrimaryStudyAdapter();
        fragmentHighBinding.rvStudyHigh.setAdapter(adapter);

        mainViewModel.getPoemByLevelH().observe(this.getActivity(), new Observer<List<Poem>>() {
            @Override
            public void onChanged(List<Poem> poems) {
                adapter.setPoems(poems);
            }
        });

        return fragmentHighBinding.getRoot();
    }
}