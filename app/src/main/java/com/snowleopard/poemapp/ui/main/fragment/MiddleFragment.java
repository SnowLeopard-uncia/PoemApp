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

import com.snowleopard.poemapp.databinding.FragmentMiddleBinding;
import com.snowleopard.poemapp.ui.main.MainViewModel;
import com.snowleopard.poemapp.logic.model.Poem;
import com.snowleopard.poemapp.ui.adapter.StudyAdapter;

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
        StudyAdapter adapter = new StudyAdapter(mainViewModel.getPoemListM());
        fragmentMiddleBinding.rvStudyMiddle.setAdapter(adapter);

        mainViewModel.getPoemByLevelM().observe(this.getActivity(), new Observer<List<Poem>>() {
            @Override
            public void onChanged(List<Poem> poems) {
                if (poems!=null){
                    mainViewModel.getPoemListM().clear();//因为用的同一个ViewModel，所以不clear的话，会出现和primary一样的数据
                    mainViewModel.setPoemListM(poems);
                    adapter.setPoems(mainViewModel.getPoemListM());
                    Log.e("TAG", "onChanged: "+"middle" );
                }else {
                    Toast.makeText(getActivity(),"暂无诗词",Toast.LENGTH_SHORT).show();
                }
            }
        });
        return fragmentMiddleBinding.getRoot();
    }
}