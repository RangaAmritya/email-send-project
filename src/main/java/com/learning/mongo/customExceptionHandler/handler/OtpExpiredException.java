package com.learning.mongo.customExceptionHandler.handler;

public class OtpExpiredException extends RuntimeException{
    public OtpExpiredException(String message){
        super(message);
    }

}
