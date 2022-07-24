package com.cognizant.core.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder    
@Document(collection = "products")
public class Product implements Serializable {
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
    private String  bidderEmail;
}
