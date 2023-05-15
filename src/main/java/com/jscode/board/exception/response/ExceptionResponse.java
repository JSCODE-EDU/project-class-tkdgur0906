package com.jscode.board.exception.response;

import com.jscode.board.exception.code.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintViolationException;

@Getter
@RequiredArgsConstructor
public class ExceptionResponse {

    private final int status;
    private final String message;

    public static ExceptionResponse from(ConstraintViolationException ex){
        return new ExceptionResponse(ErrorCode.INVALID_INPUT_EXCEPTION.getStatus(), ex.getMessage());

    }
}
