package com.assignment.abnamro.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Getter
@AllArgsConstructor
public enum ApiErrorCode {
    RECIPE_NOT_FOUND(1000, "Recipe could not found.", NOT_FOUND);

    private final int errorCode;
    private final String errorMessage;
    private final HttpStatus httpStatus;
}
