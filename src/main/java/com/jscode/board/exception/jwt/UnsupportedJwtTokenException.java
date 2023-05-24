package com.jscode.board.exception.jwt;

import com.jscode.board.exception.code.ErrorCode;
import com.jscode.board.exception.common.BusinessException;

public class UnsupportedJwtTokenException extends BusinessException {
    public UnsupportedJwtTokenException() {
        super(ErrorCode.UNSUPPORTED_JWT_TOKEN_EXCEPTION);
    }
}
