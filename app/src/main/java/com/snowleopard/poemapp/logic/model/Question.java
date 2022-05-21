package com.snowleopard.poemapp.logic.model;

import com.google.gson.annotations.SerializedName;

import lombok.Data;
import lombok.NoArgsConstructor;
//后台服务器返回的json字段经常不符合java的驼峰命名规范，有的带下划线，有的首字母没有小写，
// 如果我们不想改变原有的Java类变量名又想成功解析，可以借助@SerializedName


@NoArgsConstructor
@Data
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
}

