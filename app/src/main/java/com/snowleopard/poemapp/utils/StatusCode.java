package com.snowleopard.poemapp.utils;

/**
 * 封装状态码
 */
public enum StatusCode {

    //http定义好的状态码
    SUCCESS(200),

    SERVER_ERROR(500),

    URL_NOT_FOUND(404),

    //自定义  状态码
    NOT_ALLOWRD_REG(1001),
    USER_DOES_NOT_EXIST(1002),
    PASSWORD_WRONG(1003),
    POEM_NOT_FOUND(1004),
    COLLECTION_NOT_FOUND(1005),
    NOT_ALLOWRD_COLLECT(1006),
    MISTAKES_NOT_FOUND(1007),
    MISTAKES_EXIST(1008);

    public int code;

    StatusCode(int code)
    {
        this.code=code;
    }

}
