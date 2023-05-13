package com.jscode.board.exception.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    NOT_FOUND_BOARD_EXCEPTION(404, "게시글이 존재하지 않습니다.", HttpStatus.NOT_FOUND),
    INVALID_INPUT_EXCEPTION(400, "유효하지 않은 입력입니다.", HttpStatus.BAD_REQUEST);

    private final int status;
    private final String message;
    private final HttpStatus httpStatus;

}
