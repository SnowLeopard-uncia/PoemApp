package com.snowleopard.poemapp.logic.model;

import com.snowleopard.poemapp.utils.Constants;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 公共数据响应类
 * @param <T>
 */


public class BaseResponse<T> {
    //json返回的cond，msg和data
    private int code;
    private String msg;
    private T data;

    //判断状态码是否异常
    public boolean isCodeInvalid() {
        return code != Constants.SUCCESS;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
