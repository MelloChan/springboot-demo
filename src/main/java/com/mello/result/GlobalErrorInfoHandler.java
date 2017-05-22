package com.mello.result;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2017/5/10.
 * 统一错误码异常处理
 */
@RestControllerAdvice
public class GlobalErrorInfoHandler {
    @ExceptionHandler(value = GlobalErrorInfoException.class)
    public ResultBody errorHandlerOverJson(HttpServletRequest request,
                                           GlobalErrorInfoException exception){
        ErrorInfoInterface errorInfo=exception.getErrorInfo();
        return new ResultBody(errorInfo);
    }
}
