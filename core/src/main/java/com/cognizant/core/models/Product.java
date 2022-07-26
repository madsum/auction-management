package com.cognizant.core.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder    
@Document(collection = "products")
public class Product implements Serializable {

    private final static String DATE_PATTERN = "EEE MMM d yyyy HH:mm:ss 'GMT'XX (zzzz)";

    @Id
    private String id;
    @NotEmpty(message = "name is mandatory")
    private String productName;
    @NotEmpty(message = "price is mandatory")
    private int price;
    private int bidPrice;
    private String photoUrl;
    private Date auctionEndTime;
    private boolean isSold;
    private String bidderEmail;
    @Transient
    private String strAuctionEndTime;
/*
    public void setAuctionEndTime(String auctionEndTimeStr) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_PATTERN, Locale.ROOT);
        ZonedDateTime zdt = ZonedDateTime.parse(auctionEndTimeStr, dateTimeFormatter.withZone(ZoneId.systemDefault()));
        auctionEndTime = Date.from(zdt.toInstant());
    }*/
}
