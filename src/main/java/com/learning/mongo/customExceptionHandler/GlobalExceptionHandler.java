package com.learning.mongo.customExceptionHandler;

import com.learning.mongo.customExceptionHandler.handler.InvalidOtpException;
import com.learning.mongo.customExceptionHandler.handler.OtpExpiredException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.learning.mongo.customExceptionHandler.errorModel.ErrorResponse;

import java.util.Date;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = OtpExpiredException.class)
    public ResponseEntity<ErrorResponse> otpExpiredExceptionHandler(OtpExpiredException exception) {
        return new ResponseEntity<>(new ErrorResponse(exception.getMessage(), "failure", new Date()),
                HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = InvalidOtpException.class)
    public ResponseEntity<ErrorResponse> invalidOtpExceptionHandler(InvalidOtpException exception) {
        return new ResponseEntity<>(new ErrorResponse(exception.getMessage(), "failure", new Date()),
                HttpStatus.CONFLICT);
    }
}