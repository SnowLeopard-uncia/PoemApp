package com.snowleopard.poemapp.ui.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.snowleopard.poemapp.R;
import com.snowleopard.poemapp.databinding.FragmentDialogBinding;
import com.snowleopard.poemapp.ui.dialog.DialogActivity;

public class DialogFragment extends Fragment implements View.OnClickListener{

    FragmentDialogBinding fragmentDialogBinding;
    public DialogFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        fragmentDialogBinding= FragmentDialogBinding.inflate(inflater);
        fragmentDialogBinding.btnPrimary.setOnClickListener(this);
        fragmentDialogBinding.btnMiddle.setOnClickListener(this);
        fragmentDialogBinding.btnHigh.setOnClickListener(this);
        fragmentDialogBinding.btnRandom.setOnClickListener(this);
        return fragmentDialogBinding.getRoot();
    }


    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View view) {
        int level = 0;
        switch (view.getId()){
            case R.id.btn_primary:
                level=0;
                break;
            case R.id.btn_middle:
                level=1;
                break;
            case R.id.btn_high:
                level=2;
                break;
            case R.id.btn_random:
                level=3;
                break;
            default:
                break;
        }
        Intent intent = new Intent(getActivity(), DialogActivity.class);
        intent.putExtra("level",level);
        startActivity(intent);
    }
}