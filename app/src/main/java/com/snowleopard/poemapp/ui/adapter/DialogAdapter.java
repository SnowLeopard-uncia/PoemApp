package com.snowleopard.poemapp.ui.adapter;

import android.provider.ContactsContract;
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

    private final List<Dialog> dialogList;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch (viewType){
            case Dialog.TYPE_ASK:
                ItemAskBinding  askBinding = DataBindingUtil.inflate(inflater,R.layout.item_ask,
                        parent,false);
                return new AskViewHolder(askBinding.getRoot());
//                return new AskViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ask,
//                        parent,false));
            case Dialog.TYPE_ANSWER:
                ItemAnswerBinding  answerBinding = DataBindingUtil.inflate(inflater,R.layout.item_answer,
                        parent,false);
                return new AnswerViewHolder(answerBinding.getRoot());
//                return new AnswerViewHolder((LayoutInflater.from(parent.getContext())).inflate(R.layout.item_answer,parent,
//                        false));
        }
        return null;
    }

    public DialogAdapter(List<Dialog> dialogList) {
        dialogList.add(new Dialog("欢迎来到对王之王",Dialog.TYPE_ASK));
        dialogList.add(new Dialog("发送任意内容开始",Dialog.TYPE_ASK));
//        不能在这里初始化的两句，因为旋转屏幕时Activity重走，又会重写初始化
//        又或者有没有想过让View Model持有Adapter 如果让View Model持有Adapter，
//        这里就可以初始化，因为这样Activity旋转屏幕就只会创建一个Adapter
        this.dialogList = dialogList;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Dialog dialog = dialogList.get(position);
        if (holder instanceof AskViewHolder){
//            ((AskViewHolder) holder).itemAskBinding.tvDialogAsk.setText(dialog.getContent());
            ((AskViewHolder) holder).itemAskBinding.setTvAsk(dialog.getContent());
        }
        if (holder instanceof AnswerViewHolder){  //上面的写法可能会导致不显示item的文字
//            ((AnswerViewHolder) holder).itemAnswerBinding.tvDialogAnswer.setText(dialog.getContent());
            ((AnswerViewHolder) holder).itemAnswerBinding.setTvAnswer(dialog.getContent());
        }
    }

    @Override
    public int getItemViewType(int position) {
        Dialog dialog = dialogList.get(position);
        return dialog.getType();

    }


    @Override
    public int getItemCount() {
        return dialogList.size();
//        return 0;   //我tm无语了就是因为这个0，导致我的RecyclerView显示不出来 把这个改了就好了！！！！！！
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
