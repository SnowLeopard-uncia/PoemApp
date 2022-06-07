package com.snowleopard.poemapp.logic.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "poem_dialog_table")
public class PoemDialog {

    //There are multiple good constructors and Room will pick the no-arg constructor.
    // You can use the @Ignore annotation to eliminate unwanted constructors
    @Ignore
    public PoemDialog() {
    }

    //id为自增主码
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String ask;
    private String answer;
    @ColumnInfo(name = "level_column")
    private int level;

    public PoemDialog(String ask, String answer, int level) {
        this.ask = ask;
        this.level = level;
        this.answer = answer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAsk() {
        return ask;
    }

    public void setAsk(String ask) {
        this.ask = ask;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
