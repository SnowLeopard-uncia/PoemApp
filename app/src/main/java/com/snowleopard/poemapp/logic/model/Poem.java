package com.snowleopard.poemapp.logic.model;


import com.google.gson.annotations.SerializedName;

public class Poem {

    @SerializedName("p_level")
    private Integer pLevel;
    @SerializedName("original")
    private String original;
    @SerializedName("author")
    private String author;
    @SerializedName("background")
    private String background;
    @SerializedName("parse")
    private String parse;
    @SerializedName("title")
    private String title;
    @SerializedName("p_id")
    private String pId;

    public Integer getpLevel() {
        return pLevel;
    }

    public void setpLevel(Integer pLevel) {
        this.pLevel = pLevel;
    }

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public String getParse() {
        return parse;
    }

    public void setParse(String parse) {
        this.parse = parse;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public Poem(Integer pLevel, String original, String author, String background, String parse, String title, String pId) {
        this.pLevel = pLevel;
        this.original = original;
        this.author = author;
        this.background = background;
        this.parse = parse;
        this.title = title;
        this.pId = pId;
    }
}
