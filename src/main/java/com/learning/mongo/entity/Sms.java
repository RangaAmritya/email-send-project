package com.learning.mongo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Sms {

    private String apiKey;

    private String message;

    private String number;

    private String MMsUrl;
}
