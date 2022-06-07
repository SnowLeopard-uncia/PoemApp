package com.snowleopard.poemapp.ui.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.snowleopard.poemapp.logic.Repository;
import com.snowleopard.poemapp.logic.model.Poem;
import com.snowleopard.poemapp.logic.model.PoemDialog;
import com.snowleopard.poemapp.logic.model.UserInfo;

import java.util.ArrayList;
import java.util.List;

public class MainViewModel extends ViewModel {
    //ViewModel设置共享值

    //可能因为共享了这个list，所以导致Recycler View的错误
    //解决方案：三个不同的list变量  事实证明,三个Fragment不能公用一个list变量
    // 为什么呢，因为三个RecyclerView是不同的，，三个Recycler View使用的List不一样，
    // Inconsistency detected. Invalid view holder adapter positionViewHolder，数据移除和数据增加时，RecyclerView的Adapter中的数据集和移除／添加等操作后的数据集没有保持一致
    //就是索引溢出 跟Adapter没关系，三个RecyclerView可以公用一个Adapter，只要数据和布局一样就行。注意传进去的List要一致

    private List<Poem> poemListP = new ArrayList<>();

    private List<Poem> poemListM = new ArrayList<>();

    private List<Poem> poemListH = new ArrayList<>();


    public List<Poem> getPoemListP() {
        return poemListP;
    }

    public void setPoemListP(List<Poem> poemListP) {
        this.poemListP = poemListP;
    }

    public List<Poem> getPoemListM() {
        return poemListM;
    }

    public void setPoemListM(List<Poem> poemListM) {
        this.poemListM = poemListM;
    }

    public List<Poem> getPoemListH() {
        return poemListH;
    }

    public void setPoemListH(List<Poem> poemListH) {
        this.poemListH = poemListH;
    }

    public LiveData<List<Poem>> getPoemByLevelP() {
        return Repository.getInstance().getPoemByLevelP();
    }

    public LiveData<List<Poem>> getPoemByLevelM() {
        return Repository.getInstance().getPoemByLevelM();
    }

    public LiveData<List<Poem>> getPoemByLevelH() {
        return Repository.getInstance().getPoemByLevelH();
    }

    //    public LiveData<List<PoemDialog>> getPoemDialogByLevel(int level){
//        return Repository.repository.getPoemDialogByLevel(level);
//    }
    public UserInfo getUser() {
       return Repository.getInstance().getUserName();
    }

}
