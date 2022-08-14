package com.assignment.abnamro.exception.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
@JsonInclude(Include.NON_NULL)
public class ErrorResponse {
    private Integer httpStatus;
    private Integer code;
    private String message;
    private Date timestamp;
}
