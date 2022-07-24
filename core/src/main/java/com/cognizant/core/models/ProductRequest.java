package com.cognizant.core.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Transient;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest implements Serializable {
    private final static String DATE_PATTERN = "EEE MMM d yyyy HH:mm:ss 'GMT'XX (zzzz)";

    private String id;
    @NotEmpty(message = "name is mandatory")
    private String name;
    @NotEmpty(message = "price is mandatory")
    private int price;
    @Transient
    private MultipartFile file;
    private int bidPrice;
    private String photoUrl;
    private Date auctionEndTime;
    private boolean isSold;
    private String  bidderEmail;
    public void setAuctionEndTime(String auctionEndTimeStr) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_PATTERN, Locale.ROOT);
        ZonedDateTime zdt = ZonedDateTime.parse(auctionEndTimeStr, dateTimeFormatter.withZone(ZoneId.systemDefault()));
        auctionEndTime = Date.from(zdt.toInstant());
    }
}
