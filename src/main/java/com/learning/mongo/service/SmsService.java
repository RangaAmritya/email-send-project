package com.learning.mongo.service;

import org.springframework.stereotype.Service;

import java.util.Map;
@Service
public interface SmsService {
    Object sendOtp(String message,  Map<String, String> apiDetails);
}
