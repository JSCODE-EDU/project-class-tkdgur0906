package com.jscode.board.exception.common.response;

import com.jscode.board.exception.common.ErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
public class ExceptionResponse {

    private final int status;
    private final String message;

    public static ExceptionResponse from(ConstraintViolationException ex){
        return new ExceptionResponse(ErrorCode.INVALID_INPUT_EXCEPTION.getStatus(), ex.getMessage());

    }
}
