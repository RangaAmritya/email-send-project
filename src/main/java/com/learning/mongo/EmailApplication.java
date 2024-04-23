package com.learning.mongo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//import static com.learning.mongo.service.impl.EmailSendServiceImpl.sender;

@SpringBootApplication
//@ComponentScan(value={"org.apache.velocity.app.VelocityEngine","com.learning.mongo"})
public class EmailApplication {


	public static void main(String[] args) {
		SpringApplication.run(EmailApplication.class, args);
	}

}
