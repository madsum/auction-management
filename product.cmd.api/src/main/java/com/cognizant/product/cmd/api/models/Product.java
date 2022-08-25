package com.cognizant.product.cmd.api.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Transient;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder    
@Table(name = "products")
@Entity
public class Product implements Serializable {

    private final static String DATE_PATTERN = "EEE MMM d yyyy HH:mm:ss 'GMT'XX (zzzz)";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    @NotEmpty(message = "name is mandatory")
    private String productName;
    @NotEmpty(message = "price is mandatory")
    private int price;
    private int bidPrice;
    private String photoUrl;
    private String auctionEndTime;
    private boolean isSold;
    private String bidderEmail;
    @Transient
    private String strAuctionEndTime;

    public Date getAuctionEndTime(){
        SimpleDateFormat formatter =new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
        Date date = new Date();
        try {
            date = formatter.parse(auctionEndTime);
        } catch (ParseException e) {
            System.out.printf("Date time parse exception: "+e.getMessage());
        }
        return date;
    }
/*
    public void setAuctionEndTime(String auctionEndTimeStr) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_PATTERN, Locale.ROOT);
        ZonedDateTime zdt = ZonedDateTime.parse(auctionEndTimeStr, dateTimeFormatter.withZone(ZoneId.systemDefault()));
        auctionEndTime = Date.from(zdt.toInstant());
    }*/
}
