package com.work.common.exception;

import com.work.common.component.RespCode;

public class DefaultException extends RuntimeException {

    private RespCode respCode;

    public DefaultException(String message, Throwable cause) {
        super(message, cause);
    }

    public DefaultException(RespCode respCode) {
        super(respCode.getMessage());
        this.respCode = respCode;
    }

    public DefaultException(RespCode respCode, Throwable cause) {
        super(respCode.getMessage(), cause);
        this.respCode = respCode;
    }

    public RespCode getRespCode() {
        return respCode;
    }

    public void setRespCode(RespCode respCode) {
        this.respCode = respCode;
    }
}