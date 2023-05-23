package com.jscode.board.exception.member;

import com.jscode.board.exception.code.ErrorCode;
import com.jscode.board.exception.common.BusinessException;

public class DuplicateEmailException extends BusinessException {
    public DuplicateEmailException() {
        super(ErrorCode.DUPLICATE_EMAIL_EXCEPTION);
    }
}