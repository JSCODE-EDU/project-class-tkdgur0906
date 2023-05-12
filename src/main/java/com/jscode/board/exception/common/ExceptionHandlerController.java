package com.jscode.board.exception.common;

import com.jscode.board.exception.common.response.ExceptionResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(BusinessException.class)
    private ResponseEntity<ExceptionResponse> handleBusinessException(BusinessException exception){
        ExceptionResponse response = ExceptionResponse.from(exception);
        return new ResponseEntity<>(response, exception.getHttpStatus());
    }
}
