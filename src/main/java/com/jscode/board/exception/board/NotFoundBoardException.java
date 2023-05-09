package com.jscode.board.exception.board;

import com.jscode.board.exception.common.BusinessException;
import com.jscode.board.exception.common.ErrorCode;

public class NotFoundBoardException extends BusinessException {

    public NotFoundBoardException() {
        super(ErrorCode.NOT_FOUND_BOARD_EXCEPTION);
    }
}
