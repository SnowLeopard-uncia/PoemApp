package com.snowleopard.poemapp.logic.model;

public class Dialog {
    public static final int TYPE_ASK=0;
    public static final int TYPE_ANSWER=1;
    private String content;
    private int type;

    public Dialog(String content, int type) {
        this.content = content;
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
