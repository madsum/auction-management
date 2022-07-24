package com.cognizant.product.query.api.scheduler;

import com.cognizant.product.query.api.config.PropertyLoader;
import lombok.extern.java.Log;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import java.util.Date;

//@Component
@EnableAsync
@Log
public class ParallelScheduler {
    @Async
    @Scheduled(fixedRateString="${scheduler_rate}")
    public void sendmail() throws MessagingException {
        log.info("Hello from our Fixed delay method");

        Session session = Session.getInstance(PropertyLoader.getProperty(), new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(PropertyLoader.getPropertyByKey(PropertyLoader.USER_NAME_KEY), PropertyLoader.getPropertyByKey(PropertyLoader.USER_PASSWORD_KEY));
            }
        });
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress("tutorialspoint@gmail.com", false));

        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse("sabrina.yemin@gmail.com"));
        msg.setSubject("Tutorials point email");
        msg.setContent("Tutorials point email", "text/html");
        msg.setSentDate(new Date());

        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent("Tutorials point email", "text/html");

/*        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
        MimeBodyPart attachPart = new MimeBodyPart();

        attachPart.attachFile("/var/tmp/image19.png");
        multipart.addBodyPart(attachPart);
        msg.setContent(multipart);*/
        Transport.send(msg);
    }
}
