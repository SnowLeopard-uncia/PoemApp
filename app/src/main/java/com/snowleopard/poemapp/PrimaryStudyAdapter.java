package com.snowleopard.poemapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.snowleopard.poemapp.databinding.ItemStudyBinding;
import com.snowleopard.poemapp.logic.model.Poem;

import java.util.ArrayList;
import java.util.List;

public class PrimaryStudyAdapter extends RecyclerView.Adapter<PrimaryStudyAdapter.PrimaryStudyViewHolder> {

    private List<Poem> poemList = new ArrayList<>();
    @NonNull
    @Override
    public PrimaryStudyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_study,parent,false);
        return new PrimaryStudyViewHolder(view);
    }

//    public PrimaryStudyAdapter(List<Poem> poemList) {
//        this.poemList = poemList;
//    }
        public void setPoems(List<Poem> poemList){
        this.poemList=poemList;
        //recycler View改变
        notifyDataSetChanged();
    }


    @Override
    public void onBindViewHolder(@NonNull PrimaryStudyViewHolder holder, int position) {
        Poem poem = poemList.get(holder.getAdapterPosition());
        holder.itemStudyBinding.tvPoemTitle.setText(poem.getTitle());
        holder.itemStudyBinding.tvPoemAuthor.setText(poem.getAuthor());
        holder.itemStudyBinding.tvPoemContent.setText(poem.getOriginal());


    }

    @Override
    public int getItemCount() {
        return poemList.size();
    }

    public class PrimaryStudyViewHolder extends RecyclerView.ViewHolder {
        ItemStudyBinding itemStudyBinding;

        public PrimaryStudyViewHolder(@NonNull View itemView) {
            super(itemView);
            itemStudyBinding= DataBindingUtil.bind(itemView);
        }
    }
}
