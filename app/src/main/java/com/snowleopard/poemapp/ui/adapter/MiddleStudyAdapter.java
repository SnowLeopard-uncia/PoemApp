package com.snowleopard.poemapp.ui.adapter;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.snowleopard.poemapp.R;
import com.snowleopard.poemapp.databinding.ItemStudyBinding;
import com.snowleopard.poemapp.logic.model.Poem;
import com.snowleopard.poemapp.ui.poem.PoemActivity;

import java.util.ArrayList;
import java.util.List;

public class MiddleStudyAdapter extends RecyclerView.Adapter<MiddleStudyAdapter.ViewHolder> {
    private List<Poem> poemList;
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_study,parent,false);
//        ViewHolder holder = new ViewHolder(view);
        return new ViewHolder(view);
    }

    public MiddleStudyAdapter(List<Poem> poemList) {
        this.poemList = poemList;
    }
    public void setPoems(List<Poem> poemList){
        this.poemList=poemList;
        //recycler View改变
        notifyDataSetChanged(); //这个放在外面还是放在里面效果都是一样
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Poem poem = poemList.get(holder.getAdapterPosition());
        holder.itemStudyBinding.setStudyAuthor(poem.getAuthor());
        holder.itemStudyBinding.setStudyContent(poem.getOriginal());
        holder.itemStudyBinding.setStudyTitle(poem.getTitle());

//        holder.itemStudyBinding.tvPoemTitle.setText(poem.getTitle());
//        holder.itemStudyBinding.tvPoemAuthor.setText(poem.getAuthor());
//        holder.itemStudyBinding.tvPoemContent.setText(poem.getOriginal());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), PoemActivity.class);
                Bundle bundle = new Bundle();
                bundle.putParcelable("poem_data",poem);
                ArrayList list = new ArrayList();
                list.add(poemList);
                bundle.putParcelableArrayList("poem_list",list);
                intent.putExtra("bundle",bundle);
                view.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return poemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemStudyBinding itemStudyBinding;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemStudyBinding= DataBindingUtil.bind(itemView);
        }
    }
}
