package com.snowleopard.poemapp.logic.model;

import android.provider.SyncStateContract;

import com.snowleopard.poemapp.utils.StatusCode;


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
        return code != StatusCode.SUCCESS.code;
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
