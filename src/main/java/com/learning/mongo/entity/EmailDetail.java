package com.learning.mongo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmailDetail {

    private List<Recipient> recipients;
    private String message;
    private String subject;
    private String attachment;
}
