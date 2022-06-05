package com.snowleopard.poemapp.ui.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.snowleopard.poemapp.R;
import com.snowleopard.poemapp.databinding.ItemMistakesBinding;
import com.snowleopard.poemapp.logic.model.Question;
import com.snowleopard.poemapp.ui.poem.PoemActivity;

import java.util.ArrayList;
import java.util.List;

public class MistakesAdapter extends RecyclerView.Adapter<MistakesAdapter.ViewHolder> {

    private List<Question> list = new ArrayList<>();
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mistakes,parent,false);
        return new ViewHolder(view);
    }

    public MistakesAdapter(List<Question> list) {
        this.list = list;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Question question = list.get(position);
        holder.itemMistakesBinding.setQuestion(question);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {  //错题跳转到一个Activity
//                Intent intent = new Intent(view.getContext(), .class);
//                intent.putExtra("question",question);
//                view.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ItemMistakesBinding itemMistakesBinding;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemMistakesBinding= DataBindingUtil.bind(itemView);
        }
    }
}
