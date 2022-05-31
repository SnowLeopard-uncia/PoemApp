package com.snowleopard.poemapp.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;

import com.snowleopard.poemapp.R;
import com.snowleopard.poemapp.databinding.ItemAnswerBinding;
import com.snowleopard.poemapp.databinding.ItemAskBinding;
import com.snowleopard.poemapp.logic.model.Dialog;

import java.util.ArrayList;
import java.util.List;

public class DialogAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Dialog> dialogList;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        switch (viewType){
            case Dialog.TYPE_ANSWER:
                return new AskViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ask,parent,false));
                case Dialog.TYPE_ASK:
                return new AnswerViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_answer,parent,false));
        }
        return null;
    }

    public DialogAdapter(List<Dialog> dialogList) {
        dialogList.add(new Dialog("hello",Dialog.TYPE_ANSWER));
        this.dialogList = dialogList;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Dialog dialog = dialogList.get(position);
        if (holder instanceof AskViewHolder){
            ((AskViewHolder) holder).itemAskBinding.tvDialogAsk.setText(dialog.getContent());
        }
        if (holder instanceof AnswerViewHolder){
            ((AnswerViewHolder) holder).itemAnswerBinding.tvDialogAnswer.setText(dialog.getContent());
        }
    }

    @Override
    public int getItemViewType(int position) {
        Dialog dialog = dialogList.get(position);
        return dialog.getType();

    }


    @Override
    public int getItemCount() {
        return 0;
    }

    public static class AskViewHolder extends RecyclerView.ViewHolder {
        ItemAskBinding itemAskBinding;

        public AskViewHolder(@NonNull View itemView) {
            super(itemView);
            itemAskBinding= DataBindingUtil.bind(itemView);
        }
    }

    public static class AnswerViewHolder extends RecyclerView.ViewHolder {
        ItemAnswerBinding itemAnswerBinding;

        public AnswerViewHolder(@NonNull View itemView) {
            super(itemView);
            itemAnswerBinding=DataBindingUtil.bind(itemView);
        }
    }


}
