package com.boye.batchsendmail.services;

import com.boye.batchsendmail.entities.Car;
import com.boye.batchsendmail.entities.Client;
import com.boye.batchsendmail.repositories.CarRepository;
import com.boye.batchsendmail.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SendMailService {
    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private CarRepository carRepository ;

    @Autowired
    private ClientRepository clientRepository ;


    private List<Car> getCar(){
      return carRepository.findAll();
    }

    public void sendEmail() {

       // get mlist of car
        List<Car> carList = getCar();
     ///   get lis of libelle car
        List<String> libelles = carList.stream().map(Car::getLibelle).collect(Collectors.toList());
        SimpleMailMessage msg = new SimpleMailMessage();

        List<Client> clients = clientRepository.findAll();
        if(clients != null){
            msg.setTo(clients.listIterator().next().getEmail());
            msg.setSubject("Testing from Spring Boot");
            msg.setText("Voici la liste des car "+libelles.toString().toUpperCase());
            msg.setSentDate(new Date());
            javaMailSender.send(msg);
        }

    }
    public void sendEmailWithAttachment() throws MessagingException, IOException {

        MimeMessage msg = javaMailSender.createMimeMessage();

        // true = multipart message
        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
        helper.setTo("1@gmail.com");

        helper.setSubject("Testing from Spring Boot");

        helper.setText("<h1>Check attachment for image!</h1>", true);

        helper.addAttachment("my_photo.png", new ClassPathResource("android.png"));

        javaMailSender.send(msg);

    }
}
