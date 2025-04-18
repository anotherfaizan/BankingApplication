package com.fz.myBank.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<CustomErrorResponse> handleAccountNotFoundException(AccountNotFoundException ex, WebRequest req){
        CustomErrorResponse customErrorResponse = new CustomErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                LocalDateTime.now(),
                ex.getMessage(),
                req.getDescription(false)
        );
        return new ResponseEntity<CustomErrorResponse>(customErrorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<CustomErrorResponse> handleTypeMismatch(MethodArgumentTypeMismatchException ex, WebRequest req){
        CustomErrorResponse customErrorResponse = new CustomErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now(),
                "Account Id must be a valid number",
                req.getDescription(false)
        );
        return new ResponseEntity<>(customErrorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<CustomErrorResponse> handleNumberFormatException (NumberFormatException ex,WebRequest req){
        CustomErrorResponse customErrorResponse = new CustomErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now(),
                "Account Id must be a valid number",
                req.getDescription(false)
        );
        return new ResponseEntity<>(customErrorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomErrorResponse> handleGenericException(Exception ex, WebRequest req){
        CustomErrorResponse customErrorResponse = new CustomErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                LocalDateTime.now(),
                "An unexpected error occurred: " + ex.getMessage(),
                req.getDescription(false)
        );
        return new ResponseEntity<CustomErrorResponse>(customErrorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
