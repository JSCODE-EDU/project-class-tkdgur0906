package com.jscode.board.exception.jwt;

import com.jscode.board.exception.code.ErrorCode;
import com.jscode.board.exception.common.BusinessException;

public class MissingTokenException extends BusinessException {
    public MissingTokenException() {
        super(ErrorCode.MISSING_TOKEN_EXCEPTION);
    }
}
