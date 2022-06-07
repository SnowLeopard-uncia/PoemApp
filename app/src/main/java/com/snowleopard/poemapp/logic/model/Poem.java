package com.snowleopard.poemapp.logic.model;


import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * 可恶后端没有那个确认是否收藏的接口，搞得我要传递一个是否确认的接口过去
 */

public class Poem implements Serializable,Parcelable {

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

    protected Poem(Parcel in) {
        if (in.readByte() == 0) {
            pLevel = null;
        } else {
            pLevel = in.readInt();
        }
        original = in.readString();
        author = in.readString();
        background = in.readString();
        parse = in.readString();
        title = in.readString();
        pId = in.readString();
    }


    public static final Creator<Poem> CREATOR = new Creator<Poem>() {
        @Override
        public Poem createFromParcel(Parcel in) {
            return new Poem(in);
        }

        @Override
        public Poem[] newArray(int size) {
            return new Poem[size];
        }
    };

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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (pLevel == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(pLevel);
        }
        dest.writeString(original); //写出
        dest.writeString(author);
        dest.writeString(background);
        dest.writeString(parse);
        dest.writeString(title);
        dest.writeString(pId);
    }
}
