package com.jscode.board.exception.common;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class BusinessException extends RuntimeException {

    private final ErrorCode errorCode;

    public BusinessException(ErrorCode errorCode) {
        super();
        this.errorCode = errorCode;
    }

    public BusinessException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public String getStatus(){
        return String.valueOf(errorCode.getStatus());
    }

    public HttpStatus getHttpStatus(){
        return errorCode.getHttpStatus();
    }
}
