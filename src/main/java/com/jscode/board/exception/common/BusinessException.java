package com.jscode.board.exception.common;

import com.jscode.board.exception.code.ErrorCode;
import org.springframework.http.HttpStatus;

public class BusinessException extends RuntimeException {

    private final ErrorCode errorCode;

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public BusinessException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public int getStatus(){
        return errorCode.getStatus();
    }

    public HttpStatus getHttpStatus(){
        return errorCode.getHttpStatus();
    }
}
