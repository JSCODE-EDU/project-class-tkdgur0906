package com.jscode.board.exception.common;

import com.jscode.board.exception.response.BusinessExceptionResponse;
import com.jscode.board.exception.response.ExceptionResponse;
import com.jscode.board.exception.response.FieldExceptionResponse;
import com.jscode.board.exception.code.ErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(BusinessException.class)
    protected ResponseEntity<BusinessExceptionResponse> handleBusinessException(BusinessException ex){
        BusinessExceptionResponse response = BusinessExceptionResponse.from(ex);
        return new ResponseEntity<>(response, ex.getHttpStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<FieldExceptionResponse> handleValidationException(MethodArgumentNotValidException ex){
        FieldExceptionResponse response = FieldExceptionResponse.from(ErrorCode.INVALID_INPUT_EXCEPTION, ex);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    protected ResponseEntity<ExceptionResponse> handleConstraintViolationException(ConstraintViolationException ex){
        ExceptionResponse response = ExceptionResponse.from(ex);
        return  new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

}
