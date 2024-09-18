package com.learning.mongo.customExceptionHandler.errorModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ErrorResponse {

    private String errorMessage;
    private String status;
    private Date time;
}