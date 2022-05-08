package com.guet.qiusuo.fruittravel.config;

public class SystemException extends RuntimeException {
    private ErrorCode errorCode;

    public SystemException(ErrorCode errorCode) {
        super(errorCode.getKey());
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
