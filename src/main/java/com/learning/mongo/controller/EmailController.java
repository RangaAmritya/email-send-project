package com.learning.mongo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class EmailController {



    @GetMapping("/email")
    public String showEmailForm(Model model) {
        return "email_form"; // Looks for email_form.html in templates
    }


    @PostMapping("/send-email")
    public String sendEmail(@RequestParam String to,
                            @RequestParam String subject,
                            @RequestParam String gender,
                            @RequestParam("file") MultipartFile file,
                            Model model) {

        String msg = "Email sent successfully to " + to + " (" + gender + ")";
        System.out.println(msg);
        model.addAttribute("message", msg);
        return "email_form";
    }
}
