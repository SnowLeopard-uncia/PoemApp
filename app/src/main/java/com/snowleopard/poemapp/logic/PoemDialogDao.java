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

    @Query("SELECT * FROM poem_dialog_table WHERE level_column = 0")
    LiveData<List<PoemDialog>> getAllPrimaryPoems();

    @Query("SELECT * FROM poem_dialog_table WHERE level_column = 1")
    LiveData<List<PoemDialog>> getAllMiddlePoems();

    @Query("SELECT * FROM poem_dialog_table WHERE level_column = 2")
    LiveData<List<PoemDialog>> getAllHighPoems();

}
