package com.snowleopard.poemapp.ui.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.snowleopard.poemapp.ui.ChangeInfoActivity;
import com.snowleopard.poemapp.databinding.FragmentPersonalBinding;
import com.snowleopard.poemapp.ui.CollectionActivity;
import com.snowleopard.poemapp.ui.ComplexProblemActivity;

public class PersonalFragment extends Fragment {

    FragmentPersonalBinding personalBinding;
    public PersonalFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        personalBinding=FragmentPersonalBinding.inflate(inflater);
        personalBinding.llCollection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), CollectionActivity.class);
                startActivity(intent);
            }
        });

        personalBinding.llMistakes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ComplexProblemActivity.class);
                startActivity(intent);
            }
        });

        personalBinding.llChangeInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ChangeInfoActivity.class);
                startActivity(intent);
            }
        });



        return personalBinding.getRoot();
    }
}