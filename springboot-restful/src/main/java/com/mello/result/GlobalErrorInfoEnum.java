package com.mello.result;

/**
 * Created by Administrator on 2017/5/10.
 * 全局错误码
 */
public enum GlobalErrorInfoEnum implements ErrorInfoInterface {
    SUCCESS("0", "'success"),
    NOT_FOUNT("-1", "service not found");
    private String code;
    private String message;

    GlobalErrorInfoEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
