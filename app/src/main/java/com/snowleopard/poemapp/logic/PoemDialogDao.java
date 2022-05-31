package com.snowleopard.poemapp.logic;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.snowleopard.poemapp.logic.model.PoemDialog;

import java.util.List;

import retrofit2.http.DELETE;

@Dao
public interface PoemDialogDao {

    @Insert
    void insert(PoemDialog poemDialog);

    @Update
    void update(PoemDialog poemDialog);

    @Delete
    void delete(PoemDialog poemDialog);

    @Query("delete from poem_dialog_table")
    void deleteAll();


    @Query("SELECT * FROM poem_dialog_table WHERE level_column =:level")
    LiveData<List<PoemDialog>> getAllPoemsByLevel(int level);

    @Query("SELECT * FROM poem_dialog_table")
    LiveData<List<PoemDialog>> getAllPoems();

}
