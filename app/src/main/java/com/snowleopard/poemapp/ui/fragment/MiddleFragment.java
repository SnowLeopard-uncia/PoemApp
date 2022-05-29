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
import com.snowleopard.poemapp.MiddleStudyAdapter;
import com.snowleopard.poemapp.PrimaryStudyAdapter;
import com.snowleopard.poemapp.R;
import com.snowleopard.poemapp.databinding.FragmentMiddleBinding;
import com.snowleopard.poemapp.logic.model.Poem;

import java.util.List;


public class MiddleFragment extends Fragment {
     FragmentMiddleBinding fragmentMiddleBinding;

    MainViewModel mainViewModel;
    public MiddleFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentMiddleBinding=FragmentMiddleBinding.inflate(inflater);
        mainViewModel=new ViewModelProvider(requireActivity()).get(MainViewModel.class);
        LinearLayoutManager linearLayoutManager =new LinearLayoutManager(this.getActivity());
        fragmentMiddleBinding.rvStudyMiddle.setLayoutManager(linearLayoutManager);
        MiddleStudyAdapter adapter = new MiddleStudyAdapter();
        fragmentMiddleBinding.rvStudyMiddle.setAdapter(adapter);

        mainViewModel.getPoemByLevelM().observe(this.getActivity(), new Observer<List<Poem>>() {
            @Override
            public void onChanged(List<Poem> poems) {
                adapter.setPoems(poems);
            }
        });
        return fragmentMiddleBinding.rvStudyMiddle;
    }
}