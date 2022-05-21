package com.snowleopard.poemapp.logic.model;

public class BaseResponse<T> {
    //json返回的cond，msg和data
    private int code;
    private String msg;
    private T data;

}
