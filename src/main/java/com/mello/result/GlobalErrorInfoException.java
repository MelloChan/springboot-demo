package com.mello.result;

/**
 * Created by Administrator on 2017/5/10.
 * 统一错误码异常
 */
public class GlobalErrorInfoException extends Exception {
    private ErrorInfoInterface errorInfo;
    public GlobalErrorInfoException(ErrorInfoInterface errorInfo){
        this.errorInfo=errorInfo;
    }

    public ErrorInfoInterface getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(ErrorInfoInterface errorInfo) {
        this.errorInfo = errorInfo;
    }
}
