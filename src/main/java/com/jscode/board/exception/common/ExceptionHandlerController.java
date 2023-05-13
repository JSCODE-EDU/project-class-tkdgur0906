package com.jscode.board.exception.common;

import com.jscode.board.exception.common.response.BusinessExceptionResponse;
import com.jscode.board.exception.common.response.FieldExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<BusinessExceptionResponse> handleBusinessException(BusinessException ex){
        BusinessExceptionResponse response = BusinessExceptionResponse.from(ex);
        return new ResponseEntity<>(response, ex.getHttpStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<FieldExceptionResponse> handleValidationException(MethodArgumentNotValidException ex){
        FieldExceptionResponse response = FieldExceptionResponse.from(ErrorCode.INVALID_INPUT_EXCEPTION, ex);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
