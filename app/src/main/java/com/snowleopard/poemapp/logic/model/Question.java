package com.snowleopard.poemapp.logic.model;

import com.google.gson.annotations.SerializedName;


//后台服务器返回的json字段经常不符合java的驼峰命名规范，有的带下划线，有的首字母没有小写，
// 如果我们不想改变原有的Java类变量名又想成功解析，可以借助@SerializedName


public class Question {

    @SerializedName("question")
    private String question;
    @SerializedName("q_level")
    private Integer qLevel;
    @SerializedName("choice")
    private String choice;
    @SerializedName("right_choice")
    private String rightChoice;
    @SerializedName("q_type")
    private Integer qType;
    @SerializedName("q_id")
    private String qId;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Integer getqLevel() {
        return qLevel;
    }

    public void setqLevel(Integer qLevel) {
        this.qLevel = qLevel;
    }

    public String getChoice() {
        return choice;
    }

    public void setChoice(String choice) {
        this.choice = choice;
    }

    public String getRightChoice() {
        return rightChoice;
    }

    public void setRightChoice(String rightChoice) {
        this.rightChoice = rightChoice;
    }

    public Integer getqType() {
        return qType;
    }

    public void setqType(Integer qType) {
        this.qType = qType;
    }

    public String getqId() {
        return qId;
    }

    public void setqId(String qId) {
        this.qId = qId;
    }

    public Question(String question, Integer qLevel, String choice, String rightChoice, Integer qType, String qId) {
        this.question = question;
        this.qLevel = qLevel;
        this.choice = choice;
        this.rightChoice = rightChoice;
        this.qType = qType;
        this.qId = qId;
    }
}

