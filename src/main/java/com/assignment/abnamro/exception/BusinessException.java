package com.assignment.abnamro.exception;

import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {
    private final Integer code;

    public BusinessException(ApiErrorCode errorType) {
        super(errorType.getErrorMessage());
        this.code = errorType.getErrorCode();
    }
}
