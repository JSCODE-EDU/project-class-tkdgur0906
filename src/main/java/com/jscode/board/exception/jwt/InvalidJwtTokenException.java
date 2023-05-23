package com.jscode.board.exception.jwt;

import com.jscode.board.exception.code.ErrorCode;
import com.jscode.board.exception.common.BusinessException;

public class InvalidJwtTokenException extends BusinessException {
    public InvalidJwtTokenException() {
        super(ErrorCode.INVALID_JWT_TOKEN_EXCEPTION);
    }
}
