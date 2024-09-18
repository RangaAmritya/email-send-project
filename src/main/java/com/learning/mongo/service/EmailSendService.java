package com.learning.mongo.service;

import com.learning.mongo.entity.EmailDetail;
import jakarta.mail.MessagingException;
//import com.learning.mongo.entity.EmilDetail;

public interface EmailSendService {
    String sendSimpleMail(EmailDetail details);

    String senMailWithAttachment(EmailDetail details);

    String sendOtpToMail(String textOtp);

    String sendTemMessage();

    String senHtmlTemplate() throws MessagingException;

    String sendHRMailUsingThemeLeaf(EmailDetail emailDetail) throws MessagingException;

    Object sendEmailOtp(String receiver) throws MessagingException;

    String verifyEmailOtp(String otp, String transactionId);
}
