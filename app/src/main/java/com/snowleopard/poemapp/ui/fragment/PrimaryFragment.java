package com.snowleopard.poemapp.ui.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.snowleopard.poemapp.MainViewModel;
import com.snowleopard.poemapp.PrimaryStudyAdapter;
import com.snowleopard.poemapp.R;
import com.snowleopard.poemapp.databinding.FragmentPrimaryBinding;
import com.snowleopard.poemapp.databinding.FragmentStudyBinding;
import com.snowleopard.poemapp.logic.model.Poem;

import java.util.ArrayList;
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
        PrimaryStudyAdapter adapter = new PrimaryStudyAdapter();
        fragmentPrimaryBinding.rvStudyPrimary.setAdapter(adapter);

        mainViewModel.getPoemByLevelP().observe(this.getActivity(), new Observer<List<Poem>>() {
            @Override
            public void onChanged(List<Poem> poems) {
                adapter.setPoems(poems);
            }
        });

        return fragmentPrimaryBinding.getRoot();
    }


}