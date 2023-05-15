package com.jscode.board.exception.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@RequiredArgsConstructor
public class FieldException {

    private final String field;
    private final String value;
    private final String message;

    public static List<FieldException> create(BindingResult result){
        List<FieldError> fieldErrors = result.getFieldErrors();
        return fieldErrors.stream()
                .map(error -> new FieldException(
                        error.getField(),
                        (error.getRejectedValue() == null)? null : error.getRejectedValue().toString(),
                        error.getDefaultMessage()
                )).collect(Collectors.toList());
    }
}
