package com.freetalk.cherokee.exception;

import com.freetalk.cherokee.dto.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author : doujl
 * @className : DefaultGlobalExceptionHandlerAdvice
 * @description : 异常处理
 * @date : 2020-10-29 16:36:52
 */
@ControllerAdvice
public class DefaultGlobalExceptionHandlerAdvice {
    private Logger log = LoggerFactory.getLogger(DefaultGlobalExceptionHandlerAdvice.class);

    @ExceptionHandler(value = {BaseException.class})
    @ResponseBody
    public Result baseException(BaseException ex) {
        log.error("base exception:", ex);
        return Result.fail(ex.getErrorType());
    }
}