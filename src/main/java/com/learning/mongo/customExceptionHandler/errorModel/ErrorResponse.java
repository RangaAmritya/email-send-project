package com.learning.mongo.customExceptionHandler.errorModel;

//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;

import java.util.Date;


public class ErrorResponse {

    private String errorMessage;
    private String status;
    private Date time;

//    public ErrorResponse() {
//    }

    public ErrorResponse(String errorMessage, String status, Date time) {
        this.errorMessage = errorMessage;
        this.status = status;
        this.time = time;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}