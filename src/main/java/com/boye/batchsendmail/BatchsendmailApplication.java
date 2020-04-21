package com.boye.batchsendmail;

import com.boye.batchsendmail.services.SendMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.mail.MessagingException;
import java.io.IOException;

@SpringBootApplication
public class BatchsendmailApplication implements CommandLineRunner {

    @Autowired
    private SendMailService sendEmail;

    public static void main(String[] args) {
        SpringApplication.run(BatchsendmailApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Sending Email...");

        sendEmail.sendEmail();
        //sendEmailWithAttachment();

        System.out.println("Done");

    }

}
