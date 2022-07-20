package com.cognizant.core.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Transient;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest implements Serializable {
    private String id;
    @NotEmpty(message = "name is mandatory")
    private String name;
    @NotEmpty(message = "price is mandatory")
    private int price;
    @Transient
    private MultipartFile file;
    private int bidPrice;
    private String photoUrl;
}
