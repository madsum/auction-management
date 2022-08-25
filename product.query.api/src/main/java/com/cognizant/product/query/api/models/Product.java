package com.cognizant.product.query.api.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Transient;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
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
