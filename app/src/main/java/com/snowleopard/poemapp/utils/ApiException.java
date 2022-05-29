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
     * 还有其他状态码要跟后端商量
     */
    public boolean isFail() {
        return errorCode == StatusCode.SERVER_ERROR.code;
    }

    public boolean isNotAllowedReg(){
        return errorCode == StatusCode.NOT_ALLOWRD_REG.code;
    }

    public boolean isUserNotExist(){
        return errorCode == StatusCode.USER_DOES_NOT_EXIST.code;
    }
    public boolean isPasswordWrong(){
        return errorCode == StatusCode.PASSWORD_WRONG.code;
    }

    public boolean isPoemNotFound(){
        return errorCode==StatusCode.POEM_NOT_FOUND.code;
    }


}
