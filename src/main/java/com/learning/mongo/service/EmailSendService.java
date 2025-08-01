package com.learning.mongo.service;

import com.learning.mongo.entity.EmailDetail;
import jakarta.mail.MessagingException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
//import com.learning.mongo.entity.EmilDetail;

public interface EmailSendService {
//    String sendSimpleMail(EmailDetail details);

//    String senMailWithAttachment(EmailDetail details);

    String sendOtpToMail(String textOtp);

    String sendTemMessage();

    String senHtmlTemplate() throws MessagingException;

    void sendEmailToRecruiter(String to, String subject, String gender, MultipartFile file) throws MessagingException, IOException;

//    String sendHRMailUsingThemeLeaf(EmailDetail emailDetail) throws MessagingException;

//    Object sendEmailOtp(String receiver) throws MessagingException;

//    String verifyEmailOtp(String otp, String transactionId);
}
