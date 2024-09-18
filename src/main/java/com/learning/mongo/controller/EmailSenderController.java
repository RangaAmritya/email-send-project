package com.learning.mongo.controller;

import com.learning.mongo.entity.EmailDetail;
import com.learning.mongo.service.EmailSendService;
import io.micrometer.core.annotation.Timed;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@RestController
public class EmailSenderController {

    @Autowired
    public EmailSendService emailService;

    @PostMapping("/sendMail")
    public String
    sendMail(@RequestBody EmailDetail details)
    {
        String status
                = emailService.sendSimpleMail(details);

        return status;
    }

    @PostMapping("/sendMailWithAttachment")
    public ResponseEntity<?> sendMailWithAttachment(@RequestBody EmailDetail details){
        String status = emailService.senMailWithAttachment(details);
        return new ResponseEntity<>(status, HttpStatus.OK);
    }

    @GetMapping("/sendOtpToMail")
     public ResponseEntity<?> sendOtpToMail(@RequestParam String textOtp){
        String status = emailService.sendOtpToMail(textOtp);
        return new ResponseEntity<>(status,HttpStatus.OK);
    }

    @GetMapping("/sendWithTemplate")
    public ResponseEntity<?> sendTemMessage(){
        String status = emailService.sendTemMessage();
        return  new ResponseEntity<>(status,HttpStatus.OK);
    }

    @GetMapping("/sendHtmlTemplate")
    public ResponseEntity<?> sendHtmlTemplate() throws MessagingException {
        String status = emailService.senHtmlTemplate();
        return new ResponseEntity<>(status,HttpStatus.OK);
    }

    @PostMapping("/sendHRMailThemeLeaf")
    public ResponseEntity<?> sendHRMailUsingThemeLeaf(@RequestBody EmailDetail emailDetail) throws MessagingException {
        String status = emailService.sendHRMailUsingThemeLeaf(emailDetail);
        return new ResponseEntity<>(status,HttpStatus.OK);
    }
    @Timed(value = "my_custom_metric", description = "Time taken for my custom API")
    @PostMapping("/sendEmailOtp")
    public ResponseEntity<?> sendEmailOtp(@RequestParam String receiver) throws MessagingException {
        return ResponseEntity.ok(emailService.sendEmailOtp(receiver));
    }
    @PostMapping("/verifyEmailOtp")
    public ResponseEntity<?> verifyEmailOtp(@RequestParam String otp,@RequestParam String transactionId){
        return ResponseEntity.ok(emailService.verifyEmailOtp(otp,transactionId));
    }
}
