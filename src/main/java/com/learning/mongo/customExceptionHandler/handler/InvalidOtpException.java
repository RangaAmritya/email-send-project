package com.learning.mongo.customExceptionHandler.handler;

public class InvalidOtpException extends RuntimeException {
    public InvalidOtpException(String message) {
        super(message);
    }
}