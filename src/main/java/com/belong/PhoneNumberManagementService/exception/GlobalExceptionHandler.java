package com.belong.PhoneNumberManagementService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<ExceptionResponse> handleAllExceptions(Exception ex) {

        return new ResponseEntity<>(ExceptionResponse.builder()
                .message(ex.getMessage())
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .build(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BadRequestResponseException.class)
    public final ResponseEntity<ExceptionResponse> handleBadRequestExceptions(
            BadRequestResponseException ex) {

        return new ResponseEntity<>(ExceptionResponse.builder()
                .message(ex.getMessage())
                .status(HttpStatus.BAD_REQUEST)
                .build(), HttpStatus.BAD_REQUEST);

    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public final ResponseEntity<ExceptionResponse> handleResourceNotFoundRequestExceptions(
            ResourceNotFoundException ex, WebRequest request) {

        return new ResponseEntity<>(ExceptionResponse.builder()
                .message(ex.getMessage())
                .status(HttpStatus.NOT_FOUND)
                .build(), HttpStatus.NOT_FOUND);
    }
}
