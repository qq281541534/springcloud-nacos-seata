package com.work.common.aop;

import com.work.common.component.RespBody;
import com.work.common.component.RespCode;
import com.work.common.exception.DefaultException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@Slf4j
@ControllerAdvice(basePackages = "com.work")
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public RespBody exceptionHandler(Exception e) {
        log.error("【系统抛出Exception异常】 —— 异常内容如下：{}", e);
        RespBody respBody = new RespBody<>();
        respBody.setStatus(RespCode.FAIL.getCode());
        respBody.setMessage(RespCode.FAIL.getMessage());
        return respBody;
    }

    @ExceptionHandler(DefaultException.class)
    @ResponseBody
    public RespBody defaultException(DefaultException e) {
        log.error("【系统抛出DefaultException异常】 —— 异常内容如下：{}", e);
        RespBody respBody = new RespBody<>();
        respBody.setStatus(RespCode.FAIL.getCode());
        respBody.setMessage(RespCode.FAIL.getMessage());
        return respBody;
    }
}