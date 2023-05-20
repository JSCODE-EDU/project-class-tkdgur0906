package com.jscode.board.exception.Member;

import com.jscode.board.exception.code.ErrorCode;
import com.jscode.board.exception.common.BusinessException;

public class DuplicateEmailException extends BusinessException {
    public DuplicateEmailException(ErrorCode errorCode) {
        super(ErrorCode.DUPLICATE_EMAIL_EXCEPTION);
    }
}