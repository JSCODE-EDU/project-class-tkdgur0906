package com.jscode.board.exception.board;

import com.jscode.board.exception.code.ErrorCode;
import com.jscode.board.exception.common.BusinessException;

public class NoAuthorityMemberException extends BusinessException {
    public NoAuthorityMemberException() {
        super(ErrorCode.NO_AUTHORITY_MEMBER_EXCEPTION);
    }
}
