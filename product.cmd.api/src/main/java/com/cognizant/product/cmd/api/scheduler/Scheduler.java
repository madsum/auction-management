package com.cognizant.product.cmd.api.scheduler;

import com.cognizant.product.cmd.api.config.PropertyLoader;
import com.cognizant.product.cmd.api.models.Product;
import com.cognizant.product.cmd.api.service.ProductDbService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.net.Authenticator;
import java.util.Date;

@Component
@Log
public class Scheduler {
   private final ProductDbService productDbService;
    @Value("${scheduler_rate}")
   int schedulerRate;

    public Scheduler(ProductDbService productDbService) {
        this.productDbService = productDbService;
    }

  //  @Async
    @Scheduled(fixedRateString="${scheduler_rate}")
    public void scheduleCall(){
        log.info("ScheduleCall on every "+schedulerRate/1000+" seconds");
        var result = productDbService.findBySold();
        result.forEach(product -> {
            System.out.println("stop");
            if( (product.getAuctionEndTime().getTime() < System.currentTimeMillis())) {
                if(sendmail(product)){
                    product.setSold(true);
                    productDbService.updateProduct(product);
                }
            }
        });
    }
    public boolean sendmail(Product product){
        boolean emailSuccessful = false;
        Session session = Session.getInstance(PropertyLoader.getProperty(), new javax.mail.Authenticator() {
                protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(PropertyLoader.getPropertyByKey(PropertyLoader.USER_NAME_KEY), PropertyLoader.getPropertyByKey(PropertyLoader.USER_PASSWORD_KEY));
                }
        });
        try {
            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress("madsum@gmail.com", false));

            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(product.getBidderEmail()));
            msg.setSubject("Congregation for winning the bid on "+product.getProductName());
            msg.setContent("Hi, \n Please pay "+product.getBidPrice(), "text/html");
            msg.setSubject("Congratulation for winning the bid on "+product.getProductName());
            msg.setContent("Hi,\n You have won the bid.\n Please pay "+product.getBidPrice(), "text/html");
            msg.setSentDate(new Date());
/*
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent("Tutorials point email", "text/html");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            MimeBodyPart attachPart = new MimeBodyPart();

            attachPart.attachFile("/var/tmp/image19.png");
            multipart.addBodyPart(attachPart);
            msg.setContent(multipart);*/
            Transport.send(msg);
            emailSuccessful = true;
        }catch (MessagingException e){
            log.warning("Failed to send email: "+e.getMessage());
        }
        return emailSuccessful;
        }

}
