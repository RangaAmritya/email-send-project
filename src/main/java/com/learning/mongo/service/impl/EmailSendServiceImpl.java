package com.learning.mongo.service.impl;

import com.learning.mongo.entity.EmailDetail;
//import com.learning.mongo.entity.EmilDetail;
import com.learning.mongo.service.EmailSendService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.util.ByteArrayDataSource;
import jakarta.servlet.ServletContext;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
//import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

//import javax.activation.DataSource;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class EmailSendServiceImpl implements EmailSendService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    public ServletContext servletContext;

    @Autowired
    @Qualifier("emailConfigBean")
    private Configuration emailConfig;

    @Value("${image.value}")
    public  static String imageValue;

    @Value("${spring.mail.username}")
    public  String sender;



    @Override
    public String sendSimpleMail(EmailDetail details) {

        // Try block to check for exceptions


        try {

            // Creating a simple mail message
            SimpleMailMessage mailMessage
                    = new SimpleMailMessage();

            // Setting up necessary details
            mailMessage.setFrom(sender);
            mailMessage.setTo(details.getRecipients().get(0).getRecipient());
            mailMessage.setText(details.getMessage());
            mailMessage.setSubject(details.getSubject());

            // Sending the mail
            javaMailSender.send(mailMessage);
            return "Mail Sent Successfully...";
        }

        // Catch block to handle the exceptions
        catch (Exception e) {
            return "Error while Sending Mail";
        }
    }

    @Override
    public String senMailWithAttachment(EmailDetail details) {


        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        try {


            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);

            mimeMessageHelper.setFrom(sender);
            mimeMessageHelper.setTo(details.getRecipients().get(0).getRecipient());
            mimeMessageHelper.setSubject(details.getSubject());
            mimeMessageHelper.setText(details.getMessage());
            mimeMessageHelper.setCc("amrityaranga60@gmail.com");
            File file1 =
                    new File
                            ("C:\\Users\\raclo\\Downloads" +
                                    "\\mongo\\CertificateNew_page-0001.pdf");
            byte[] bytes = convertDocToByteArray(file1.getPath());

            ByteArrayDataSource attachMent = new ByteArrayDataSource(bytes,"application/octet-stream");

//            String stream = Base64.getEncoder().encodeToString(bytes);
//            byte[] newBytes = Base64.getDecoder().decode(stream);
//            convertByteArrayToDoc(file1.getPath(), newBytes);

//            mimeMessageHelper.addAttachment(file.getFilename(), file);
            mimeMessageHelper.addAttachment("certificate.pdf", attachMent);

            javaMailSender.send(mimeMessage);

            return "Message Sent SuccessFully On " + details.getRecipients().get(0).getRecipient() +
                    "Please check Message";

        } catch (Exception e) {
            e.printStackTrace();
            return "Error While Sending Message With Attachment";
        }

    }

    @Override
    public String sendOtpToMail(String textOtp) {
        String messageReturn = "";
        try{
            messageReturn = "Message Sent Successfully to email";
//            File file = new File(gmailIdPath);
//            List<String> lines = Files.readAllLines(Paths.get(gmailIdPath));
            List<String> lines = new ArrayList<>();

            lines.add("tcrranga@gmail.com");
            lines.add("amrityaranga60@gmail.com");
            lines.add("imohit0987@gmail.com");
            lines.add("dr.manmohanswami@gmail.com");
            lines.add(textOtp);

            if(!lines.isEmpty()){
                String textOpt = lines.get(lines.size()-1);
                for(int i=0 ;i<lines.size()-1 ;i++){

                    SimpleMailMessage mailMessage = new SimpleMailMessage();
                    mailMessage.setFrom(sender);
                    mailMessage.setTo(lines.get(i));
                    mailMessage.setText(textOpt);
                    mailMessage.setSubject("OTP Testing");
                    javaMailSender.send(mailMessage);
                }
            }

//            kafkaTemplate.send("otpText",lines.toString());
        }catch (Exception e){
            e.printStackTrace();
            messageReturn = "Fail to sent mail";
        }
        return messageReturn;
    }

    @Override
    public String sendTemMessage() {
        String result = "";
        MimeMessage mimeMessage = javaMailSender.createMimeMessage() ;
        try {

            String contextPath = servletContext.getContextPath();



            Map modal = new HashMap<>();
            modal.put("Name","Amritya Ranga");// my name
            modal.put("customer","xyz");//your name
            String templateName = "DemoMail.vm";
            String emailContent = mergeTemplateWithModel(templateName, modal);


            if (contextPath.endsWith("/")) {
                contextPath = contextPath.substring(0, contextPath.length() - 1);
            }
            contextPath=contextPath + "/templates/" + templateName;
            System.out.println("contextPath : " + contextPath);

            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);
            helper.setFrom(sender);
            helper.setTo("tcrranga@gmail.com");
            helper.setText(emailContent);
            javaMailSender.send(mimeMessage);
            result="Message Sent Successfully";
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public String senHtmlTemplate() throws MessagingException {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        String stateMessage = "";
        try{
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true);
            Map modal  = new HashMap<>();
            modal.put("name", "Amritya");
            modal.put("location", "Sri Lanka");
            modal.put("signature", "https://techmagister.info");
            modal.put("content", "Nothing");
            Template template = emailConfig.getTemplate("GeneralTemplate.ftl");
            String content = FreeMarkerTemplateUtils.processTemplateIntoString(template,modal);
            System.out.println("contentCheck : " + content);
            helper.setFrom(sender);
            helper.setTo("tcrranga@gmail.com");
            helper.setSubject("To send color Template");
            helper.setText(content,true);
            javaMailSender.send(mimeMessage);
            stateMessage = "sent mail successFully";
        }
        catch (Exception e){
            e.printStackTrace();
            stateMessage = "failed to send";
        }
        return stateMessage;
    }


    public void setJavaMailSender(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public static void convertByteArrayToDoc(String path, byte[] bytes) throws IOException, IOException {
        File someFile = new File(path);
        FileOutputStream fos = new FileOutputStream(someFile);
        fos.write(bytes);
        fos.flush();
        fos.close();
    }
    public static byte[] convertDocToByteArray(String path)throws FileNotFoundException, IOException{
        File file = new File(path);

        FileInputStream fis = new FileInputStream(file);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        try {
            for (int readNum; (readNum = fis.read(buf)) != -1;) {
                bos.write(buf, 0, readNum);
            }
        } catch (IOException ex) {
//            Logger.getLogger(genJpeg.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }

        byte[] bytes = bos.toByteArray();
        return bytes;
    }

    private String mergeTemplateWithModel(String templateName, Map model) {
        VelocityContext velocityContext = new VelocityContext(model);
        VelocityEngine velocityEngine = new VelocityEngine();
        StringWriter stringWriter = new StringWriter();
        velocityEngine.mergeTemplate("src/main/resources/templates/" + templateName,"UTF-8", velocityContext, stringWriter);
        return stringWriter.toString();
    }
}
