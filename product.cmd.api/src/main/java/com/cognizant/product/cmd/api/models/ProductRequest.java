package com.cognizant.product.cmd.api.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Transient;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest implements Serializable {
    private final static String DATE_PATTERN = "dd MM yyyy HH:mm:ss";

    private String id;
    @NotEmpty(message = "name is mandatory")
    private String name;
    @NotEmpty(message = "price is mandatory")
    private int price;
    @Transient
    private MultipartFile file;
    private int bidPrice;
    private String photoUrl;
    private String auctionEndTime;
    private boolean isSold;
    private String  bidderEmail;
/*    public void setAuctionEndTime(String auctionEndTimeStr) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_PATTERN, Locale.ROOT);
        try {
            ZonedDateTime zdt = ZonedDateTime.parse(auctionEndTimeStr, dateTimeFormatter.withZone(ZoneId.systemDefault()));
            auctionEndTime = Date.from(zdt.toInstant());
        }catch (Exception e){
            System.out.printf(e.getMessage());
        }

    }*/
}
