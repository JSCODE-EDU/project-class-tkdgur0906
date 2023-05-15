package com.jscode.board.exception.response;

import com.jscode.board.exception.common.BusinessException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;


@Getter
@RequiredArgsConstructor
public class BusinessExceptionResponse {

    private final int status;
    private final String message;


    public static BusinessExceptionResponse from(BusinessException businessException){
        return new BusinessExceptionResponse(businessException.getStatus(), businessException.getMessage());
    }

}
