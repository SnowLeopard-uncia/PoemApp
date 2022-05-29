package com.snowleopard.poemapp.ui.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.snowleopard.poemapp.R;
import com.snowleopard.poemapp.databinding.FragmentDialogBinding;
import com.snowleopard.poemapp.ui.DialogActivity;

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

    @Override
    public void onClick(View view) {
//        switch (view.getId()){
//            case R.id.btn_primary:
//                break;
//            case R.id.btn_middle:
//                break;
//            case R.id.btn_high:
//                break;
//            case R.id.btn_random:
//                break;
//            default:
//                break;
//        }
        Intent intent = new Intent(getActivity(), DialogActivity.class);
        startActivity(intent);

    }
}