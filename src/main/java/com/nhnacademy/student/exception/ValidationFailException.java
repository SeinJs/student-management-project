package com.nhnacademy.student.exception;

import org.springframework.validation.BindingResult;

import java.util.stream.Collectors;

public class ValidationFailException extends RuntimeException{
    public ValidationFailException(BindingResult bindingResult) {
        super(
                bindingResult.getAllErrors()
                        .stream()
                        .map(error -> new StringBuilder().append("ObjectName = ")
                                .append(error.getObjectName())
                                .append(", Message = ")
                                .append(error.getDefaultMessage())
                                .append(", Code = ")
                                .append(error.getCode())
                        ).collect(Collectors.joining("|"))
        );
    }
}
