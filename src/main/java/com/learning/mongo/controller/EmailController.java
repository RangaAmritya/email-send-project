package com.learning.mongo.controller;


import com.learning.mongo.service.EmailSendService;
import jakarta.mail.MessagingException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class EmailController {

    private final EmailSendService emailSendService;

    public EmailController(EmailSendService emailSendService){
       this.emailSendService=emailSendService;
    }


    @GetMapping("/email")
    public String showEmailForm(Model model) {
        return "email_form"; // Looks for email_form.html in templates
    }


    @PostMapping("/send-email")
    public String sendEmail(@RequestParam String to,
                            @RequestParam String subject,
                            @RequestParam(required = false) String gender,
                            @RequestParam(value = "file",required = false) MultipartFile file,
                            Model model) throws MessagingException, IOException {

        emailSendService.sendEmailToRecruiter(to,subject,gender,file);

        String msg = "Email sent successfully to " + to + " (" + gender + ")";
        System.out.println(msg);
        model.addAttribute("message", msg);
        return "email_form";
    }
}
