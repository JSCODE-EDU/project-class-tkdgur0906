package com.jscode.board.exception.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    NOT_FOUND_BOARD_EXCEPTION(404, "게시글이 존재하지 않습니다.", HttpStatus.NOT_FOUND);

    private final int status;
    private final String message;
    private final HttpStatus httpStatus;

}
