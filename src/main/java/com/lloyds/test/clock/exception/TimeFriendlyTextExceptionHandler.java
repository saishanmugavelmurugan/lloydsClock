package com.lloyds.test.clock.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class TimeFriendlyTextExceptionHandler {
    @ExceptionHandler(IllegalArgumentException.class)
    public ProblemDetail illegalArgumentExceptionHandler(IllegalArgumentException ex, WebRequest request) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ProblemDetail internalServerExceptionHandler(Exception ex, WebRequest request) {
        return ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
    }

}
