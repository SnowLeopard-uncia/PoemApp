package com.snowleopard.poemapp.utils;

/**
 * 状态码错误的异常处理
 */
public class ApiException extends RuntimeException{
    private int errorCode;

    public ApiException(int errorCode,String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
    }
    /**
     * 判断是否500
     *
     * 还有其他状态码要跟后端商量
     */
    public boolean isFail() {
        return errorCode == Constants.FAIL;
    }

}
