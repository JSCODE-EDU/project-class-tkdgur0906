package com.jscode.board.exception.common.response;

import com.jscode.board.exception.common.BusinessException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ExceptionResponse {

    private final String status;
    private final String message;

    public static ExceptionResponse from(BusinessException businessException){
        return new ExceptionResponse(businessException.getStatus(), businessException.getMessage());
    }
}
