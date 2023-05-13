package com.jscode.board.exception.common.response;

import com.jscode.board.exception.common.ErrorCode;
import com.jscode.board.exception.common.FieldException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class FieldExceptionResponse {

    private final int status;
    private final String message;
    private final List<FieldException> fieldExceptions;

    public static FieldExceptionResponse from(ErrorCode errorCode, MethodArgumentNotValidException ex){
        return new FieldExceptionResponse(errorCode.getStatus(), errorCode.getMessage(),
                FieldException.create(ex.getBindingResult()));
    }
}
