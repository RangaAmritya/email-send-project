package com.learning.mongo.controller;

import com.learning.mongo.entity.EmailDetail;
//import com.learning.mongo.entity.EmilDetail;
import com.learning.mongo.service.EmailSendService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

}
