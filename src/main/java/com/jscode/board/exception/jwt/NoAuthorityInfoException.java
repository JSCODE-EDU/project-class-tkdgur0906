package com.jscode.board.exception.jwt;

import com.jscode.board.exception.code.ErrorCode;
import com.jscode.board.exception.common.BusinessException;

public class NoAuthorityInfoException extends BusinessException {

    public NoAuthorityInfoException() {
        super(ErrorCode.NO_AUTHORITY_INFO_EXCEPTION);
    }
}
