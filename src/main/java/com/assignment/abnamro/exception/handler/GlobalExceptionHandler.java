package com.assignment.abnamro.exception.handler;


import com.assignment.abnamro.exception.BusinessException;
import com.assignment.abnamro.exception.response.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Calendar;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleUnknownException(RuntimeException ex) {
        ErrorResponse body = ErrorResponse.builder()
                .httpStatus(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message(ex.getMessage())
                .timestamp(Calendar.getInstance().getTime()) // Joda time jv8
                .build();

        return new ResponseEntity<>(body, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({BusinessException.class})
    public ResponseEntity<Object> handleBusinessException(BusinessException ex) {
        ErrorResponse body = ErrorResponse.builder()
                .httpStatus(HttpStatus.NOT_FOUND.value())
                .code(ex.getCode())
                .message(ex.getMessage())
                .timestamp(Calendar.getInstance().getTime())
                .build();

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }
}


